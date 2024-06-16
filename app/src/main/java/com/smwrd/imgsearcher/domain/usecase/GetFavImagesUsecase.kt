package com.smwrd.imgsearcher.domain.usecase

import com.smwrd.imgsearcher.domain.model.ImageModel
import com.smwrd.imgsearcher.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavImagesUsecase @Inject constructor(
    private val repository: ImageRepository
) {
    operator fun invoke() : Flow<List<ImageModel>> = repository.getAllFavorites()
}