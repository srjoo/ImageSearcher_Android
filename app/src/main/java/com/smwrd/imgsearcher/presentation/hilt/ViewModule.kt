package com.smwrd.imgsearcher.presentation.view.hilt

import com.smwrd.imgsearcher.domain.usecase.AddFavImageUsecase
import com.smwrd.imgsearcher.domain.usecase.GetFavImagesUsecase
import com.smwrd.imgsearcher.domain.usecase.RemoveFavImageUsecase
import com.smwrd.imgsearcher.domain.usecase.SearchImageUsecase
import com.smwrd.imgsearcher.presentation.view.viewmodel.ImageViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ViewModule {
    @ActivityScoped
    @Provides
    fun provideImageViewModel(
        searchImageUsecase: SearchImageUsecase,
        addFavImageUsecase: AddFavImageUsecase,
        removeFavImageUsecase: RemoveFavImageUsecase,
        getFavImagesUsecase: GetFavImagesUsecase
    ): ImageViewModel
    = ImageViewModel(searchImageUsecase, addFavImageUsecase, removeFavImageUsecase, getFavImagesUsecase)
}