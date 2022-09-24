package com.jet.feature.search.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration.Long
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.ui.theme.JetTheme
import com.example.core.ui.views.RestaurantList
import com.example.core.ui.views.TopBar
import com.jet.feature.search.presentation.viewmodel.SearchContract.Effect
import com.jet.feature.search.presentation.viewmodel.SearchContract.Effect.NetworkErrorEffect
import com.jet.feature.search.presentation.viewmodel.SearchContract.Effect.UnknownErrorEffect
import com.jet.feature.search.presentation.viewmodel.SearchContract.Event
import com.jet.feature.search.presentation.viewmodel.SearchContract.Event.OnBackButtonClicked
import com.jet.feature.search.presentation.viewmodel.SearchContract.Event.OnQueryClearClicked
import com.jet.feature.search.presentation.viewmodel.SearchContract.Event.OnSearch
import com.jet.feature.search.presentation.viewmodel.SearchContract.State
import com.jet.feature.search.presentation.viewmodel.SearchViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    SearchScreenImpl(
        state = viewModel.viewState.value,
        sendEvent = { viewModel.onUiEvent(it) },
        effectFlow = viewModel.effect,
    )
}

@Composable
private fun SearchScreenImpl(
    state: State,
    effectFlow: Flow<Effect>,
    sendEvent: (event: Event) -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }

    HandleEffect(
        effectFlow = effectFlow,
        snackBarHostState = snackBarHostState,
    )

    Scaffold(
        scaffoldState = rememberScaffoldState(snackbarHostState = snackBarHostState),
        topBar = {
            TopBar(
                title = "Search",
                searchText = state.query,
                onNavigateUp = {
                    sendEvent(OnBackButtonClicked)
                },
                onSearchTextChange = { query ->
                    sendEvent(OnSearch(query))
                },
                onClearClick = {
                    sendEvent(OnQueryClearClicked)
                }
            )
        },
    ) { scaffoldPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(
                    start = JetTheme.spacing.spacing16,
                    top = scaffoldPadding.calculateTopPadding(),
                    end = JetTheme.spacing.spacing16,
                    bottom = scaffoldPadding.calculateBottomPadding(),
                )
        ) {
            if (state.restaurants.isEmpty() && state.noResultFoundText.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = state.noResultFoundText)
                }
            } else {
                RestaurantList(restaurants = state.restaurants)
            }
        }
    }
}

@Composable
private fun HandleEffect(
    effectFlow: Flow<Effect>,
    snackBarHostState: SnackbarHostState,
) {
    LaunchedEffect(effectFlow) {
        effectFlow.onEach { effect ->
            when (effect) {
                is NetworkErrorEffect -> {
                    snackBarHostState.showSnackbar(
                        message = effect.message,
                        duration = Long,
                    )
                }
                is UnknownErrorEffect -> {
                    snackBarHostState.showSnackbar(
                        message = effect.message,
                        duration = Long,
                    )
                }
            }
        }.collect()
    }
}
