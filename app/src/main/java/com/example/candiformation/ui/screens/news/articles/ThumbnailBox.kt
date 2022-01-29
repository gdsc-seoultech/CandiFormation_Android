package com.example.candiformation.ui.screens.news.articles

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.candiformation.ui.SharedViewModel

@ExperimentalMaterialApi
@Composable
fun ThumbnailBox(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val uriHandler = LocalUriHandler.current

    Surface(
        color = Color.Magenta,
        onClick = {
            uriHandler.openUri(viewModel.articleLink.value)
        }
    ) {
        Text("링크 이동", fontSize = 30.sp)
    }
}
