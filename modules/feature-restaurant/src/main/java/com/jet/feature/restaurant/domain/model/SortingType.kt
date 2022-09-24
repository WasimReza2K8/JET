package com.jet.feature.restaurant.domain.model

import com.jet.feature.restaurant.R.string

sealed class SortingType(val id: Int) {
    object BestMatch : SortingType(string.restaurant_best_match)

    object MinCost : SortingType(string.restaurant_min_cost)

    object DeliveryCost : SortingType(string.restaurant_delivery_cost)

    object RatingAverage : SortingType(string.restaurant_rating_avg)

    object Distance : SortingType(string.restaurant_distance)

    object Popularity : SortingType(string.restaurant_popularity)

    object AverageProductPrice : SortingType(string.restaurant_avg_price)

    object Newest : SortingType(string.restaurant_newest)
}
