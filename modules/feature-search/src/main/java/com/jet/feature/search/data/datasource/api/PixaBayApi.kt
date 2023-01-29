package com.jet.feature.search.data.datasource.api

import com.jet.feature.search.data.dto.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaBayApi {
    @GET("api/")
    suspend fun searchPhotos(
        @Query("q") searchString: String,
        @Query("per_page") perPage: Int = 50,
    ): ResponseDto
}