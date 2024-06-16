package com.smwrd.imgsearcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.smwrd.imgsearcher.presentation.view.main.MainScreen
import com.smwrd.imgsearcher.presentation.view.viewmodel.ImageViewModel
import com.smwrd.imgsearcher.ui.theme.ImageSearcherTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImageSearcherTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}