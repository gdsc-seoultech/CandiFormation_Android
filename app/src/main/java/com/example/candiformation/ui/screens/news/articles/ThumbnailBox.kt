package com.example.candiformation.ui.screens.news.articles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.candiformation.ui.SharedViewModel

@Composable
fun ThumbnailBox(
    navController: NavHostController,
    viewModel: SharedViewModel,
    url: String
) {
    Image(
        painter = rememberImagePainter(data = url),
        contentDescription = null,
        modifier = Modifier.size(128.dp)
    )
}