package com.smwrd.imgsearcher.data.repository.remote

import com.smwrd.imgsearcher.domain.model.ImageResultModel

interface ImageRemoteDataSource {
    suspend fun searchImages(query: String, page: Int, size: Int): ImageResultModel
}