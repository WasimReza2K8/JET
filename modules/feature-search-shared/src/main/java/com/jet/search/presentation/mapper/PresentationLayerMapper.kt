package com.jet.search.presentation.mapper

import com.jet.search.presentation.model.PhotoUiModel
import com.jet.search.domain.model.Photo

fun Photo.toPhotoUiModel() = PhotoUiModel(
    localId = localId,
    comments = comments,
    downloads = downloads,
    id = id,
    largeImageURL = largeImageURL,
    likes = likes,
    tags = tagToHashTag(tags),
    userName = userName,
    userId = userId,
    previewURL = previewURL,
)

fun tagToHashTag(tags: String): String {
    val temp = tags.replace(", ", " #")
    return "#${temp}"
}
