package com.jet.feature.restaurant.domain.usecase

import app.cash.turbine.test
import com.example.core.dispatcher.BaseDispatcherProvider
import com.example.core.state.Output
import com.example.core.state.Output.Success
import com.example.core.state.Output.UnknownError
import com.jet.feature.restaurant.utils.TestDispatcherProvider
import com.jet.feature.restaurant.utils.restaurants
import com.jet.restaurant.domain.repository.RestaurantRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

internal class GetDefaultRestaurantsUseCaseTest {
    private val getSortedRestaurants: GetSortedRestaurants = mockk()
    private val repository: RestaurantRepository = mockk(relaxed = true)
    private val dispatcherProvider: BaseDispatcherProvider = TestDispatcherProvider()
    private val useCase: GetDefaultRestaurantsUseCase = GetDefaultRestaurantsUseCase(
        repository = repository,
        dispatcherProvider = dispatcherProvider,
        getSortedRestaurants = getSortedRestaurants
    )

    @Test
    fun `Given valid restaurants When invoke called Then returns success output`() =
        runTest {
            // Given
            val expected = Success(restaurants)
            coEvery {
                repository.getRestaurants()
            } returns flow { emit(restaurants) }

            coEvery {
                getSortedRestaurants(any(), any())
            } returns flow { emit(Success(restaurants)) }

            // When
            useCase().test {
                // Then
                val result = awaitItem()
                assertEquals(expected, result)
                awaitComplete()
            }
        }

    @Test
    fun `Given error sorted restaurants When invoke called Then returns unknown output`() =
        runTest {
            // Given
            val expected = UnknownError
            coEvery {
                repository.getRestaurants()
            } returns flow { emit(restaurants) }

            coEvery {
                getSortedRestaurants(any(), any())
            } returns flow { throw NullPointerException("test") }

            // When
            useCase().test {
                // Then
                val result = awaitItem()
                assertEquals(expected, result)
                awaitComplete()
            }
        }

    @Test
    fun `Given IoException restaurants When invoke called Then returns NetworkError output`() =
        runTest {
            // Given
            val provided = IOException("test")
            val expected = Output.NetworkError
            coEvery {
                repository.getRestaurants()
            } returns flow { throw provided }

            // When
            useCase().test {
                // Then
                assertEquals(expected, awaitItem())
                awaitComplete()
            }
        }

    @Test
    fun `Given NullPointerException restaurants When invoke called Then returns UnknownError output`() =
        runTest {
            // Given
            val provided = NullPointerException("test")
            val expected = UnknownError
            coEvery {
                repository.getRestaurants()
            } returns flow { throw provided }

            // When
            useCase().test {
                // Then
                assertEquals(expected, awaitItem())
                awaitComplete()
            }
        }
}

