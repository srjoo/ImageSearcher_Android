package com.smwrd.imgsearcher.data.repository.local

import com.smwrd.imgsearcher.data.db.FavoriteImageDao
import com.smwrd.imgsearcher.data.mapper.fromFavoriteImageEntity
import com.smwrd.imgsearcher.data.mapper.toFavoriteImageEntity
import com.smwrd.imgsearcher.domain.model.ImageModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ImageLocalDataSourceImpl @Inject constructor(
    private val imageDao: FavoriteImageDao
) : ImageLocalDataSource {
    override suspend fun addFavorite(image: ImageModel) {
        imageDao.addFavorite(toFavoriteImageEntity(image))
    }

    override suspend fun removeFavorite(image: ImageModel) {
        imageDao.removeFavorite(toFavoriteImageEntity(image))
    }

    override fun getAllFavorites(): Flow<List<ImageModel>> {
        return imageDao.getAllFavorites().map { it.map {e -> fromFavoriteImageEntity(e) }.toList() }
    }
}