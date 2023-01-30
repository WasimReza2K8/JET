package com.jet.search.domain.repository

import com.jet.search.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchPhoto(query: String): Flow<List<Photo>>
    fun getPhotoById(id: String): Flow<Photo?>
}
