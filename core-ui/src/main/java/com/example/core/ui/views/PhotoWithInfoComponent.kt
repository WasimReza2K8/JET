package com.example.core.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.core.ui.theme.JetTheme

@Composable
fun PhotoWithInfoComponent(
    text1: String,
    text2: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit = {},
) {

    Box(modifier = modifier.fillMaxSize()) {
        ImageComponent(
            url = imageUrl,
            contentScale = ContentScale.FillWidth,
            modifier = modifier
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .background(color = JetTheme.color.background)
        ) {
            Text(
                text = text1,
                fontSize = 14.sp,
                color = JetTheme.color.grey200,
                modifier = Modifier.padding(horizontal = JetTheme.spacing.spacing4),
            )
            Text(
                text = text2,
                fontSize = 14.sp,
                color = JetTheme.color.teal200,
                modifier = Modifier.padding(horizontal = JetTheme.spacing.spacing4),
            )
            content()
        }
    }
}

@Preview
@Composable
private fun ImageTextPreview() {
    JetTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            PhotoWithInfoComponent(
                text1 = "userName",
                text2 = "tags",
                modifier = Modifier,
                imageUrl = "imageUrl"
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "inner Text")
                }
            }
        }
    }
}
