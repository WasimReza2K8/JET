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

object RestaurantContract {
    data class State(
        val text: String = "", // Remove if not used
    ) : ViewState

    sealed interface Event : ViewEvent {
        object OnViewModelInit : Event // Remove if not used
        object OnBackButtonClicked : Event // Remove if not used
    }

    sealed interface Effect : ViewEffect {
        data class NetworkErrorEffect(val message: String) : Effect // Remove if not used
        data class UnknownErrorEffect(val message: String) : Effect // Remove if not used
    }
}
