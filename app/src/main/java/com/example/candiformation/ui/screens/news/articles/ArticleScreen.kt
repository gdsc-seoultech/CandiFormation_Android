package com.example.candiformation.ui.screens.news.articles

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel

@Composable
fun ArticleScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {

    Text("article id : ${viewModel.articleId.value}")
}