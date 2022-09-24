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

import com.example.core.navigation.Navigator
import com.example.core.resProvider.ResourceProvider
import com.example.core.state.Output
import com.example.core.state.Output.NetworkError
import com.example.core.state.Output.UnknownError
import com.jet.feature.search.domain.usecase.SearchUseCase
import com.jet.feature.search.presentation.viewmodel.SearchContract.Effect.NetworkErrorEffect
import com.jet.feature.search.presentation.viewmodel.SearchContract.Effect.UnknownErrorEffect
import com.jet.feature.search.presentation.viewmodel.SearchContract.Event.OnSearch
import com.jet.feature.search.utils.restaurantsForUi
import com.jet.feature.search.utils.restaurantsForViewModel
import com.jet.restaurant.domain.model.Restaurant
import com.jet.restaurant.presentation.model.RestaurantUiModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.Before
import org.junit.Test

class SearchViewModelTest {
    private val searchUseCase: SearchUseCase = mockk()
    private val resourceProvider: ResourceProvider = mockk {
        every {
            getString(any())
        } returns "mock"
    }
    private val navigator: Navigator = mockk()
    private lateinit var viewModel: SearchViewModel

    private fun createViewModel() {
        viewModel = SearchViewModel(
            searchUseCase,
            resourceProvider = resourceProvider,
            navigator = navigator
        )
    }

    private lateinit var testDispatcher: TestDispatcher

    @Before
    fun setUp() {
        testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        createViewModel()
    }

    @Test
    fun `Given valid query when onSearch starts return no restaurants`() =
        runTest {
            val expected = emptyList<RestaurantUiModel>()
            val given = Output.Success(emptyList<Restaurant>())

            coEvery {
                searchUseCase("de")
            } returns flow { emit(given) }

            viewModel.onUiEvent(OnSearch("de"))
            advanceTimeBy(1000)
            assertEquals(expected, viewModel.viewState.value.restaurants)
        }

    @Test
    fun `Given valid query when onSearch starts return no restaurants text not empty`() =
        runTest {
            val given = Output.Success(emptyList<Restaurant>())

            coEvery {
                searchUseCase("de")
            } returns flow { emit(given) }

            viewModel.onUiEvent(OnSearch("de"))
            advanceTimeBy(1000)
            assertThat(viewModel.viewState.value.noResultFoundText).isNotEmpty()
        }

    @Test
    fun `Given valid query when onSearch starts return valid list of restaurants`() =
        runTest {
            val expected = restaurantsForUi
            val given = Output.Success(restaurantsForViewModel)
            coEvery {
                searchUseCase("de")
            } returns flow { emit(given) }

            viewModel.onUiEvent(OnSearch("de"))
            advanceTimeBy(1000)
            assertEquals(expected, viewModel.viewState.value.restaurants)
        }

    @Test
    fun `Given double query when onSearch use case called single time`() =
        runTest {
            val given = Output.Success(restaurantsForViewModel)
            coEvery {
                searchUseCase("dev")
            } returns flow { emit(given) }

            viewModel.onUiEvent(OnSearch("d"))
            viewModel.onUiEvent(OnSearch("de"))
            viewModel.onUiEvent(OnSearch("dev"))
            advanceTimeBy(1000)
            verify(exactly = 1) { searchUseCase("dev") }
        }

    @Test
    fun `Given empty query when onSearch use case not called`() =
        runTest {
            initGettingEmptyQuery()
            verify(exactly = 0) { searchUseCase("") }
        }

    @Test
    fun `Given empty query when onSearch view state emit with empty list`() =
        runTest {
            initGettingEmptyQuery()
            assertThat(viewModel.viewState.value.restaurants.isEmpty()).isTrue
        }

    @Test
    fun `Given network error when onSearch starts return network error effect`() =
        runTest {
            val expected = NetworkErrorEffect("mock")
            val given = NetworkError
            coEvery {
                searchUseCase("de")
            } returns flow { emit(given) }

            viewModel.onUiEvent(OnSearch("de"))
            advanceTimeBy(1000)
            viewModel.effect.take(1).collectLatest {
                assertEquals(expected, it)
            }
        }

    @Test
    fun `Given unknown error when onSearch starts return unknown error effect`() =
        runTest {
            val expected = UnknownErrorEffect("mock")
            val given = UnknownError
            coEvery {
                searchUseCase("de")
            } returns flow { emit(given) }

            viewModel.onUiEvent(OnSearch("de"))
            advanceTimeBy(1000)
            viewModel.effect.take(1).collectLatest {
                assertEquals(expected, it)
            }
        }

    private fun initGettingEmptyQuery() =
        runTest {
            val given = Output.Success(restaurantsForViewModel)
            coEvery {
                searchUseCase("")
            } returns flow { emit(given) }

            viewModel.onUiEvent(OnSearch(""))
            advanceTimeBy(1000)
        }

    @org.junit.After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
