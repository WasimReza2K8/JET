package com.example.wasim.search

import com.example.wasim.search.FakeSearchRepository.ReturnType.NetworkException
import com.example.wasim.search.FakeSearchRepository.ReturnType.UnknownException
import com.example.wasim.search.FakeSearchRepository.ReturnType.Valid
import com.example.wasim.search.FakeSearchRepository.ReturnType.ValidEmptyList
import com.jet.search.domain.model.Photo
import com.jet.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import photo
import photoList
import java.io.IOException
import javax.inject.Inject

class FakeSearchRepository @Inject constructor() : SearchRepository {
    private lateinit var returnType: ReturnType
    override fun searchPhoto(query: String): Flow<List<Photo>> =
        flow {
            when (returnType) {
                Valid -> emit(photoList)
                ValidEmptyList -> emit(emptyList())
                NetworkException -> throw IOException()
                UnknownException -> throw RuntimeException()
            }
        }

    override fun getPhotoById(id: String): Flow<Photo?> =
        flow {
            when (returnType) {
                Valid -> emit(photo)
                UnknownException -> throw RuntimeException()
                else -> {}
            }
        }

    fun setReturnType(returnType: ReturnType) {
        this.returnType = returnType
    }

    enum class ReturnType {
        Valid,
        ValidEmptyList,
        NetworkException,
        UnknownException
    }

}