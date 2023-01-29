package com.jet.search.presentation.model

data class PhotoUiModel(
    val localId: String,
    val id: Int,
    val comments: Int,
    val downloads: Int,
    val largeImageURL: String,
    val previewURL: String,
    val likes: Int,
    val tags: String,
    val userName: String,
    val userId: Int,
)
