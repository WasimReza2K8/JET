package com.example.core.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder

interface FeatureLauncher {
    fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier = Modifier,
    )
}
