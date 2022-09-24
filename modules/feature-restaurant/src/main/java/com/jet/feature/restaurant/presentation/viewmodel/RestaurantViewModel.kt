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

import androidx.lifecycle.viewModelScope
import com.example.core.R.string
import com.example.core.navigation.Navigator
import com.example.core.resProvider.ResourceProvider
import com.example.core.state.Output
import com.example.core.state.Output.NetworkError
import com.example.core.state.Output.UnknownError
import com.example.core.ui.R.string.no_restaurant
import com.example.core.viewmodel.BaseViewModel
import com.jet.feature.restaurant.domain.model.SortingType
import com.jet.feature.restaurant.domain.model.SortingType.AverageProductPrice
import com.jet.feature.restaurant.domain.model.SortingType.BestMatch
import com.jet.feature.restaurant.domain.model.SortingType.DeliveryCost
import com.jet.feature.restaurant.domain.model.SortingType.Distance
import com.jet.feature.restaurant.domain.model.SortingType.MinCost
import com.jet.feature.restaurant.domain.model.SortingType.Newest
import com.jet.feature.restaurant.domain.model.SortingType.Popularity
import com.jet.feature.restaurant.domain.model.SortingType.RatingAverage
import com.jet.feature.restaurant.domain.usecase.GetDefaultRestaurantsUseCase
import com.jet.feature.restaurant.domain.usecase.GetSortedRestaurants
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Effect.NetworkErrorEffect
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Effect.UnknownErrorEffect
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnAverageProductPriceClicked
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnBestMatchClicked
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnDeliveryCostClicked
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnDistanceClicked
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnMinCostClicked
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnNewestClicked
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnPopularityClicked
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnRatingAverageClicked
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnSearchClicked
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnViewModelInit
import com.jet.restaurant.domain.model.Restaurant
import com.jet.restaurant.presentation.mapper.mapToRestaurantUi
import com.jet.restaurant.presentation.model.RestaurantUiModel
import com.jet.search.presentation.SearchLauncher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val getDefaultRestaurantsUseCase: GetDefaultRestaurantsUseCase,
    private val getSortedRestaurants: GetSortedRestaurants,
    private val resourceProvider: ResourceProvider,
    private val navigator: Navigator,
    private val searchLauncher: SearchLauncher,
) : BaseViewModel<RestaurantContract.Event, RestaurantContract.State, RestaurantContract.Effect>() {

    private lateinit var restaurantList: List<Restaurant>
    override fun provideInitialState() = RestaurantContract.State()

    override fun handleEvent(event: RestaurantContract.Event) {
        when (event) {
            OnViewModelInit -> getDefaultRestaurants()
            OnAverageProductPriceClicked -> getSortedRestaurants(AverageProductPrice)
            OnBestMatchClicked -> getSortedRestaurants(BestMatch)
            OnDeliveryCostClicked -> getSortedRestaurants(DeliveryCost)
            OnDistanceClicked -> getSortedRestaurants(Distance)
            OnMinCostClicked -> getSortedRestaurants(MinCost)
            OnNewestClicked -> getSortedRestaurants(Newest)
            OnPopularityClicked -> getSortedRestaurants(Popularity)
            OnRatingAverageClicked -> getSortedRestaurants(RatingAverage)
            OnSearchClicked -> navigator.navigate(searchLauncher.route())
        }
    }

    private fun getSortedRestaurants(sortingType: SortingType) {
        if (!::restaurantList.isInitialized) {
            sendEffect(UnknownErrorEffect(resourceProvider.getString(no_restaurant)))
            return
        }

        viewModelScope.launch {
            getSortedRestaurants(
                restaurantList,
                sortingType
            ).catch {
                sendEffect(UnknownErrorEffect(resourceProvider.getString(string.unknown_error)))
            }.collect { output ->
                when (output) {
                    is Output.Success -> updateState {
                        copy(
                            restaurantList = getRestaurantUiList(output.result),
                            selectedSortingType = sortingType
                        )
                    }
                    else -> {
                        // do nothing
                    }
                }
            }
        }
    }

    private fun getDefaultRestaurants() {
        viewModelScope.launch {
            getDefaultRestaurantsUseCase().collect { output ->
                when (output) {
                    is Output.Success -> {
                        restaurantList = output.result
                        updateState {
                            copy(
                                restaurantList = getRestaurantUiList(output.result),
                            )
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

    private fun getRestaurantUiList(list: List<Restaurant>): List<RestaurantUiModel> =
        list.map { restaurant ->
            restaurant.mapToRestaurantUi(resourceProvider)
        }

    init {
        onUiEvent(OnViewModelInit)
    }
}
