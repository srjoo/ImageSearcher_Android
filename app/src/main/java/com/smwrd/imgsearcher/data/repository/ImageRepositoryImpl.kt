package com.smwrd.imgsearcher.data.repository

import com.smwrd.imgsearcher.data.repository.local.ImageLocalDataSource
import com.smwrd.imgsearcher.data.repository.remote.ImageRemoteDataSource
import com.smwrd.imgsearcher.domain.model.ImageModel
import com.smwrd.imgsearcher.domain.model.ImageResultModel
import com.smwrd.imgsearcher.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val localSource: ImageLocalDataSource,
    private val remoteSource: ImageRemoteDataSource
) : ImageRepository {

    override suspend fun searchImages(query: String, page: Int, size: Int): ImageResultModel = remoteSource.searchImages(query, page, size)

    override suspend fun addFavorite(image: ImageModel) = localSource.addFavorite(image)

    override suspend fun removeFavorite(image: ImageModel) = localSource.removeFavorite(image)

    override fun getAllFavorites(): Flow<List<ImageModel>> = localSource.getAllFavorites()
}