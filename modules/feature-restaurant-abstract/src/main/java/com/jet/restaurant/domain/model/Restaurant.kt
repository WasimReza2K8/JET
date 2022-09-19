package com.jet.restaurant.domain.model

data class Restaurant(
    val id: String,
    val name: String,
    val status: Status,
    val sortingValues: SortingValues,
) {
    data class SortingValues(
        val bestMatch: Double,
        val newest: Double,
        val ratingAverage: Double,
        val distance: Int,
        val popularity: Double,
        val averageProductPrice: Int,
        val deliveryCosts: Int,
        val minCost: Int,
    )
}

sealed interface Status {
    val priority: Int
    val value: String

    data class Open(
        override val priority: Int = 1,
        override val value: String = "open",
    ) : Status

    data class OrderAhead(
        override val priority: Int = 2,
        override val value: String = "order ahead",
    ) : Status

    data class Closed(
        override val priority: Int = 3,
        override val value: String = "closed",
    ) : Status

    data class Unknown(
        override val priority: Int = 100,
        override val value: String = "closed",
    ) : Status
}