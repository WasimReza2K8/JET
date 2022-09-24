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

sealed class Status(
    val priority: Int,
    val value: String,
) {
    object Open : Status(priority = OPEN_PRIORITY, value = OPEN)

    object OrderAhead : Status(priority = ORDER_AHEAD_PRIORITY, value = ORDER_AHEAD)

    object Closed : Status(priority = CLOSED_PRIORITY, value = CLOSED)

    object Unknown : Status(priority = UNKNOWN_PRIORITY, value = UNKNOWN)

    companion object {
        private const val OPEN_PRIORITY = 1
        private const val OPEN = "open"
        private const val ORDER_AHEAD_PRIORITY = 2
        private const val ORDER_AHEAD = "order ahead"
        private const val CLOSED_PRIORITY = 3
        private const val CLOSED = "closed"
        private const val UNKNOWN_PRIORITY = 100
        private const val UNKNOWN = "unknown"
    }
}
