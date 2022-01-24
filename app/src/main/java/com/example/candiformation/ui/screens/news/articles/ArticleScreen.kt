package com.example.candiformation.ui.screens.news.articles

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.utils.Constants

@Composable
fun ArticleScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {

    Scaffold(
        topBar = {
            ArticleScreenTopAppBar(navController = navController)
        },
        content = {
            ArticleScreenContent(
                navController = navController,
                viewModel = viewModel
            )
        }
    )


}

@Composable
fun ArticleScreenContent(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    Column {
        Text(currentRoute.toString())
        Text(viewModel.articleId.value.toString())
        Divider()
        Text(viewModel.articleTitle.value.toString())
        Divider()

        Text(viewModel.articleContent.value.toString())
    }
}

@Composable
fun ArticleScreenTopAppBar(
    navController: NavHostController
) {
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Text(
                text = "Article",
                fontSize = Constants.TOP_APP_BAR_FONT,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate("news") {
                    popUpTo("news") { inclusive = true }
                }
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Arrow Back")
            }
        }
    )
}