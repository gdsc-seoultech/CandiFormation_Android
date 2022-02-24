package com.example.candiformation.navigation.navGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.screens.info.InfoScreen
import com.example.candiformation.ui.screens.info.PresidentsArchive

fun NavGraphBuilder.infoNavGraph(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    navigation(
        startDestination = "info",
        route = "info_root"
    ) {
        composable(route = "info") {
            InfoScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = "info/presidentArchive"){
            PresidentsArchive(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}