package com.jet.feature.restaurant.data.mapper

import com.jet.feature.restaurant.data.dto.RestaurantDto
import com.jet.feature.restaurant.data.mapper.Status.CLOSED
import com.jet.feature.restaurant.data.mapper.Status.OPEN
import com.jet.feature.restaurant.data.mapper.Status.ORDER_AHEAD
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
                OPEN -> Open()
                ORDER_AHEAD -> OrderAhead()
                CLOSED -> Closed()
                else -> Unknown()
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

internal object Status {
    internal const val OPEN = "open"
    internal const val ORDER_AHEAD = "order ahead"
    internal const val CLOSED = "closed"
}
