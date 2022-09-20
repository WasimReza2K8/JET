package com.example.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

private val color = Color()

private val DarkColorPalette = darkColors(
    primary = color.Orange200,
    primaryVariant = color.Orange700,
    secondary = color.Teal200
)

private val LightColorPalette = lightColors(
    primary = color.Orange500,
    primaryVariant = color.Orange700,
    secondary = color.Teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

private val LocalSpacings = compositionLocalOf<Spacing> {
    error("No spacings provided! Make sure to wrap all components in an JetTheme.")
}
private val LocalColors = compositionLocalOf<com.example.core.ui.theme.Color> {
    error("No colors provided! Make sure to wrap all components in an JetTheme.")
}

private val LocalTypography = compositionLocalOf<Typography> {
    error("No typography provided! Make sure to wrap all components in an JetTheme.")
}

private val LocalElevations = compositionLocalOf<Elevation> {
    error("No elevation provided! Make sure to wrap all components in an JetTheme.")
}

private val LocalShapes = compositionLocalOf<Shape> {
    error("No shape provided! Make sure to wrap all components in an JetTheme.")
}
private val LocalSnackBarHostState: ProvidableCompositionLocal<SnackbarHostState> =
    compositionLocalOf { error("No SnackbarHostState provided") }

@Composable
fun JetTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    CompositionLocalProvider(
        LocalElevations provides Elevation(),
        LocalSpacings provides Spacing(),
        LocalColors provides Color(),
        LocalTypography provides Typography(),
        LocalShapes provides Shape(),
    ) {
        MaterialTheme(
            colors = colors,
            content = content
        )
    }
}

object JetTheme {
    val color: com.example.core.ui.theme.Color
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val elevation: Elevation
        @Composable
        @ReadOnlyComposable
        get() = LocalElevations.current

    val shape: Shape
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val spacing: Spacing
        @Composable
        @ReadOnlyComposable
        get() = LocalSpacings.current

    val snackBarHost: SnackbarHostState
        @Composable
        @ReadOnlyComposable
        get() = LocalSnackBarHostState.current
}