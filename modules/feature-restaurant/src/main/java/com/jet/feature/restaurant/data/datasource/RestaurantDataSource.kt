package com.jet.feature.restaurant.data.datasource

import android.content.Context
import com.jet.feature.restaurant.data.dto.RestaurantsDto
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class RestaurantDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun getRestaurants(): Flow<RestaurantsDto> =
        flow {
            val json = context.assets
                .open("sample.json")
                .bufferedReader()
                .use { it.readText() }
            emit(Json.decodeFromString(json))
        }
}