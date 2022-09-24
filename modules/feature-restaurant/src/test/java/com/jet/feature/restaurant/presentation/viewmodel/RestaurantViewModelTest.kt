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

import com.example.core.navigation.Navigator
import com.example.core.resProvider.ResourceProvider
import com.example.core.state.Output
import com.example.core.ui.feature.FeatureProvider
import com.jet.feature.restaurant.domain.model.SortingType.Newest
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
import com.jet.feature.restaurant.utils.restaurantsForUViewModelSorted
import com.jet.feature.restaurant.utils.restaurantsForUi
import com.jet.feature.restaurant.utils.restaurantsForUiSorted
import com.jet.feature.restaurant.utils.restaurantsForViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class RestaurantViewModelTest {
    private val getDefaultRestaurantsUseCase: GetDefaultRestaurantsUseCase = mockk(relaxed = true)
    private val getSortedRestaurants: GetSortedRestaurants = mockk(relaxed = true)
    private val featureProvider: FeatureProvider = mockk(relaxed = true)
    private val resourceProvider: ResourceProvider = mockk {
        every {
            getString(any())
        } returns "mock"
    }
    private val navigator: Navigator = mockk(relaxed = true)
    private lateinit var viewModel: RestaurantViewModel

    private lateinit var testDispatcher: TestDispatcher

    private fun createViewModel() {
        viewModel = RestaurantViewModel(
            getDefaultRestaurantsUseCase = getDefaultRestaurantsUseCase,
            getSortedRestaurants = getSortedRestaurants,
            resourceProvider = resourceProvider,
            navigator = navigator,
            featureProvider = featureProvider,
        )
    }

    @Before
    fun setUp() {
        testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `Given valid restaurantList when view model init the viewState contains valid state`() =
        runTest {
            val expected = restaurantsForUi
            val given = Output.Success(restaurantsForViewModel)
            coEvery {
                getDefaultRestaurantsUseCase()
            } returns flow { emit(given) }

            createViewModel()

            assertEquals(expected, viewModel.viewState.value.restaurantList)
        }

    @Test
    fun `Given network error when view model init the effect contains network error`() =
        runTest {
            val expected = NetworkErrorEffect("mock")
            coEvery {
                getDefaultRestaurantsUseCase()
            } returns flow { emit(Output.NetworkError) }

            createViewModel()

            viewModel.effect.take(1).collectLatest {
                assertEquals(expected, it)
            }
        }

    @Test
    fun `Given unknown error when view model init the effect contains unknown error`() =
        runTest {
            val expected = UnknownErrorEffect("mock")
            coEvery {
                getDefaultRestaurantsUseCase()
            } returns flow { emit(Output.UnknownError) }

            createViewModel()

            viewModel.effect.take(1).collectLatest {
                assertEquals(expected, it)
            }
        }

    @Test
    fun `Given valid restaurantList when view model bestMatch clicked the viewState contains valid state`() =
        runTest {
            val expected = restaurantsForUiSorted
            initMock()

            viewModel.onUiEvent(OnBestMatchClicked)

            assertEquals(expected, viewModel.viewState.value.restaurantList)
        }

    @Test
    fun `Given valid restaurantList when view model avgProductPrice clicked the viewState contains valid state`() =
        runTest {
            val expected = restaurantsForUiSorted
            initMock()

            viewModel.onUiEvent(OnAverageProductPriceClicked)

            assertEquals(expected, viewModel.viewState.value.restaurantList)
        }

    @Test
    fun `Given valid restaurantList when view model popularity clicked the viewState contains valid state`() =
        runTest {
            val expected = restaurantsForUiSorted
            initMock()
            viewModel.onUiEvent(OnPopularityClicked)

            assertEquals(expected, viewModel.viewState.value.restaurantList)
        }

    @Test
    fun `Given valid restaurantList when view model ratingAvg clicked the viewState contains valid state`() =
        runTest {
            val expected = restaurantsForUiSorted
            initMock()

            viewModel.onUiEvent(OnRatingAverageClicked)

            assertEquals(expected, viewModel.viewState.value.restaurantList)
        }

    @Test
    fun `Given valid restaurantList when view model distance clicked the viewState contains valid state`() =
        runTest {
            val expected = restaurantsForUiSorted
            initMock()

            viewModel.onUiEvent(OnDistanceClicked)

            assertEquals(expected, viewModel.viewState.value.restaurantList)
        }

    @Test
    fun `Given valid restaurantList when view model minCost clicked the viewState contains valid state`() =
        runTest {
            val expected = restaurantsForUiSorted
            initMock()

            viewModel.onUiEvent(OnMinCostClicked)

            assertEquals(expected, viewModel.viewState.value.restaurantList)
        }

    @Test
    fun `Given valid restaurantList when view model deliveryCost clicked the viewState contains valid state`() =
        runTest {
            val expected = restaurantsForUiSorted
            initMock()

            viewModel.onUiEvent(OnDeliveryCostClicked)

            assertEquals(expected, viewModel.viewState.value.restaurantList)
        }

    @Test
    fun `Given valid restaurantList when view model newest clicked the viewState contains valid state`() =
        runTest {
            val expected = restaurantsForUiSorted
            initMock()

            viewModel.onUiEvent(OnNewestClicked)

            assertEquals(expected, viewModel.viewState.value.restaurantList)
        }

    @Test
    fun `Given valid restaurantList when view model newest clicked the viewState contains valid sorting type`() =
        runTest {
            val expected = Newest
            initMock()

            viewModel.onUiEvent(OnNewestClicked)

            assertEquals(expected, viewModel.viewState.value.selectedSortingType)
        }

    @Test
    fun `Given empty restaurantList when view model newest clicked the viewState no restaurant found effect`() =
        runTest {
            coEvery {
                getDefaultRestaurantsUseCase()
            } returns flow { emit(Output.NetworkError) }

            createViewModel()


            viewModel.onUiEvent(OnNewestClicked)

            val effectList = mutableListOf<RestaurantContract.Effect>()

            viewModel.effect
                .onCompletion {
                    assertThat(effectList.last() is UnknownErrorEffect).isTrue
                }
                .take(2)
                .collectLatest {
                    effectList.add(it)
                }
        }

    @Test
    fun `Given error when view model newest clicked the unknown error effect send`() =
        runTest {
            val error = NullPointerException()
            val expected = UnknownErrorEffect("mock")
            initMock()
            coEvery {
                getSortedRestaurants(any(), any())
            } returns flow { throw error }

            viewModel.onUiEvent(OnNewestClicked)

            viewModel.effect.take(1).collectLatest {
                assertEquals(expected, it)
            }
        }

    private fun initMock() = runTest {
        val given = Output.Success(restaurantsForViewModel)
        val sortedOutput = Output.Success(restaurantsForUViewModelSorted)
        coEvery {
            getDefaultRestaurantsUseCase()
        } returns flow { emit(given) }

        createViewModel()

        coEvery {
            getSortedRestaurants(any(), any())
        } returns flow { emit(sortedOutput) }
    }

    @org.junit.After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
