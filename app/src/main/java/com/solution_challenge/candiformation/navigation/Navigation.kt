package com.solution_challenge.candiformation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.solution_challenge.candiformation.navigation.navGraph.*
import com.solution_challenge.candiformation.ui.SharedViewModel
import com.solution_challenge.candiformation.ui.screens.splash.SplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val ignoreRoutes = listOf<String>(
        "news/articles/selectedArticle",
        "splash",
        "profile/login/signup/service_usage",
        "profile/login/signup/private_info"
    )

    if (ignoreRoutes.contains(currentRoute)) {
        viewModel.bottomBarShown.value = false
    } else {
        viewModel.bottomBarShown.value = true
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = Color.Black)

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
        profileNavGraph(navController = navController, viewModel = viewModel)
    }
}