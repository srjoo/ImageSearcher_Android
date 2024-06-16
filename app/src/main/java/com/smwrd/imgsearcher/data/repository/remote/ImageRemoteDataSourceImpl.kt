package com.smwrd.imgsearcher.data.repository.remote

import com.smwrd.imgsearcher.data.api.KakaoApiService
import com.smwrd.imgsearcher.data.mapper.fromImageDocument
import com.smwrd.imgsearcher.domain.model.ImageResultModel
import javax.inject.Inject

class ImageRemoteDataSourceImpl @Inject constructor (
    private val imageService: KakaoApiService
) : ImageRemoteDataSource {
    override suspend fun searchImages(query: String, page: Int, size: Int): ImageResultModel {
        try {
            val response = imageService.searchImages(query, page, size)
            return ImageResultModel(
                response.meta.is_end,
                response.documents.map { fromImageDocument(it) }.toList()
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ImageResultModel(true, emptyList());
    }
}