package com.jet.feature.restaurant.data.repository

import app.cash.turbine.test
import com.jet.feature.restaurant.data.datasource.RestaurantDataSource
import com.jet.feature.restaurant.data.dto.RestaurantsDto
import com.jet.feature.restaurant.utils.restaurant0
import com.jet.feature.restaurant.utils.restaurant3
import com.jet.feature.restaurant.utils.restaurantDto
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

internal class RestaurantRepositoryImplTest {
    private val dataSource: RestaurantDataSource = mockk()
    private val repository = RestaurantRepositoryImpl(dataSource = dataSource)

    @Test
    fun `Given valid dto When getRestaurants called Then emit valid restaurant list`() =
        runTest {
            // Given
            val providedData = RestaurantsDto(restaurantDto)
            val expectedData = listOf(
                restaurant0,
                restaurant3,
            )
            coEvery {
                dataSource.getRestaurants()
            } returns flow { emit(providedData) }
            // When
            repository.getRestaurants().test {
                // Then
                assertEquals(expectedData, awaitItem())
                awaitComplete()
            }
        }

    @Test
    fun `Given IoException When getRestaurants called Then emit exception`() =
        runTest {
            // Given
            val providedData = IOException()
            coEvery {
                dataSource.getRestaurants()
            } returns flow { throw providedData }

            // When
            repository.getRestaurants().test {
                // Then
                assertEquals(providedData, awaitError())
            }
        }
}