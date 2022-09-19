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

package com.jet.feature.restaurant.data.repository

import com.jet.feature.restaurant.data.datasource.RestaurantDataSource
import com.jet.feature.restaurant.data.mapper.mapToRestaurant
import com.jet.restaurant.domain.model.Restaurant
import com.jet.restaurant.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val dataSource: RestaurantDataSource,
) : RestaurantRepository {
    override fun getRestaurants(): Flow<List<Restaurant>> =
        dataSource.getRestaurants().map { restaurantsDto ->
            restaurantsDto.restaurants.map { item ->
                item.mapToRestaurant()
            }
        }
}

