package com.jet.feature.restaurant.presentation.model

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
        val distance: String,
        val popularity: Double,
        val averageProductPrice: String,
        val deliveryCosts: String,
        val minCost: String,
    )
}
