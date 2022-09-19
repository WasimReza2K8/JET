package com.jet.feature.restaurant.domain.model

sealed interface SortingType {
    object BestMatch : SortingType
    object MinCost : SortingType
    object DeliveryCost : SortingType
    object RatingAverage : SortingType
    object Distance : SortingType
    object Popularity : SortingType
    object AverageProductPrice : SortingType
    object Newest : SortingType
}