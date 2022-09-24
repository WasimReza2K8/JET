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

import com.example.core.viewmodel.ViewEffect
import com.example.core.viewmodel.ViewEvent
import com.example.core.viewmodel.ViewState
import com.jet.restaurant.presentation.model.RestaurantUiModel

object SearchContract {
    data class State(
        val query: String = "",
        val noResultFoundText: String = "",
        val restaurants: List<RestaurantUiModel> = emptyList(),
    ) : ViewState

    sealed interface Event : ViewEvent {
        object OnBackButtonClicked : Event
        data class OnSearch(val query: String) : Event
        object OnQueryClearClicked : Event
        object OnInitViewModel : Event
    }

    sealed interface Effect : ViewEffect {
        data class NetworkErrorEffect(val message: String) : Effect
        data class UnknownErrorEffect(val message: String) : Effect
    }
}
