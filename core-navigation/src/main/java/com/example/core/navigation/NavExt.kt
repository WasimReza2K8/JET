package com.example.core.navigation

import androidx.navigation.NavGraphBuilder

fun NavGraphBuilder.register(featureLauncher: FeatureLauncher) {
    featureLauncher.registerGraph(navGraphBuilder = this)
}
