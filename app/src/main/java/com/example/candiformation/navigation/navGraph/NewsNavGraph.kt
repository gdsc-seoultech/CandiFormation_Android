package com.example.candiformation.navigation.navGraph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.screens.news.NewsScreen
import com.example.candiformation.ui.screens.news.articles.ArticleScreen

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterialApi
fun NavGraphBuilder.newsNavGraph(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    navigation(
        startDestination = "news",
        route = "news_root"
    ) {
        composable(route = "news") {
            NewsScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = "news/articles/selectedArticle") {
            ArticleScreen(navController = navController, viewModel = viewModel)
        }
    }
}