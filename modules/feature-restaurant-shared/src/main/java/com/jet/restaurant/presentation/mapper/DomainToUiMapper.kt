package com.jet.restaurant.presentation.mapper

import com.example.core.resProvider.ResourceProvider
import com.example.core.util.Util.centToEuro
import com.example.core.util.Util.getStringHavingPostfix
import com.example.core.util.Util.getStringHavingPrefixAndPostfix
import com.example.core.util.Util.meterToKm
import com.jet.restaurant.R
import com.jet.restaurant.domain.model.Restaurant
import com.jet.restaurant.presentation.model.RestaurantUiModel

fun Restaurant.mapToRestaurantUi(resourceProvider: ResourceProvider) =
    RestaurantUiModel(
        id = id,
        name = name,
        status = status.value,
        sortingValues = RestaurantUiModel.SortingValues(
            bestMatch = sortingValues.bestMatch,
            newest = sortingValues.newest,
            averageProductPrice = getStringHavingPrefixAndPostfix(
                resourceProvider.getString(R.string.restaurant_shared_avg),
                centToEuro(sortingValues.averageProductPrice),
                resourceProvider.getString(R.string.restaurant_shared_euro),
            ),
            minCost = getStringHavingPrefixAndPostfix(
                resourceProvider.getString(R.string.restaurant_shared_min),
                centToEuro(sortingValues.minCost),
                resourceProvider.getString(R.string.restaurant_shared_euro),
            ),

            deliveryCosts = getStringHavingPostfix(
                centToEuro(sortingValues.deliveryCosts),
                resourceProvider.getString(R.string.restaurant_shared_euro),
            ),
            distance = getStringHavingPostfix(
                meterToKm(sortingValues.distance),
                resourceProvider.getString(R.string.restaurant_shared_kilo_meter),
            ),
            ratingAverage = sortingValues.ratingAverage,
            popularity = sortingValues.popularity,
        )
    )
