package com.smwrd.imgsearcher.domain.usecase

import com.smwrd.imgsearcher.domain.model.ImageModel
import com.smwrd.imgsearcher.domain.repository.ImageRepository
import javax.inject.Inject

class RemoveFavImageUsecase @Inject constructor(
    private val repository: ImageRepository
) {
    suspend operator fun invoke(image: ImageModel) = repository.removeFavorite(image)
}