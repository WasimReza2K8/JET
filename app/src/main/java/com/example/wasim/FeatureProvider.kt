package com.example.wasim

import com.jet.restaurant.presentation.launcher.RestaurantFeatureLauncher
import com.jet.search.presentation.SearchLauncher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureProvider @Inject constructor(
    val restaurantLauncher: RestaurantFeatureLauncher,
    val searchLauncher: SearchLauncher,
)
