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

package com.jet.feature.restaurant.domain.usecase

import com.example.core.dispatcher.BaseDispatcherProvider
import com.example.core.ext.isNetworkException
import com.example.core.state.Output
import com.example.core.state.Output.NetworkError
import com.example.core.state.Output.UnknownError
import com.jet.feature.restaurant.domain.model.SortingType.BestMatch
import com.jet.restaurant.domain.model.Restaurant
import com.jet.restaurant.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDefaultRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantRepository,
    private val getSortedRestaurants: GetSortedRestaurants,
    private val dispatcherProvider: BaseDispatcherProvider,
) {
    operator fun invoke(): Flow<Output<List<Restaurant>>> =
        repository.getRestaurants()
            .flatMapMerge { restaurant ->
                sorting(restaurant)
            }.catch { error ->
                if (error.isNetworkException()) {
                    emit(NetworkError)
                } else {
                    emit(UnknownError)
                }
            }.flowOn(dispatcherProvider.io())

    private fun sorting(restaurantList: List<Restaurant>): Flow<Output<List<Restaurant>>> =
        getSortedRestaurants(restaurantList, BestMatch)

}
