package com.example.core.navigation

import androidx.navigation.NavOptionsBuilder
import com.example.core.navigation.NavigatorEvent.Directions
import com.example.core.navigation.NavigatorEvent.NavigateUp
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {
    private val navigationEvents = Channel<NavigatorEvent>(capacity = Channel.CONFLATED)
    val destinations: Flow<NavigatorEvent> = navigationEvents.receiveAsFlow()
    fun navigateUp(): Boolean = navigationEvents.trySend(NavigateUp).isSuccess
    fun navigate(
        route: String,
        builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true }
    ): Boolean =
        navigationEvents.trySend(Directions(route, builder)).isSuccess
}
