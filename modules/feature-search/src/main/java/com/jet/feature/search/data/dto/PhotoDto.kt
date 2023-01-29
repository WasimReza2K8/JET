package com.jet.feature.search.data.dto

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class PhotoDto(
    @SerialName("comments")
    val comments: Int,
    @SerialName("downloads")
    val downloads: Int,
    @SerialName("id")
    val id: Int,
    @SerialName("largeImageURL")
    val largeImageURL: String,
    @SerialName("webformatURL")
    val imageURL: String,
    @SerialName("likes")
    val likes: Int,
    @SerialName("tags")
    val tags: String,
    @SerialName("user")
    val user: String,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("views")
    val views: Int,
)
