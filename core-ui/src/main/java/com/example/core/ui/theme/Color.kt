package com.example.core.ui.theme

import androidx.compose.ui.graphics.Color


data class Color internal constructor(
    val blue200: Color = Color(color = 0xFF80E1FF),
    val blue500: Color = Color(color = 0xFF00D9FF),
    val blue700: Color = Color(color = 0xFF038CCC),
    val teal200: Color = Color(color = 0xFF03DAC5),
    val grey200: Color = Color(color = 0xFFF3F8F7),
    val grey500: Color = Color(color = 0xFF738180),
    val background: Color =Color(android.graphics.Color.parseColor("#BF000000"))
)
