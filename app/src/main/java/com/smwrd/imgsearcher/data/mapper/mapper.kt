package com.smwrd.imgsearcher.data.mapper

import com.smwrd.imgsearcher.data.model.FavoriteImageEntity
import com.smwrd.imgsearcher.data.model.ImageDocument
import com.smwrd.imgsearcher.domain.model.ImageModel

fun fromFavoriteImageEntity(fimg:FavoriteImageEntity) : ImageModel = ImageModel(fimg.url, fimg.thumbnail, fimg.title, fimg.description)
fun toFavoriteImageEntity(img:ImageModel) : FavoriteImageEntity = FavoriteImageEntity(img.url, img.thumbnail, img.title, img.description)
fun fromImageDocument(dimg: ImageDocument) : ImageModel = ImageModel(dimg.image_url, dimg.thumbnail_url,
    dimg.display_sitename.ifEmpty { dimg.doc_url }, dimg.doc_url)