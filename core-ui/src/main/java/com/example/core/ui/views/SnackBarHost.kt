package com.example.core.ui.views

import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

val LocalSnackBarHostState: ProvidableCompositionLocal<SnackbarHostState> = compositionLocalOf { error("No SnackbarHostState provided") }