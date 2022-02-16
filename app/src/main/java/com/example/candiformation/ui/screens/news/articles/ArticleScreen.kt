package com.example.candiformation.ui.screens.news.articles

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.utils.Constants
import com.example.candiformation.utils.Constants.CONTENT_INNER_PADDING

@ExperimentalMaterialApi
@Composable
fun ArticleScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            ArticleScreenTopAppBar(
                navController = navController,
                viewModel = viewModel
            )
        },
        content = {
            ArticleScreenContent(
                navController = navController,
                viewModel = viewModel
            )
        },
        bottomBar = {
            CommentTextField(viewModel = viewModel)
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun ArticleScreenContent(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = CONTENT_INNER_PADDING, end = CONTENT_INNER_PADDING, top = 12.dp)
    ) {
        articleTitle(viewModel = viewModel)
        ArticleTime()
        Spacer(modifier = Modifier.height(24.dp))
        articleContent(navController = navController, viewModel = viewModel)
        Spacer(modifier = Modifier.height(12.dp))
        ThumbnailBox(navController = navController, viewModel = viewModel)
        Spacer(modifier = Modifier.height(8.dp))
        LikeAndComments(viewModel = viewModel, likeIconClicked = {})
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
    }
}

@Composable
fun articleContent(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = viewModel.articleContent.value,
            fontSize = 18.sp
        )
    }
}

@Composable
fun articleTitle(
    viewModel: SharedViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = viewModel.articleTitle.value,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ArticleScreenTopAppBar(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "News",
                    fontSize = Constants.TOP_APP_BAR_FONT,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Arrow Back"
                )
            }
        },
        actions = {
            IconButton(
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.IosShare,
                    contentDescription = "Ios Share",
                    tint = Color.Black
                )
            }
        }
    )
}

@Composable
fun ArticleTime() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = "01/11 17:35",
            fontSize = 14.sp,
            color = Color.LightGray
        )
    }
}