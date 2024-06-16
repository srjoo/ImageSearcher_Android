package com.smwrd.imgsearcher.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.smwrd.imgsearcher.data.model.FavoriteImageEntity

@Database(entities = [FavoriteImageEntity::class], version = 1)
abstract class FavoriteImageDatabase : RoomDatabase() {
    abstract fun favoriteImageDao(): FavoriteImageDao
}