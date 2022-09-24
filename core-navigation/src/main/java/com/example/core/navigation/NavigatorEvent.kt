package com.example.core.navigation

import androidx.navigation.NavOptionsBuilder

sealed class NavigatorEvent {
    data class Directions(
        val destination: String,
        val builder: NavOptionsBuilder.() -> Unit,
    ) : NavigatorEvent()

    object NavigateUp : NavigatorEvent()
}
