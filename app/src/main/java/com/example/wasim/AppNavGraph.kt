package com.example.wasim

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.core.navigation.register

@Composable
fun AppNavGraph(
    navController: NavHostController,
    featureProvider: FeatureProvider,
) {
    NavHost(
        navController = navController,
        startDestination = featureProvider.restaurantLauncher.route()
    ) {
        register(featureProvider.restaurantLauncher)
        register(featureProvider.searchLauncher)
    }
}
