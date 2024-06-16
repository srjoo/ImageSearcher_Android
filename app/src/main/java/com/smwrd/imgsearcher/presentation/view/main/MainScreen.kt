package com.smwrd.imgsearcher.presentation.view.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.smwrd.imgsearcher.domain.model.ImageModel
import com.smwrd.imgsearcher.presentation.view.detail.ImageDetailScreen
import com.smwrd.imgsearcher.presentation.view.favorite.FavoriteImagesScreen
import com.smwrd.imgsearcher.presentation.view.search.ImageSearchScreen
import com.smwrd.imgsearcher.presentation.view.viewmodel.ImageViewModel

@Composable
fun MainScreen(viewModel: ImageViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentRoute(navController) == "search",
                    onClick = { navController.navigate("search") },
                    label = { Text("검색") },
                    icon = { Icon(Icons.Default.Search, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = currentRoute(navController) == "favorites",
                    onClick = { navController.navigate("favorites") },
                    label = { Text("즐겨찾기") },
                    icon = { Icon(Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Color.Red
                    ) }
                )
            }
        }
    ) {
        NavHost(navController, startDestination = "search", modifier = Modifier.padding(it)) {
            composable("search") { ImageSearchScreen(viewModel, navController) }
            composable("favorites") { FavoriteImagesScreen(viewModel, navController) }
            composable(
                "detail/{imageType}/{imageUrl}",
                arguments = listOf(navArgument("imageType") {type = NavType.StringType}, navArgument("imageUrl") { type = NavType.IntType })
            ) { backStackEntry ->
                var imageType = backStackEntry.arguments?.getString("imageType")
                val imageIndex = backStackEntry.arguments?.getInt("imageUrl", -1)
                var image:ImageModel? = null
                if(imageIndex != null && imageIndex > -1) {
                    when(imageType) {
                        "search" -> if(viewModel.imageResults.size > imageIndex) image = viewModel.imageResults[imageIndex]
                        "favorite" -> if(viewModel.favoriteImages.size > imageIndex) image = viewModel.favoriteImages[imageIndex]
                    }
                }
                ImageDetailScreen(image, navController)
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

