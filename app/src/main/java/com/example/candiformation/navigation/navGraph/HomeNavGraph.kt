package com.example.candiformation.navigation.navGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.screens.home.HomeScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    navigation(
        startDestination = "home",
        route = "home_root"
    ) {
        composable(route = "home") {
            HomeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}