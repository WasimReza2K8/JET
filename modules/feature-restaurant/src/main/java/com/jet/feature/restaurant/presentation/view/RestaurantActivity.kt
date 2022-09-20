package com.jet.feature.restaurant.presentation.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.ui.theme.JetTheme
import com.jet.feature.restaurant.domain.model.SortingType.BestMatch
import com.jet.feature.restaurant.presentation.model.RestaurantUiModel
import com.jet.feature.restaurant.presentation.model.RestaurantUiModel.SortingValues
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.Effect.UnknownErrorEffect
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantContract.State
import com.jet.feature.restaurant.presentation.viewmodel.RestaurantViewModel
import com.jet.restaurant.domain.model.Status.Open
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.flow

@AndroidEntryPoint
class RestaurantActivity : AppCompatActivity() {
    private val viewModel: RestaurantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetTheme {
                MainScreen(
                    state = viewModel.viewState.value,
                    sendEvent = { viewModel.onUiEvent(it) },
                    effectFlow = viewModel.effect,
                )
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    JetTheme {
        MainScreen(
            state = State(
                restaurantList = listOf(
                    RestaurantUiModel(
                        id = "1",
                        name = "Sushi Bar",
                        status = Open().value,
                        sortingValues = SortingValues(
                            bestMatch = 0.0,
                            newest = 96.0,
                            ratingAverage = 4.5,
                            distance = "1.19mock",
                            popularity = 17.0,
                            averageProductPrice = "mock 15.36mock",
                            deliveryCosts = "2mock",
                            minCost = "mock 10mock"
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

