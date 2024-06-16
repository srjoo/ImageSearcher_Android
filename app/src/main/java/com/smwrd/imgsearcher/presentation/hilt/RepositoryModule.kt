package com.smwrd.imgsearcher.presentation.view.hilt

import android.content.Context
import androidx.room.Room
import com.smwrd.imgsearcher.data.api.KakaoApiService
import com.smwrd.imgsearcher.data.db.FavoriteImageDatabase
import com.smwrd.imgsearcher.data.repository.ImageRepositoryImpl
import com.smwrd.imgsearcher.data.repository.local.ImageLocalDataSource
import com.smwrd.imgsearcher.data.repository.local.ImageLocalDataSourceImpl
import com.smwrd.imgsearcher.data.repository.remote.ImageRemoteDataSource
import com.smwrd.imgsearcher.data.repository.remote.ImageRemoteDataSourceImpl
import com.smwrd.imgsearcher.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideImageRepository(localDataSource: ImageLocalDataSource, remoteDataSource: ImageRemoteDataSource): ImageRepository
    = ImageRepositoryImpl(localDataSource, remoteDataSource)

    @Singleton
    @Provides
    fun provideImageLocalDataSource(db : FavoriteImageDatabase): ImageLocalDataSource
    = ImageLocalDataSourceImpl(db.favoriteImageDao())

    @Singleton
    @Provides
    fun provideFavoriteImageDatabase(@ApplicationContext context: Context): FavoriteImageDatabase {
        return Room.databaseBuilder(
            context,
            FavoriteImageDatabase::class.java,
            "fav_images.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideImageRemoteDataSource(api : KakaoApiService): ImageRemoteDataSource
    = ImageRemoteDataSourceImpl(api)
}