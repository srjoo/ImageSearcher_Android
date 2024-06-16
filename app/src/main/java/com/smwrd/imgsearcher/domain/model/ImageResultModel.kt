package com.smwrd.imgsearcher.domain.model

data class ImageResultModel(
    val isEnd: Boolean,
    val results:List<ImageModel>
)