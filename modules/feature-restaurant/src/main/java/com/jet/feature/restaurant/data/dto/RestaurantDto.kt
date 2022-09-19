package com.jet.feature.restaurant.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RestaurantDto(
    val id: String,
    val name: String,
    val status: String,
    val sortingValues: SortingValues,
) {
    @Serializable
    data class SortingValues(
        val bestMatch: Double,
        val newest: Double,
        val ratingAverage: Double,
        val distance: Int,
        val popularity: Double,
        val averageProductPrice: Int,
        val deliveryCosts: Int,
        val minCost: Int,
    )
}
