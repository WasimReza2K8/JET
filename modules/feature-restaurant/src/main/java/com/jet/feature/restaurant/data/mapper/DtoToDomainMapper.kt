package com.jet.feature.restaurant.data.mapper

import com.jet.feature.restaurant.data.dto.RestaurantDto
import com.jet.restaurant.domain.model.Restaurant
import com.jet.restaurant.domain.model.Restaurant.SortingValues
import com.jet.restaurant.domain.model.Status.Closed
import com.jet.restaurant.domain.model.Status.Open
import com.jet.restaurant.domain.model.Status.OrderAhead
import com.jet.restaurant.domain.model.Status.Unknown

fun RestaurantDto.mapToRestaurant(): Restaurant =
    Restaurant(
        id = id,
        name = name,
        status = status.let {
            when (it) {
                Open.value -> Open
                OrderAhead.value -> OrderAhead
                Closed.value -> Closed
                else -> Unknown
            }
        },
        sortingValues = SortingValues(
            bestMatch = sortingValues.bestMatch,
            newest = sortingValues.newest,
            averageProductPrice = sortingValues.averageProductPrice,
            distance = sortingValues.distance,
            minCost = sortingValues.minCost,
            deliveryCosts = sortingValues.deliveryCosts,
            ratingAverage = sortingValues.ratingAverage,
            popularity = sortingValues.popularity,
        )
    )
