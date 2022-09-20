package com.jet.feature.restaurant.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration.Long
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.core.ui.theme.JetTheme
import com.example.core.ui.views.Chip
import com.example.core.ui.views.ChipGroup
import com.example.core.ui.views.TopBar
import com.jet.feature.restaurant.R.string
import com.jet.feature.restaurant.domain.model.SortingType.AverageProductPrice
import com.jet.feature.restaurant.domain.model.SortingType.BestMatch
import com.jet.feature.restaurant.domain.model.SortingType.DeliveryCost
import com.jet.feature.restaurant.domain.model.SortingType.Distance
import com.jet.feature.restaurant.domain.model.SortingType.MinCost
import com.jet.feature.restaurant.domain.model.SortingType.Newest
import com.jet.feature.restaurant.domain.model.SortingType.Popularity
import com.jet.feature.restaurant.domain.model.SortingType.RatingAverage
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
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun MainScreen(
    state: State,
    effectFlow: Flow<Effect>,
    sendEvent: (event: Event) -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }
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

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = string.restaurant_title),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Search, contentDescription = "")
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

            LazyColumn {
                items(items = state.restaurantList) { item ->
                    RestaurantItem(item = item)
                }
            }
        }
    }
}

@Composable
fun selectChip(
    state: State,
    chips: List<Chip>,
): List<Chip> {
    val selectedId = when (state.selectedSortingType) {
        BestMatch -> string.restaurant_best_match
        MinCost -> string.restaurant_min_cost
        DeliveryCost -> string.restaurant_delivery_cost
        RatingAverage -> string.restaurant_rating_avg
        Distance -> string.restaurant_distance
        Popularity -> string.restaurant_popularity
        AverageProductPrice -> string.restaurant_avg_price
        Newest -> string.restaurant_newest
    }
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
            checked = true,
            onCheckedListener = {
                sendEvent(OnBestMatchClicked)
            }
        ),
        Chip(
            id = string.restaurant_rating_avg,
            text = stringResource(id = string.restaurant_rating_avg),
            onCheckedListener = {
                sendEvent(OnRatingAverageClicked)

            }
        ),

        Chip(
            id = string.restaurant_distance,
            text = stringResource(id = string.restaurant_distance),
            onCheckedListener = {
                sendEvent(OnDistanceClicked)
            }
        ),
        Chip(
            id = string.restaurant_min_cost,
            text = stringResource(id = string.restaurant_min_cost),
            onCheckedListener = {
                sendEvent(OnMinCostClicked)
            }
        ),
        Chip(
            id = string.restaurant_avg_price,
            text = stringResource(id = string.restaurant_avg_price),
            onCheckedListener = {
                sendEvent(OnAverageProductPriceClicked)

            }
        ),
        Chip(
            id = string.restaurant_delivery_cost,
            text = stringResource(id = string.restaurant_delivery_cost),
            onCheckedListener = {
                sendEvent(OnDeliveryCostClicked)
            }
        ),
        Chip(
            id = string.restaurant_newest,
            text = stringResource(id = string.restaurant_newest),
            onCheckedListener = {
                sendEvent(OnNewestClicked)
            }
        ),
        Chip(
            id = string.restaurant_popularity,
            text = stringResource(id = string.restaurant_popularity),
            onCheckedListener = {
                sendEvent(OnPopularityClicked)
            }
        ),
    )
