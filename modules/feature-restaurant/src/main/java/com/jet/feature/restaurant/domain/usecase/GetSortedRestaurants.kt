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
        emit(
            Output.Success(
                restaurants.sortedWith(
                    compareBy<Restaurant> { it.status.priority }
                        .then(getAdditionalComparator(sortingType))
                )
            )
        )
    }

    private fun getAdditionalComparator(sortingType: SortingType): Comparator<Restaurant> =
        when (sortingType) {
            Newest -> compareByDescending { it.sortingValues.newest }

            BestMatch -> compareByDescending { it.sortingValues.bestMatch }

            MinCost -> compareBy { it.sortingValues.minCost }

            DeliveryCost -> compareBy { it.sortingValues.deliveryCosts }

            AverageProductPrice -> compareBy { it.sortingValues.averageProductPrice }

            Distance -> compareBy { it.sortingValues.distance }

            RatingAverage -> compareByDescending { it.sortingValues.ratingAverage }

            Popularity -> compareByDescending { it.sortingValues.popularity }
        }
}
