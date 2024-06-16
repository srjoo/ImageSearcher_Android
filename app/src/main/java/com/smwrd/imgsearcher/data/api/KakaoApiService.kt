package com.smwrd.imgsearcher.data.api

import com.smwrd.imgsearcher.data.model.ImageSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoApiService {
    @GET("/v2/search/image")
    suspend fun searchImages(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): ImageSearchResponse
}