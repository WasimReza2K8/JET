package com.example.core.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ChipDefaults
import androidx.compose.material.FilterChip
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.ui.theme.JetTheme

@Composable
fun JetChip(
    chip: Chip,
) {
    FilterChip(
        selected = chip.checked,
        onClick = {
            chip.onCheckedListener()
        },
        colors = ChipDefaults.filterChipColors(
            selectedBackgroundColor = JetTheme.color.Orange500,
            selectedContentColor = Color.White,
        )
    ) {
        Text(chip.text)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFilterChipEnabled() {
    JetTheme {
        Column(
            verticalArrangement = Arrangement
                .spacedBy(16.dp),
            modifier = Modifier.wrapContentSize()
        ) {
            JetChip(
                chip = Chip(
                    text = "rate",
                ),
            )
        }

    }
}

data class Chip(
    val text: String,
    val id: Int = text.hashCode(),
    val checked: Boolean = false,
    val onCheckedListener: () -> Unit = {},
)

