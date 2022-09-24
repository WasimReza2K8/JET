import com.jet.restaurant.domain.model.Restaurant
import com.jet.restaurant.domain.model.Restaurant.SortingValues
import com.jet.restaurant.domain.model.Status.Closed
import com.jet.restaurant.domain.model.Status.Open
import com.jet.restaurant.domain.model.Status.OrderAhead

val restaurant0 =
    Restaurant(
        id = "1",
        name = "Sushi Bar",
        status = Open,
        sortingValues = SortingValues(
            bestMatch = 0.0,
            newest = 96.0,
            ratingAverage = 4.5,
            distance = 1190,
            popularity = 17.0,
            averageProductPrice = 1536,
            deliveryCosts = 200,
            minCost = 1000
        )
    )

val restaurant3 =
    restaurant0.copy(
        name = "Delight",
        status = Open,
        sortingValues = SortingValues(
            bestMatch = 1232.0,
            newest = 63.0,
            ratingAverage = 3.5,
            distance = 2199,
            popularity = 1269.0,
            averageProductPrice = 1139,
            deliveryCosts = 230,
            minCost = 1501
        )
    )

val restaurant2 =
    restaurant0.copy(
        name = "Devine",
        status = OrderAhead,
        sortingValues = SortingValues(
            bestMatch = 1222.0,
            newest = 93.0,
            ratingAverage = 3.0,
            distance = 1199,
            popularity = 1212.0,
            averageProductPrice = 1639,
            deliveryCosts = 250,
            minCost = 1001
        )
    )

val restaurant1 =
    restaurant0.copy(
        status = Closed,
        sortingValues = SortingValues(
            bestMatch = 12.0,
            newest = 9.0,
            ratingAverage = 4.0,
            distance = 119,
            popularity = 12.0,
            averageProductPrice = 1636,
            deliveryCosts = 210,
            minCost = 100
        )
    )

val restaurants =
    listOf(
        restaurant0,
        restaurant1,
        restaurant2,
        restaurant3,
    )