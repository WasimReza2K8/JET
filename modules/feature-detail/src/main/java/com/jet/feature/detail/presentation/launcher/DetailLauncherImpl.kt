package com.jet.feature.detail.presentation.launcher

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jet.detail.presentation.DetailLauncher
import com.jet.feature.detail.presentation.view.DetailScreen
import javax.inject.Inject

class DetailLauncherImpl @Inject constructor() : DetailLauncher {

    override fun route(localId: String) = "$ROUTE/$localId"

    override fun registerGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.composable(
            route = "$ROUTE/{$LOCAL_ID}",
            arguments = listOf(navArgument(LOCAL_ID) { type = NavType.StringType })
        ) {
            DetailScreen()
        }
    }

    companion object {
        private const val ROUTE = "detail"
        const val LOCAL_ID = "local_id"
    }
}
