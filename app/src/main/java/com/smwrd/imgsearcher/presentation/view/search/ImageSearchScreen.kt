package com.smwrd.imgsearcher.presentation.view.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.smwrd.imgsearcher.domain.model.ImageModel
import com.smwrd.imgsearcher.presentation.util.openWebpage
import com.smwrd.imgsearcher.presentation.view.viewmodel.ImageViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@Composable
fun ImageSearchScreen(viewModel: ImageViewModel,
                      navController: NavHostController,
                      modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .filter { it != null }
            .distinctUntilChanged()
            .collect { index ->
                if (index == listState.layoutInfo.totalItemsCount - 1 && !viewModel.isLoading) {
                    viewModel.loadMoreImages()
                }
            }
    }
    Box(modifier = modifier.fillMaxSize(),) {
        Column {
            Row {
                TextField(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(0.5f),
                    value = viewModel.searchQuery,
                    onValueChange = { viewModel.searchQuery = it },
                    label = { Text("이미지 검색") },
                    placeholder = { Text("검색어를 입력하세요") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            coroutineScope.launch {
                                listState.scrollToItem(index = 0)
                            }
                            viewModel.searchImages()
                            keyboardController?.hide()
                        }
                    )
                )
                Button(onClick = {
                    coroutineScope.launch {
                        listState.scrollToItem(index = 0)
                    }
                    viewModel.searchImages()
                    keyboardController?.hide()
                },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 8.dp, end = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
            if(viewModel.imageResults.isNotEmpty()) {
                LazyColumn(state = listState) {
                    items(viewModel.imageResults) { image ->
                        ImageListItem(image, viewModel, navController)
                    }
                }
            }
        }
        if (viewModel.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ImageListItem(image: ImageModel, viewModel: ImageViewModel, navController: NavController) {
    val isFavorite = viewModel.isFavorite(image)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("detail/search/${viewModel.imageResults.indexOf(image)}")
            }
    ) {
        GlideImage (
            model = image.thumbnail,
            loading = placeholder(android.R.drawable.progress_indeterminate_horizontal),
            failure = placeholder(android.R.drawable.ic_delete),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Column(modifier = Modifier
            .weight(1f)
            .padding(start = 8.dp)) {
            Text(image.title, maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            if(image.title != image.description) {
                Text(image.description, maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 10.sp)
            }
        }
        IconButton(onClick = { openWebpage(context = navController.context, url = image.description) }) {
            Icon(imageVector = Icons.Default.Home,
                contentDescription = null,
                tint = Color.Blue
            )
        }
        IconButton(onClick = { openWebpage(context = navController.context, url = image.url) }) {
            Icon(imageVector = Icons.Default.Place,
                contentDescription = null,
                tint = Color.Blue
            )
        }
        IconButton(onClick = { viewModel.toggleFavorite(image) }) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null,
                tint = Color.Red
            )
        }
    }
}