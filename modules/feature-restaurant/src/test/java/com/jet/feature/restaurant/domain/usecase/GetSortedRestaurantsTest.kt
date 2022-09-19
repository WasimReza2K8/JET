package com.jet.feature.restaurant.domain.usecase

import app.cash.turbine.test
import com.example.core.state.Output
import com.jet.feature.restaurant.domain.model.SortingType.AverageProductPrice
import com.jet.feature.restaurant.domain.model.SortingType.BestMatch
import com.jet.feature.restaurant.domain.model.SortingType.DeliveryCost
import com.jet.feature.restaurant.domain.model.SortingType.Distance
import com.jet.feature.restaurant.domain.model.SortingType.MinCost
import com.jet.feature.restaurant.domain.model.SortingType.Newest
import com.jet.feature.restaurant.domain.model.SortingType.Popularity
import com.jet.feature.restaurant.domain.model.SortingType.RatingAverage
import com.jet.feature.restaurant.utils.restaurant0
import com.jet.feature.restaurant.utils.restaurant1
import com.jet.feature.restaurant.utils.restaurant2
import com.jet.feature.restaurant.utils.restaurant3
import com.jet.feature.restaurant.utils.restaurant4
import com.jet.feature.restaurant.utils.restaurant5
import com.jet.feature.restaurant.utils.restaurants
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest

import org.junit.Test

internal class GetSortedRestaurantsTest {

    @Test
    fun `Given restaurantList and bestMatch sorting type when invoked return flow of correct list`() = runTest {
        val given = restaurants
        val expected = Output.Success(
            listOf(
                restaurant3,
                restaurant0,
                restaurant2,
                restaurant5,
                restaurant4,
                restaurant1
            )
        )

        GetSortedRestaurants().invoke(given, BestMatch).test {
            val result = awaitItem()
            assertEquals(expected, result)
            awaitComplete()
        }
    }

    @Test
    fun `Given restaurantList and newest sorting type when invoked return flow of correct list`() = runTest {
        val given = restaurants
        val expected = Output.Success(
            listOf(
                restaurant0,
                restaurant3,
                restaurant5,
                restaurant2,
                restaurant4,
                restaurant1
            )
        )

        GetSortedRestaurants().invoke(given, Newest).test {
            val result = awaitItem()
            assertEquals(expected, result)
            awaitComplete()
        }
    }

    @Test
    fun `Given restaurantList and avgProductPrice sorting type when invoked return flow of correct list`() = runTest {
        val given = restaurants
        val expected = Output.Success(
            listOf(
                restaurant3,
                restaurant0,
                restaurant5,
                restaurant2,
                restaurant4,
                restaurant1
            )
        )

        GetSortedRestaurants().invoke(given, AverageProductPrice).test {
            val result = awaitItem()
            assertEquals(expected, result)
            awaitComplete()
        }
    }

    //
    @Test
    fun `Given restaurantList and minCost sorting type when invoked return flow of correct list`() = runTest {
        val given = restaurants
        val expected = Output.Success(
            listOf(
                restaurant0,
                restaurant3,
                restaurant2,
                restaurant5,
                restaurant1,
                restaurant4,
            )
        )

        GetSortedRestaurants().invoke(given, MinCost).test {
            val result = awaitItem()
            assertEquals(expected, result)
            awaitComplete()
        }
    }

    @Test
    fun `Given restaurantList and deliveryCost sorting type when invoked return flow of correct list`() = runTest {
        val given = restaurants
        val expected = Output.Success(
            listOf(
                restaurant0,
                restaurant3,
                restaurant5,
                restaurant2,
                restaurant1,
                restaurant4,
            )
        )

        GetSortedRestaurants().invoke(given, DeliveryCost).test {
            val result = awaitItem()
            assertEquals(expected, result)
            awaitComplete()
        }
    }

    @Test
    fun `Given restaurantList and ratingAverage sorting type when invoked return flow of correct list`() = runTest {
        val given = restaurants
        val expected = Output.Success(
            listOf(
                restaurant0,
                restaurant3,
                restaurant5,
                restaurant2,
                restaurant1,
                restaurant4,
            )
        )

        GetSortedRestaurants().invoke(given, RatingAverage).test {
            val result = awaitItem()
            assertEquals(expected, result)
            awaitComplete()
        }
    }

    @Test
    fun `Given restaurantList and popularity sorting type when invoked return flow of correct list`() = runTest {
        val given = restaurants
        val expected = Output.Success(
            listOf(
                restaurant3,
                restaurant0,
                restaurant5,
                restaurant2,
                restaurant4,
                restaurant1,
            )
        )

        GetSortedRestaurants().invoke(given, Popularity).test {
            val result = awaitItem()
            assertEquals(expected, result)
            awaitComplete()
        }
    }

    @Test
    fun `Given restaurantList and distance sorting type when invoked return flow of correct list`() = runTest {
        val given = restaurants
        val expected = Output.Success(
            listOf(
                restaurant0,
                restaurant3,
                restaurant5,
                restaurant2,
                restaurant1,
                restaurant4,
            )
        )

        GetSortedRestaurants().invoke(given, Distance).test {
            val result = awaitItem()
            assertEquals(expected, result)
            awaitComplete()
        }
    }
}