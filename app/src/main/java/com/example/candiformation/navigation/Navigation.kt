package com.example.candiformation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.candiformation.navigation.navGraph.*
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.screens.splash.SplashScreen

@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    if (currentRoute == "news/articles/selectedArticle" || currentRoute == "splash") {
        viewModel.bottomBarShown.value = false
    } else {
        viewModel.bottomBarShown.value = true
    }

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