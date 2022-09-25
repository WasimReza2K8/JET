package com.jet.restaurant.presentation.views

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.core.ui.R
import com.example.core.ui.theme.JetTheme
import com.jet.restaurant.domain.model.Status.Open
import com.jet.restaurant.presentation.model.RestaurantUiModel
import com.jet.restaurant.presentation.model.RestaurantUiModel.SortingValues

@Composable
fun RestaurantList(
    restaurants: List<RestaurantUiModel>,
    modifier: Modifier = Modifier,
) {
    val sidePadding = getPadding()
    LazyColumn(
        modifier = modifier
            .padding(horizontal = sidePadding)
            .testTag(stringResource(R.string.restaurant_list)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(items = restaurants) { item ->
            RestaurantItem(item = item)
        }
    }
}

@Composable
private fun getPadding(): Dp {
    var orientation by remember { mutableStateOf(Configuration.ORIENTATION_PORTRAIT) }

    val configuration = LocalConfiguration.current

    LaunchedEffect(configuration) {
        snapshotFlow { configuration.orientation }
            .collect { orientation = it }
    }

    return when (orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            JetTheme.spacing.spacing80
        }
        else -> {
            JetTheme.spacing.spacing0
        }
    }
}

@Preview
@Composable
fun RestaurantListPreview() {
    JetTheme {
        RestaurantList(
            restaurants = listOf(
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
            )
        )
    }
}
