package com.smwrd.imgsearcher.data.model

data class ImageDocument(
    val collection: String,
    val datetime: String,
    val display_sitename: String,
    val doc_url: String,
    val image_url: String,
    val thumbnail_url: String,
    val width: Int,
    val height: Int
)