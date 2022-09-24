package com.jet.feature.restaurant.presentation.launcher

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jet.feature.restaurant.presentation.view.RestaurantScreen
import com.jet.restaurant.presentation.launcher.RestaurantFeatureLauncher
import javax.inject.Inject

class RestaurantLauncherImpl @Inject constructor() : RestaurantFeatureLauncher {
    private val route = "restaurant"

    override fun route() = route

    override fun registerGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.composable(route) {
            RestaurantScreen()
        }
    }
}
