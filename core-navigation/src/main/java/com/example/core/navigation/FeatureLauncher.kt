package com.example.core.navigation

import androidx.navigation.NavGraphBuilder

interface FeatureLauncher {
    fun registerGraph(navGraphBuilder: NavGraphBuilder)
}
