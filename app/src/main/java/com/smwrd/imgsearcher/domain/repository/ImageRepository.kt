package com.smwrd.imgsearcher.domain.repository

import com.smwrd.imgsearcher.domain.model.ImageModel
import com.smwrd.imgsearcher.domain.model.ImageResultModel
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun searchImages(query: String, page: Int, size: Int) : ImageResultModel
    suspend fun addFavorite(image: ImageModel)
    suspend fun removeFavorite(image: ImageModel)
    fun getAllFavorites() : Flow<List<ImageModel>>
}