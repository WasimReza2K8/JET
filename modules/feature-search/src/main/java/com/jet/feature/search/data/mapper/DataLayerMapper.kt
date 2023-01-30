package com.jet.feature.search.data.mapper

import com.jet.feature.search.data.datasource.db.entity.PhotoEntity
import com.jet.feature.search.data.dto.PhotoDto
import com.jet.search.domain.model.Photo

fun PhotoEntity.toDomainPhoto(): Photo {
    return Photo(
        localId = localId,
        comments = comments,
        downloads = downloads,
        id = id,
        largeImageURL = largeImageURL,
        likes = likes,
        tags = tags,
        userName = user,
        userId = userId,
        searchTerm = searchTerm,
        previewURL = previewURL,
    )
}

fun PhotoDto.toPhotoEntity(searchTerm: String): PhotoEntity {
    return PhotoEntity(
        localId = "${searchTerm}_$id",
        comments = comments,
        downloads = downloads,
        id = id,
        largeImageURL = largeImageURL,
        likes = likes,
        tags = tags,
        user = user,
        userId = userId,
        searchTerm = searchTerm,
        previewURL = previewURL,
    )
}
