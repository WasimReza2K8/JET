package com.jet.feature.restaurant.domain.usecase

import com.example.core.state.Output
import com.jet.feature.restaurant.domain.model.SortingType
import com.jet.feature.restaurant.domain.model.SortingType.AverageProductPrice
import com.jet.feature.restaurant.domain.model.SortingType.BestMatch
import com.jet.feature.restaurant.domain.model.SortingType.DeliveryCost
import com.jet.feature.restaurant.domain.model.SortingType.Distance
import com.jet.feature.restaurant.domain.model.SortingType.MinCost
import com.jet.feature.restaurant.domain.model.SortingType.Newest
import com.jet.feature.restaurant.domain.model.SortingType.Popularity
import com.jet.feature.restaurant.domain.model.SortingType.RatingAverage
import com.jet.restaurant.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSortedRestaurants @Inject constructor() {
    operator fun invoke(
        restaurants: List<Restaurant>,
        sortingType: SortingType,
    ): Flow<Output<List<Restaurant>>> = flow {
        val sortedRestaurants: List<Restaurant>
        when (sortingType) {
            Newest -> {
                sortedRestaurants = restaurants.sortedWith(
                    compareBy<Restaurant> { it.status.priority }
                        .thenByDescending { it.sortingValues.newest }
                )
            }
            BestMatch -> {
                sortedRestaurants = restaurants.sortedWith(
                    compareBy<Restaurant> { it.status.priority }
                        .thenByDescending { it.sortingValues.bestMatch }
                )
            }
            MinCost -> {
                sortedRestaurants = restaurants.sortedWith(
                    compareBy({ it.status.priority }, { it.sortingValues.minCost })
                )
            }
            DeliveryCost -> {
                sortedRestaurants = restaurants.sortedWith(
                    compareBy({ it.status.priority }, { it.sortingValues.deliveryCosts })
                )
            }
            AverageProductPrice -> {
                sortedRestaurants = restaurants.sortedWith(
                    compareBy({ it.status.priority }, { it.sortingValues.averageProductPrice })
                )
            }
            Distance -> {
                sortedRestaurants = restaurants.sortedWith(
                    compareBy({ it.status.priority }, { it.sortingValues.distance })
                )
            }
            RatingAverage -> {
                sortedRestaurants = restaurants.sortedWith(
                    compareBy<Restaurant> { it.status.priority }
                        .thenByDescending { it.sortingValues.ratingAverage }
                )
            }
            Popularity -> {
                sortedRestaurants = restaurants.sortedWith(
                    compareBy<Restaurant> { it.status.priority }
                        .thenByDescending { it.sortingValues.popularity }
                )
            }
        }
        emit(Output.Success(sortedRestaurants))
    }
}

