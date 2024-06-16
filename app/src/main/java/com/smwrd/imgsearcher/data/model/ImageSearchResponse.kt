package com.smwrd.imgsearcher.data.model

data class ImageSearchResponse(
    val meta: ImageSearchMeta,
    val documents: List<ImageDocument>
)