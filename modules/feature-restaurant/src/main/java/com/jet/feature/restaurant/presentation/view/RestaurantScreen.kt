package com.jet.feature.restaurant.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration.Long
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.ui.theme.JetTheme
import com.example.core.ui.views.Chip
import com.example.core.ui.views.ChipGroup
import com.example.core.ui.views.RestaurantList
import com.example.core.ui.views.TopBar
import com.jet.feature.restaurant.R.string
import com.jet.feature.restaurant.domain.model.SortingType.BestMatch
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Effect
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Effect.NetworkErrorEffect
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Effect.UnknownErrorEffect
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnAverageProductPriceClicked
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnBestMatchClicked
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnDeliveryCostClicked
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnDistanceClicked
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnMinCostClicked
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnNewestClicked
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnPopularityClicked
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnRatingAverageClicked
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Event.OnSearchClicked
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.State
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantViewModel
import com.jet.restaurant.domain.model.Status.Open
import com.jet.restaurant.presentation.model.RestaurantUiModel
import com.jet.restaurant.presentation.model.RestaurantUiModel.SortingValues
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

@Composable
fun RestaurantScreen(viewModel: RestaurantViewModel = hiltViewModel()) {
    RestaurantScreenImpl(
        state = viewModel.viewState.value,
        sendEvent = { viewModel.onUiEvent(it) },
        effectFlow = viewModel.effect,
    )
}

@Composable
private fun RestaurantScreenImpl(
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
                title = stringResource(id = string.restaurant_title),
                actions = {
                    IconButton(
                        onClick = {
                            sendEvent(OnSearchClicked)
                        }
                    ) {
                        Icon(Icons.Default.Search, contentDescription = "Search Icon")
                    }
                },
            )
        },
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = JetTheme.spacing.spacing16,
                    top = scaffoldPadding.calculateTopPadding() + JetTheme.spacing.spacing8,
                    end = JetTheme.spacing.spacing16,
                    bottom = scaffoldPadding.calculateBottomPadding() + JetTheme.spacing.spacing8,
                ),
            verticalArrangement = Arrangement.spacedBy(JetTheme.spacing.spacing8)
        ) {
            val chips = selectChip(state, getChips(sendEvent))
            ChipGroup(
                chips = chips
            )

            RestaurantList(restaurants = state.restaurantList)
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

@Composable
fun selectChip(
    state: State,
    chips: List<Chip>,
): List<Chip> {
    val selectedId = state.selectedSortingType.id
    val selectedIndex = chips.indexOfFirst { it.id == selectedId }
    val selectedChip = chips[selectedIndex]
    return chips.toMutableList().map {
        it.copy(checked = false)
    }.toMutableList().apply {
        set(
            selectedIndex,
            selectedChip.copy(checked = true),
        )
    }
}

@Composable
fun getChips(
    sendEvent: (event: Event) -> Unit,
): List<Chip> =
    listOf(
        Chip(
            id = string.restaurant_best_match,
            text = stringResource(id = string.restaurant_best_match),
            onCheckedListener = { isChecked ->
                sendUiEvent(sendEvent, isChecked, OnBestMatchClicked)
            }
        ),
        Chip(
            id = string.restaurant_rating_avg,
            text = stringResource(id = string.restaurant_rating_avg),
            onCheckedListener = { isChecked ->
                sendUiEvent(sendEvent, isChecked, OnRatingAverageClicked)
            }
        ),

        Chip(
            id = string.restaurant_distance,
            text = stringResource(id = string.restaurant_distance),
            onCheckedListener = { isChecked ->
                sendUiEvent(sendEvent, isChecked, OnDistanceClicked)
            }
        ),
        Chip(
            id = string.restaurant_min_cost,
            text = stringResource(id = string.restaurant_min_cost),
            onCheckedListener = { isChecked ->
                sendUiEvent(sendEvent, isChecked, OnMinCostClicked)
            }
        ),
        Chip(
            id = string.restaurant_avg_price,
            text = stringResource(id = string.restaurant_avg_price),
            onCheckedListener = { isChecked ->
                sendUiEvent(sendEvent, isChecked, OnAverageProductPriceClicked)
            }
        ),
        Chip(
            id = string.restaurant_delivery_cost,
            text = stringResource(id = string.restaurant_delivery_cost),
            onCheckedListener = { isChecked ->
                sendUiEvent(sendEvent, isChecked, OnDeliveryCostClicked)
            }
        ),
        Chip(
            id = string.restaurant_newest,
            text = stringResource(id = string.restaurant_newest),
            onCheckedListener = { isChecked ->
                sendUiEvent(sendEvent, isChecked, OnNewestClicked)
            }
        ),
        Chip(
            id = string.restaurant_popularity,
            text = stringResource(id = string.restaurant_popularity),
            onCheckedListener = { isChecked ->
                sendUiEvent(sendEvent, isChecked, OnPopularityClicked)
            }
        ),
    )

private fun sendUiEvent(
    sendEvent: (event: Event) -> Unit,
    isChecked: Boolean,
    event: Event,
) {
    if (!isChecked) {
        sendEvent(event)
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    JetTheme {
        RestaurantScreenImpl(
            state = State(
                restaurantList = listOf(
                    RestaurantUiModel(
                        id = "1",
                        name = "Sushi Bar",
                        status = Open.value,
                        sortingValues = SortingValues(
                            bestMatch = 0.0,
                            newest = 96.0,
                            ratingAverage = 4.5,
                            distance = "1.19$",
                            popularity = 17.0,
                            averageProductPrice = "Avg. 15.36$",
                            deliveryCosts = "2$",
                            minCost = "min 10$"
                        )
                    )
                ),
                selectedSortingType = BestMatch
            ),
            sendEvent = { },
            effectFlow = flow { emit(UnknownErrorEffect("testing")) },
        )
    }
}
