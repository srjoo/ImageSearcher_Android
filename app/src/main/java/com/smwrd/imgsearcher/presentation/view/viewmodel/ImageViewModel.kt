package com.smwrd.imgsearcher.presentation.view.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smwrd.imgsearcher.domain.model.ImageModel
import com.smwrd.imgsearcher.domain.usecase.AddFavImageUsecase
import com.smwrd.imgsearcher.domain.usecase.GetFavImagesUsecase
import com.smwrd.imgsearcher.domain.usecase.RemoveFavImageUsecase
import com.smwrd.imgsearcher.domain.usecase.SearchImageUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val searchImageUsecase: SearchImageUsecase,
    private val addFavUsecase: AddFavImageUsecase,
    private val removeFavImageUsecase: RemoveFavImageUsecase,
    private val getFavImagesUsecase: GetFavImagesUsecase
) : ViewModel() {

    var searchQuery by mutableStateOf("")
    var imageResults by mutableStateOf(emptyList<ImageModel>())
    var favoriteImages by mutableStateOf(emptyList<ImageModel>())
    var isLoading by mutableStateOf(false)
        private set

    private var currentPage = 1
    private var isEnd = false

    init {
        viewModelScope.launch {
            favoriteImages = getFavImagesUsecase().first()
        }
    }

    private fun loadImages() {
        if(!isLoading && searchQuery.isNotEmpty()) {
            isLoading = true
            viewModelScope.launch {
                val response = searchImageUsecase(searchQuery, currentPage, 20)
                imageResults = imageResults + response.results
                isEnd = response.isEnd
                isLoading = false
            }
        }
    }

    fun searchImages() {
        if(!isLoading) {
            currentPage = 1
            imageResults = emptyList()
            isEnd = false
            loadImages()
        }
    }

    fun loadMoreImages() {
        if (!isLoading && !isEnd) {
            currentPage++
            loadImages()
        }
    }

    fun toggleFavorite(image: ImageModel) {
        viewModelScope.launch {
            if (isFavorite(image)) {
                favoriteImages = favoriteImages.toMutableList().apply { remove(image) }
                removeFavImageUsecase(image)
            } else {
                favoriteImages = favoriteImages.toMutableList().apply { add(image) }
                addFavUsecase(image)
            }
        }
    }

    fun isFavorite(image: ImageModel): Boolean {
        return favoriteImages.any { it.url == image.url }
    }
}