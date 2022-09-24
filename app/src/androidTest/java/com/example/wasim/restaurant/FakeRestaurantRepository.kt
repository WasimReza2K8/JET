package com.example.wasim.restaurant

import com.example.wasim.restaurant.FakeRestaurantRepository.ReturnType.NetworkException
import com.example.wasim.restaurant.FakeRestaurantRepository.ReturnType.UnknownException
import com.example.wasim.restaurant.FakeRestaurantRepository.ReturnType.Valid
import com.jet.restaurant.domain.model.Restaurant
import com.jet.restaurant.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import restaurants
import java.io.IOException
import javax.inject.Inject

class FakeRestaurantRepository @Inject constructor() : RestaurantRepository {
    private lateinit var returnType: ReturnType
    override fun getRestaurants(): Flow<List<Restaurant>> =
        flow {
            when (returnType) {
                Valid -> emit(restaurants)
                NetworkException -> throw IOException()
                UnknownException -> throw RuntimeException()
            }
        }

    fun setReturnType(returnType: ReturnType) {
        this.returnType = returnType
    }

    enum class ReturnType {
        Valid,
        NetworkException,
        UnknownException
    }

}