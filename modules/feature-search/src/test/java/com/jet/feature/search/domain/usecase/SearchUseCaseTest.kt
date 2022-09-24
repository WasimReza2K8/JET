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

package com.jet.feature.search.domain.usecase

import app.cash.turbine.test
import com.example.core.dispatcher.BaseDispatcherProvider
import com.example.core.state.Output.NetworkError
import com.example.core.state.Output.Success
import com.example.core.state.Output.UnknownError
import com.jet.feature.search.utils.TestDispatcherProvider
import com.jet.feature.search.utils.restaurant2
import com.jet.feature.search.utils.restaurant3
import com.jet.feature.search.utils.restaurant4
import com.jet.feature.search.utils.restaurants
import com.jet.restaurant.domain.model.Restaurant
import com.jet.restaurant.domain.repository.RestaurantRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class SearchUseCaseTest {
    private val repository: RestaurantRepository = mockk(relaxed = true)
    private val dispatcherProvider: BaseDispatcherProvider = TestDispatcherProvider()
    private val useCase: SearchUseCase = SearchUseCase(
        repository = repository,
        dispatcherProvider = dispatcherProvider
    )

    @Test
    fun `Given valid query with valid response when invoked return valid list of restaurants`() =
        runTest {
            // Given
            val expected = Success(listOf(restaurant2, restaurant3, restaurant4))
            val query = "De"
            coEvery {
                repository.getRestaurants()
            } returns flow { emit(restaurants) }

            useCase.invoke(query).test {
                assertEquals(expected, awaitItem())
                awaitComplete()
            }
        }

    @Test
    fun `Given valid query with empty list when invoked return empty list of restaurants`() =
        runTest {
            // Given
            val expected = Success(emptyList<Restaurant>())
            val query = "fa"
            coEvery {
                repository.getRestaurants()
            } returns flow { emit(restaurants) }

            useCase.invoke(query).test {
                assertEquals(expected, awaitItem())
                awaitComplete()
            }
        }

    @Test
    fun `Given IOException when invoked return empty list of restaurants`() =
        runTest {
            // Given
            val expected = NetworkError
            val query = "fa"
            coEvery {
                repository.getRestaurants()
            } returns flow { throw IOException() }

            useCase.invoke(query).test {
                assertEquals(expected, awaitItem())
                awaitComplete()
            }
        }

    @Test
    fun `Given RuntimeException when invoked return empty list of restaurants`() =
        runTest {
            // Given
            val expected = UnknownError
            val query = "fa"
            coEvery {
                repository.getRestaurants()
            } returns flow { throw RuntimeException() }

            useCase.invoke(query).test {
                assertEquals(expected, awaitItem())
                awaitComplete()
            }
        }
}
