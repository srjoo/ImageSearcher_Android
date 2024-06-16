package com.smwrd.imgsearcher.presentation.view.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.smwrd.imgsearcher.domain.model.ImageModel
import com.smwrd.imgsearcher.presentation.view.viewmodel.ImageViewModel

@Composable
fun FavoriteImagesScreen(viewModel: ImageViewModel, navController: NavHostController) {
    LazyColumn {
        items(viewModel.favoriteImages) { favorite ->
            FavoriteImageItem(favorite, viewModel, navController)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FavoriteImageItem(
    favorite: ImageModel,
    viewModel: ImageViewModel,
    navController: NavHostController
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("detail/favorite/${viewModel.favoriteImages.indexOf(favorite)}")
            }
    ) {
        GlideImage (
            model = favorite.thumbnail,
            loading = placeholder(android.R.drawable.progress_indeterminate_horizontal),
            failure = placeholder(android.R.drawable.ic_delete),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Text(favorite.title, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f).padding(start = 8.dp))
        IconButton(onClick = { viewModel.toggleFavorite(favorite) }) {
            Icon(imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = Color.Red
            )
        }
    }
}