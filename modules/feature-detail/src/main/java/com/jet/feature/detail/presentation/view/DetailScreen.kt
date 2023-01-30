/*
 * Copyright 2021 Wasim Reza.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jet.feature.detail.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.ui.theme.JetTheme
import com.example.core.ui.views.PhotoWithInfoComponent
import com.jet.feature.detail.R
import com.jet.feature.detail.presentation.viewmodel.DetailContract.Event
import com.jet.feature.detail.presentation.viewmodel.DetailContract.Event.OnBackButtonClicked
import com.jet.feature.detail.presentation.viewmodel.DetailContract.State
import com.jet.feature.detail.presentation.viewmodel.DetailViewModel
import timber.log.Timber

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DetailScreen(viewModel: DetailViewModel = hiltViewModel()) {
    val state: State by viewModel.viewState.collectAsStateWithLifecycle()
    Timber.e("selected photo:${state.photo}")
    DetailScreenImpl(
        state = state,
        sendEvent = { viewModel.onUiEvent(it) },
    )
}

@Composable
private fun DetailScreenImpl(
    state: State,
    sendEvent: (event: Event) -> Unit,
) {

    Scaffold { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
        ) {
            IconButton(onClick = { sendEvent(OnBackButtonClicked) }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "",
                )
            }
            state.photo?.let { photo ->
                PhotoWithInfoComponent(
                    text1 = photo.userName,
                    text2 = photo.tags,
                    imageUrl = photo.largeImageURL) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        ImageActivityItem(
                            text = photo.likes.toString(),
                            painter = painterResource(id = R.drawable.detail_ic_like)
                        )
                        ImageActivityItem(
                            text = photo.comments.toString(),
                            painter = painterResource(id = R.drawable.detail_ic_comment)
                        )
                        ImageActivityItem(
                            text = photo.downloads.toString(),
                            painter = painterResource(id = R.drawable.detail_ic_download)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ImageActivityItem(
    text: String,
    painter: Painter,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier
        .wrapContentSize()
        .padding(horizontal = JetTheme.spacing.spacing4)

    ) {
        Image(painter = painter, contentDescription = "image_activity")
        Text(text = text, color = Color.White)
    }
}

@Preview
@Composable
private fun DetailPreview() {
    DetailScreenImpl(State(), { event: Event -> })
}
