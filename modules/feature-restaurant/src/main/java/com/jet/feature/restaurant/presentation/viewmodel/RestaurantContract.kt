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

import com.example.core.viewmodel.ViewEffect
import com.example.core.viewmodel.ViewEvent
import com.example.core.viewmodel.ViewState
import com.jet.feature.restaurant.domain.model.SortingType
import com.jet.feature.restaurant.domain.model.SortingType.BestMatch
import com.jet.restaurant.presentation.model.RestaurantUiModel

object RestaurantContract {
    data class State(
        val restaurantList: List<RestaurantUiModel> = emptyList(),
        val selectedSortingType: SortingType = BestMatch,
    ) : ViewState

    sealed interface Event : ViewEvent {
        object OnViewModelInit : Event
        object OnBestMatchClicked : Event
        object OnRatingAverageClicked : Event
        object OnDistanceClicked : Event
        object OnPopularityClicked : Event
        object OnMinCostClicked : Event
        object OnDeliveryCostClicked : Event
        object OnAverageProductPriceClicked : Event
        object OnNewestClicked : Event
        object OnSearchClicked : Event
    }

    sealed interface Effect : ViewEffect {
        data class NetworkErrorEffect(val message: String) : Effect
        data class UnknownErrorEffect(val message: String) : Effect
    }
}
