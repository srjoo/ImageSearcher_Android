package com.smwrd.imgsearcher.presentation.view.hilt

import com.smwrd.imgsearcher.domain.repository.ImageRepository
import com.smwrd.imgsearcher.domain.usecase.AddFavImageUsecase
import com.smwrd.imgsearcher.domain.usecase.GetFavImagesUsecase
import com.smwrd.imgsearcher.domain.usecase.RemoveFavImageUsecase
import com.smwrd.imgsearcher.domain.usecase.SearchImageUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UsecaseModule {
    @Singleton
    @Provides
    fun provideSearchImageUsecase(repository: ImageRepository) : SearchImageUsecase = SearchImageUsecase(repository)

    @Singleton
    @Provides
    fun provideAddFavImageUsecase(repository: ImageRepository) : AddFavImageUsecase = AddFavImageUsecase(repository)

    @Singleton
    @Provides
    fun provideRemoveFavImageUsecase(repository: ImageRepository) : RemoveFavImageUsecase = RemoveFavImageUsecase(repository)

    @Singleton
    @Provides
    fun provideGetFavImagesUsecase(repository: ImageRepository) : GetFavImagesUsecase = GetFavImagesUsecase(repository)
}