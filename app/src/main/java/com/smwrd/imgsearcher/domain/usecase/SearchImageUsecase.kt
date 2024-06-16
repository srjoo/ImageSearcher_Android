package com.smwrd.imgsearcher.domain.usecase

import com.smwrd.imgsearcher.domain.model.ImageResultModel
import com.smwrd.imgsearcher.domain.repository.ImageRepository
import javax.inject.Inject

class SearchImageUsecase @Inject constructor(
    private val repository: ImageRepository
) {
    suspend operator fun invoke(query: String, page: Int, size: Int) : ImageResultModel {
        return repository.searchImages(query, page, size)
    }
}