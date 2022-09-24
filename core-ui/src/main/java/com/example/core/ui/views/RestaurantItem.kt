package com.example.core.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.ui.R
import com.example.core.ui.R.drawable
import com.example.core.ui.theme.JetTheme
import com.example.core.ui.theme.JetTheme.elevation
import com.example.core.ui.theme.JetTheme.spacing
import com.jet.restaurant.presentation.model.RestaurantUiModel
import com.jet.restaurant.presentation.model.RestaurantUiModel.SortingValues

@Composable
fun RestaurantItem(
    modifier: Modifier = Modifier,
    item: RestaurantUiModel,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = spacing.spacing4),
        elevation = elevation.elevation4
    ) {
        Row(
            horizontalArrangement = Arrangement
                .spacedBy(spacing.spacing16),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = modifier.padding(spacing.spacing16),
                painter = painterResource(drawable.ic_storefront),
                contentDescription = "Restaurant Image"
            )
            RestaurantContent(item = item)
        }
    }
}

@Composable
private fun RestaurantContent(item: RestaurantUiModel) {
    Column(
        modifier = Modifier.padding(vertical = spacing.spacing8),
        verticalArrangement = Arrangement
            .spacedBy(spacing.spacing4),
    ) {
        ItemTopRow(item = item)

        Text(
            text = " (${item.status})",
            style = JetTheme.typography.caption
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spacing.spacing16)
        ) {
            ImageTextItem(
                modifier = Modifier.padding(end = spacing.spacing16),
                painter = painterResource(id = drawable.ic_best_match),
                text = item.sortingValues.bestMatch.toString(),
            )
            ImageTextItem(
                painter = painterResource(id = drawable.ic_distance_small),
                text = item.sortingValues.distance,
            )
            ImageTextItem(
                painter = painterResource(id = drawable.ic_delivery_costs_small),
                text = item.sortingValues.deliveryCosts,
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spacing.spacing16)
        ) {
            ImageTextItem(
                painter = painterResource(id = drawable.ic_min_cost_small),
                text = item.sortingValues.minCost,
            )
            ImageTextItem(
                painter = painterResource(id = drawable.ic_min_cost_small),
                text = item.sortingValues.averageProductPrice,
            )
            ImageTextItem(
                painter = painterResource(id = drawable.ic_new_release),
                text = item.sortingValues.newest.toString(),
            )
        }
        ImageTextItem(
            painter = painterResource(id = drawable.ic_popularity),
            text = "${item.sortingValues.popularity}",
        )
    }
}

@Composable
private fun ItemTopRow(item: RestaurantUiModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item.name,
            style = JetTheme.typography.body1
        )
        ImageTextItem(
            modifier = Modifier.padding(end = spacing.spacing16),
            painter = painterResource(id = drawable.ic_rating),
            text = item.sortingValues.ratingAverage.toString(),
        )
    }
}

@Composable
fun ImageTextItem(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String = "",
    text: String,
) {
    Row(
        modifier = Modifier
            .width(spacing.spacing88)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription
        )
        Text(
            text = text,
            style = JetTheme.typography.caption,
            color = JetTheme.color.grey500,
        )
    }
}

@Preview
@Composable
fun ItemPreview() {
    JetTheme {
        RestaurantItem(
            item = RestaurantUiModel(
                id = "124s",
                name = "Sushi Bar",
                status = "Open",
                sortingValues = SortingValues(
                    bestMatch = 123.0,
                    newest = 3456.0,
                    ratingAverage = 4.0,
                    distance = "1234.0",
                    popularity = 1278.0,
                    averageProductPrice = "12.0",
                    deliveryCosts = "200.0",
                    minCost = "1234.0",
                )
            )
        )
    }
}

private val item = RestaurantUiModel(
    id = "124s",
    name = "Sushi Bar",
    status = "Open",
    sortingValues = SortingValues(
        bestMatch = 123.0,
        newest = 3456.0,
        ratingAverage = 4.0,
        distance = "1234.0",
        popularity = 1278.0,
        averageProductPrice = "12.0",
        deliveryCosts = "200.0",
        minCost = "1234.0",
    )
)

@Preview
@Composable
fun ListPreview() {
    JetTheme {
        val list = mutableListOf(item, item, item, item)
        LazyColumn(
            Modifier.background(JetTheme.color.grey200)
        ) {
            items(items = list) { item ->
                RestaurantItem(item = item)
            }
        }
    }
}
