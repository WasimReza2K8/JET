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

package com.jet.feature.restaurant.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.core.viewmodel.BaseViewModel
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnBackButtonClicked
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnViewModelInit
import com.jet.restaurant.domain.usecase.RestaurantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val useCase: RestaurantUseCase,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<RestaurantContract.Event, RestaurantContract.State, RestaurantContract.Effect>() {

    override fun provideInitialState() = RestaurantContract.State()

    override fun handleEvent(event: RestaurantContract.Event) {
        when (event) {
            OnViewModelInit -> {}
            OnBackButtonClicked -> {}
        }
    }

    init {}
}
