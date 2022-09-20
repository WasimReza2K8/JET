package com.jet.feature.restaurant.presentation.mapper

import com.example.core.resProvider.ResourceProvider
import com.jet.feature.restaurant.R
import com.jet.feature.restaurant.presentation.model.RestaurantUiModel
import com.jet.feature.restaurant.presentation.util.Util.centToEuro
import com.jet.feature.restaurant.presentation.util.Util.getStringHavingPostfix
import com.jet.feature.restaurant.presentation.util.Util.getStringHavingPrefixAndPostfix
import com.jet.feature.restaurant.presentation.util.Util.round
import com.jet.restaurant.domain.model.Restaurant

fun Restaurant.mapToRestaurantUi(resourceProvider: ResourceProvider) =
    RestaurantUiModel(
        id = id,
        name = name,
        status = status.value,
        sortingValues = RestaurantUiModel.SortingValues(
            bestMatch = sortingValues.bestMatch,
            newest = sortingValues.newest,
            averageProductPrice = getStringHavingPrefixAndPostfix(
                resourceProvider.getString(R.string.restaurant_avg),
                centToEuro(sortingValues.averageProductPrice),
                resourceProvider.getString(R.string.restaurant_euro),
            ),
            minCost = getStringHavingPrefixAndPostfix(
                resourceProvider.getString(R.string.restaurant_min),
                centToEuro(sortingValues.minCost),
                resourceProvider.getString(R.string.restaurant_euro),
            ),

            deliveryCosts = getStringHavingPostfix(
                centToEuro(sortingValues.deliveryCosts),
                resourceProvider.getString(R.string.restaurant_euro),
            ),
            distance = getStringHavingPostfix(
                (sortingValues.distance.toDouble() / 1000).round(),
                resourceProvider.getString(R.string.restaurant_kilo_meter)
            ),
            ratingAverage = sortingValues.ratingAverage,
            popularity = sortingValues.popularity,
        )
    )


