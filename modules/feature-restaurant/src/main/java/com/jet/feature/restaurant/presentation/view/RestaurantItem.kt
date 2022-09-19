package com.jet.feature.restaurant.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.ui.R
import com.example.core.ui.theme.JetTheme
import com.jet.feature.restaurant.presentation.RestaurantUiModel
import com.jet.feature.restaurant.presentation.RestaurantUiModel.SortingValues

@Composable
fun RestaurantItem(
    modifier: Modifier = Modifier,
    item: RestaurantUiModel,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 10.dp
    ) {
        Row(
            horizontalArrangement = Arrangement
                .spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                modifier = modifier.padding(16.dp),
                painter = painterResource(R.drawable.ic_storefront),
                contentDescription = "Restaurant Image"
            )
            Column(
                modifier = Modifier.padding(vertical = 8.dp),
                verticalArrangement = Arrangement
                    .spacedBy(8.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item.name,
                            style = JetTheme.typography.body1
                        )
                        Text(
                            text = " (${item.status})",
                            color = Color.Green,
                            style = JetTheme.typography.caption
                        )
                    }
                    ImageTextItem(
                        modifier = Modifier.padding(end = 16.dp),
                        painter = painterResource(id = R.drawable.ic_rating),
                        text = item.sortingValues.ratingAverage.toString(),
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ImageTextItem(
                        painter = painterResource(id = R.drawable.ic_distance_small),
                        text = item.sortingValues.distance.toString(),
                    )
                    ImageTextItem(
                        painter = painterResource(id = R.drawable.ic_delivery_costs_small),
                        text = item.sortingValues.deliveryCosts.toString(),
                    )
                    ImageTextItem(
                        painter = painterResource(id = R.drawable.ic_min_cost_small),
                        text = "${item.sortingValues.minCost}",
                    )
                    ImageTextItem(
                        painter = painterResource(id = R.drawable.ic_min_cost_small),
                        text = "${item.sortingValues.averageProductPrice}",
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ImageTextItem(
                        painter = painterResource(id = R.drawable.ic_new_release),
                        text = "${item.sortingValues.newest}",
                    )
                    ImageTextItem(
                        painter = painterResource(id = R.drawable.ic_popularity),
                        text = "${item.sortingValues.popularity}",
                    )
                }
            }
        }
    }
}

@Composable
fun ImageTextItem(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String = "",
    text: String,
    arrangement: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(4.dp),
) {
    Row(
        modifier = modifier,
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription
        )
        Text(
            text = text,
            style = JetTheme.typography.caption,
            color = JetTheme.color.Grey500,
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
                    distance = 1234.0,
                    popularity = 1278.0,
                    averageProductPrice = 12.0,
                    deliveryCosts = 200.0,
                    minCost = 1234.0,
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
        distance = 1234.0,
        popularity = 1278.0,
        averageProductPrice = 12.0,
        deliveryCosts = 200.0,
        minCost = 1234.0,
    )
)

@Preview
@Composable
fun ListPreview() {
    JetTheme {
        val list = mutableListOf(item, item, item, item)
        LazyColumn(
            Modifier.background(JetTheme.color.Grey200)
        ) {
            items(items = list) { item ->
                RestaurantItem(item = item)
            }
        }
    }
}