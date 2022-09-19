package com.example.core.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Elevation internal constructor(
    val noElevation: Dp = 0.dp,
    val elevation4: Dp = 4.dp,
    val elevation8: Dp = 8.dp,
    val elevation16: Dp = 16.dp,
    val default: Dp = elevation4,
)