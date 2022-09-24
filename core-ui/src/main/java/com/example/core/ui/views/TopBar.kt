package com.example.core.ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.ui.R
import com.example.core.ui.theme.JetTheme

@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
    onSearchTextChange: ((String) -> Unit)? = null,
    onClearClick: () -> Unit = {},
    onNavigateUp: (() -> Unit)? = null,
    searchHint: String = stringResource(id = R.string.search_restaurant),
    searchText: String = "",
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        contentColor = Color.White,
        title = {
            Column {
                if (onSearchTextChange != null) {
                    SearchText(
                        onSearchTextChange = onSearchTextChange,
                        onClearClick = onClearClick,
                        searchHint = searchHint,
                        searchText = searchText,
                    )
                } else {
                    Text(
                        text = title,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }
            }
        },
        modifier = modifier,
        navigationIcon = onNavigateUp?.let {
            {
                IconButton(
                    onClick = it,
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        },
        actions = actions,
    )
}

@Composable
fun SearchText(
    modifier: Modifier = Modifier,
    onSearchTextChange: ((String) -> Unit),
    onClearClick: () -> Unit = {},
    searchHint: String = "Search a restaurant",
    searchText: String = "",
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                end = 16.dp,
            )
            .focusRequester(focusRequester)
            .then(modifier),
        value = searchText,
        onValueChange = onSearchTextChange,
        shape = JetTheme.shape.roundCorner8,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            backgroundColor = Color.White

        ),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp
        ),
        trailingIcon = {
            AnimatedVisibility(
                enter = fadeIn(),
                exit = fadeOut(),
                visible = true,
            ) {
                IconButton(
                    onClick = { onClearClick() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                    )
                }
            }
        },
        placeholder = {
            Text(searchHint)
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
            }
        ),
    )

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }
}

@Preview
@Composable
fun TopBarDemo() {
    JetTheme {
        Column(
            verticalArrangement = Arrangement
                .spacedBy(16.dp),
            modifier = Modifier.wrapContentSize()
        ) {
            TopBar(
                "Just Eat",
            )
            TopBar(
                "Just Eat",
                onNavigateUp = {}
            )
            TopBar(
                "Just Eat",
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Search, contentDescription = "Search Icon")
                    }
                },
            )
            TopBar(
                "Just Eat",
                onNavigateUp = {},
                onSearchTextChange = {}
            )
        }
    }
}
