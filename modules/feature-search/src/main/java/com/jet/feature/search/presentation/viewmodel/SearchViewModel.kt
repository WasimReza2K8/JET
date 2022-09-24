/*
 * Copyright 2021 Wasim Reza.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jet.feature.search.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.core.R.string
import com.example.core.navigation.Navigator
import com.example.core.resProvider.ResourceProvider
import com.example.core.state.Output.NetworkError
import com.example.core.state.Output.Success
import com.example.core.state.Output.UnknownError
import com.example.core.ui.R.string.no_restaurant
import com.example.core.viewmodel.BaseViewModel
import com.jet.feature.search.domain.usecase.SearchUseCase
import com.jet.feature.search.presentation.viewmodel.SearchContract.Effect.NetworkErrorEffect
import com.jet.feature.search.presentation.viewmodel.SearchContract.Effect.UnknownErrorEffect
import com.jet.feature.search.presentation.viewmodel.SearchContract.Event.OnBackButtonClicked
import com.jet.feature.search.presentation.viewmodel.SearchContract.Event.OnQueryClearClicked
import com.jet.feature.search.presentation.viewmodel.SearchContract.Event.OnSearch
import com.jet.feature.search.presentation.viewmodel.SearchContract.State
import com.jet.restaurant.presentation.mapper.mapToRestaurantUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val resourceProvider: ResourceProvider,
    private val navigator: Navigator,
) : BaseViewModel<SearchContract.Event, State, SearchContract.Effect>() {

    override fun provideInitialState() = State()

    override fun handleEvent(event: SearchContract.Event) {
        when (event) {
            is OnSearch -> {
                updateState { copy(query = event.query) }
                startQuery(event.query)
            }
            OnBackButtonClicked -> {
                navigator.navigateUp()
            }
            OnQueryClearClicked -> updateState { copy(query = "") }
        }
    }

    private fun startQuery(query: String) {
        viewModelScope.launch {
            flowOf(query)
                .debounce(DEBOUNCE_TIME)
                .filter {
                    updateState {
                        copy(
                            noResultFoundText = "",
                            restaurants = emptyList(),
                        )
                    }
                    return@filter it.isNotEmpty()
                }
                .shareIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed()
                )
                .flatMapLatest { query ->
                    searchUseCase(query)
                }.collect { output ->
                    when (output) {
                        is Success -> {
                            if (output.result.isEmpty()) {
                                updateState {
                                    copy(
                                        noResultFoundText = resourceProvider.getString(no_restaurant),
                                        restaurants = emptyList()
                                    )
                                }
                            } else {
                                updateState {
                                    copy(
                                        restaurants = output.result.map { restaurant ->
                                            restaurant.mapToRestaurantUi(resourceProvider)
                                        }
                                    )
                                }
                            }
                        }
                        NetworkError -> {
                            sendEffect(NetworkErrorEffect(resourceProvider.getString(string.network_error)))
                        }
                        UnknownError -> {
                            sendEffect(UnknownErrorEffect(resourceProvider.getString(string.unknown_error)))
                        }
                    }
                }
        }
    }

    companion object {
        private const val DEBOUNCE_TIME = 350L
    }
}
