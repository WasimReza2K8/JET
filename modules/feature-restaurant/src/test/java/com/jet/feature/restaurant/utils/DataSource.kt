package com.jet.feature.restaurant.utils

import com.jet.feature.restaurant.data.dto.RestaurantDto
import com.jet.restaurant.presentation.model.RestaurantUiModel
import com.jet.restaurant.domain.model.Restaurant
import com.jet.restaurant.domain.model.Restaurant.SortingValues
import com.jet.restaurant.domain.model.Status.Closed
import com.jet.restaurant.domain.model.Status.Open
import com.jet.restaurant.domain.model.Status.OrderAhead

val restaurant0 =
    Restaurant(
        id = "1",
        name = "Sushi Bar",
        status = Open,
        sortingValues = SortingValues(
            bestMatch = 0.0,
            newest = 96.0,
            ratingAverage = 4.5,
            distance = 1190,
            popularity = 17.0,
            averageProductPrice = 1536,
            deliveryCosts = 200,
            minCost = 1000
        )
    )

val restaurant3 =
    restaurant0.copy(
        status = Open,
        sortingValues = SortingValues(
            bestMatch = 1232.0,
            newest = 63.0,
            ratingAverage = 3.5,
            distance = 2199,
            popularity = 1269.0,
            averageProductPrice = 1139,
            deliveryCosts = 230,
            minCost = 1501
        )
    )

val restaurant2 =
    restaurant0.copy(
        status = OrderAhead,
        sortingValues = SortingValues(
            bestMatch = 1222.0,
            newest = 93.0,
            ratingAverage = 3.0,
            distance = 1199,
            popularity = 1212.0,
            averageProductPrice = 1639,
            deliveryCosts = 250,
            minCost = 1001
        )
    )
val restaurant5 =
    restaurant0.copy(
        status = OrderAhead,
        sortingValues = SortingValues(
            bestMatch = 220.0,
            newest = 193.0,
            ratingAverage = 5.0,
            distance = 999,
            popularity = 3512.0,
            averageProductPrice = 1339,
            deliveryCosts = 190,
            minCost = 1301
        )
    )

val restaurant1 =
    restaurant0.copy(
        status = Closed,
        sortingValues = SortingValues(
            bestMatch = 12.0,
            newest = 9.0,
            ratingAverage = 4.0,
            distance = 119,
            popularity = 12.0,
            averageProductPrice = 1636,
            deliveryCosts = 210,
            minCost = 100
        )
    )

val restaurant4 =
    restaurant3.copy(
        status = Closed,
        sortingValues = SortingValues(
            bestMatch = 1220.0,
            newest = 93.0,
            ratingAverage = 3.7,
            distance = 1199,
            popularity = 1512.0,
            averageProductPrice = 1039,
            deliveryCosts = 260,
            minCost = 1201
        )
    )

val restaurantDto0 =
    RestaurantDto(
        id = "1",
        name = "Sushi Bar",
        status = "open",
        sortingValues = RestaurantDto.SortingValues(
            bestMatch = 0.0,
            newest = 96.0,
            ratingAverage = 4.5,
            distance = 1190,
            popularity = 17.0,
            averageProductPrice = 1536,
            deliveryCosts = 200,
            minCost = 1000
        )
    )
val restaurantDto1 =
    restaurantDto0.copy(
        sortingValues = RestaurantDto.SortingValues(
            bestMatch = 1232.0,
            newest = 63.0,
            ratingAverage = 3.5,
            distance = 2199,
            popularity = 1269.0,
            averageProductPrice = 1139,
            deliveryCosts = 230,
            minCost = 1501
        )
    )

val restaurantDto =
    listOf(
        restaurantDto0,
        restaurantDto1,
    )

val restaurants =
    listOf(
        restaurant0,
        restaurant1,
        restaurant2,
        restaurant3,
        restaurant4,
        restaurant5
    )

val restaurantsForViewModel =
    listOf(
        restaurant1,
        restaurant0,
    )

val restaurantsForUViewModelSorted =  listOf(
    restaurant0,
    restaurant1,
)

private val restaurantForUi0 =
    RestaurantUiModel(
        id = "1",
        name = "Sushi Bar",
        status = Open.value,
        sortingValues = RestaurantUiModel.SortingValues(
            bestMatch = 0.0,
            newest = 96.0,
            ratingAverage = 4.5,
            distance = "1.19mock",
            popularity = 17.0,
            averageProductPrice = "mock 15.36mock",
            deliveryCosts = "2mock",
            minCost = "mock 10mock"
        )
    )

private val restaurantForUi1 =
    restaurantForUi0.copy(
        status = Closed.value,
        sortingValues = RestaurantUiModel.SortingValues(
            bestMatch = 12.0,
            newest = 9.0,
            ratingAverage = 4.0,
            distance = "0.12mock",
            popularity = 12.0,
            averageProductPrice = "mock 16.36mock",
            deliveryCosts = "2.1mock",
            minCost = "mock 1mock"
        )
    )

val restaurantsForUi = listOf(
    restaurantForUi1,
    restaurantForUi0,
)

val restaurantsForUiSorted = listOf(
    restaurantForUi0,
    restaurantForUi1,
)
