package com.jet.feature.search.presentation.launcher

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jet.feature.search.presentation.view.SearchScreen
import com.jet.search.presentation.SearchLauncher
import javax.inject.Inject

class SearchLauncherImpl @Inject constructor() : SearchLauncher {
    private val route = "search"

    override fun route() = route

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier,
    ) {
        navGraphBuilder.composable(route) {
            SearchScreen()
        }
    }
}
