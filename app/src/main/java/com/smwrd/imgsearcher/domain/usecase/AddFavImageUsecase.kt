package com.smwrd.imgsearcher.domain.usecase

import com.smwrd.imgsearcher.domain.model.ImageModel
import com.smwrd.imgsearcher.domain.repository.ImageRepository
import javax.inject.Inject

class AddFavImageUsecase @Inject constructor(
    private val repository: ImageRepository
) {
    suspend operator fun invoke(image: ImageModel) = repository.addFavorite(image)
}