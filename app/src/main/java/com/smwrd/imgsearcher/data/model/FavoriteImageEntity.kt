package com.smwrd.imgsearcher.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteImageEntity(
    @PrimaryKey
    val url: String,
    val thumbnail: String,
    val title: String,
    val description: String
)