package com.example.core.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.ui.theme.JetTheme

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    hint: String,
) {
    Card(
        shape = RoundedCornerShape(30),
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(50.dp),
        elevation = JetTheme.elevation.elevation8,
        backgroundColor = Color.White
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.padding(start = JetTheme.spacing.spacing12)
        ) {
            Box(Modifier.weight(1f)) {
                BasicTextField(
                    value = value,
                    onValueChange = { onValueChange(it) },
                    modifier = Modifier.fillMaxWidth()
                )
                if (hint.isNotBlank() && value.isBlank()) {
                    Text(text = hint, color = Color.Gray)
                }
            }

            Box(Modifier.padding(5.dp)) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    Modifier
                        .clip(RoundedCornerShape(50))
                        .clickable { onClick() }
                        .padding(5.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun SearchBarDemo() {
    JetTheme {
        SearchBar(
            value = "",
            onClick = {},
            onValueChange = {

            },
            hint = "Search"
        )
    }
}
