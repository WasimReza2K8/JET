package com.jet.feature.restaurant.presentation

data class RestaurantUiModel(
    val id: String,
    val name: String,
    val status: String,
    val sortingValues: SortingValues,
) {

    data class SortingValues(
        val bestMatch: Double,
        val newest: Double,
        val ratingAverage: Double,
        val distance: Double,
        val popularity: Double,
        val averageProductPrice: Double,
        val deliveryCosts: Double,
        val minCost: Double,
    )
}
