package com.jet.feature.restaurant.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RestaurantsDto(
    val restaurants: List<RestaurantDto>,
)
