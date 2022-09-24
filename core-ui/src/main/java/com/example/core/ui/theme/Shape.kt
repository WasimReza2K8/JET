package com.example.core.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

data class Shape(
    val roundCorner4: CornerBasedShape = RoundedCornerShape(4.dp),
    val roundCorner8: CornerBasedShape = RoundedCornerShape(8.dp),
    val roundCorner16: CornerBasedShape = RoundedCornerShape(16.dp),
)
