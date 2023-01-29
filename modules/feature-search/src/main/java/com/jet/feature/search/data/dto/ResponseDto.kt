package com.jet.feature.search.data.dto

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ResponseDto(
    @SerialName("hits")
    val images: List<PhotoDto>
)
