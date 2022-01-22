package com.example.candiformation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.candiformation.navigation.navGraph.*
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.screens.splash.SplashScreen

@Composable
fun SetupNavigation(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        infoNavGraph(navController = navController, viewModel = viewModel)
        candidateNavGraph(navController = navController, viewModel = viewModel)
        homeNavGraph(navController = navController, viewModel = viewModel)
        newsNavGraph(navController = navController, viewModel = viewModel)
        settingNavGraph(navController = navController, viewModel = viewModel)
    }
}