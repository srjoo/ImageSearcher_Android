package com.smwrd.imgsearcher.data.repository.local

import com.smwrd.imgsearcher.domain.model.ImageModel
import kotlinx.coroutines.flow.Flow

interface ImageLocalDataSource {
    suspend fun addFavorite(image: ImageModel)
    suspend fun removeFavorite(image: ImageModel)
    fun getAllFavorites() : Flow<List<ImageModel>>
}