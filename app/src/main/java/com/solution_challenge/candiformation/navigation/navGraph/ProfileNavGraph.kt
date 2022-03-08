package com.solution_challenge.candiformation.navigation.navGraph

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.solution_challenge.candiformation.ui.SharedViewModel
import com.solution_challenge.candiformation.ui.screens.profile.ProfileScreen
import com.solution_challenge.candiformation.ui.screens.profile.appInfo.AppInfoScreen
import com.solution_challenge.candiformation.ui.screens.profile.comments.CommentsScreen
import com.solution_challenge.candiformation.ui.screens.profile.like.LikeScreen
import com.solution_challenge.candiformation.ui.screens.profile.login.LoginScreen
import com.solution_challenge.candiformation.ui.screens.profile.login.signup.*
import com.solution_challenge.candiformation.ui.screens.profile.version.VersionLogScreen

@ExperimentalMaterialApi
fun NavGraphBuilder.profileNavGraph(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    navigation(
        startDestination = "profile",
        route = "profile_root"
    ) {
        composable(route = "profile") {
            ProfileScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = "profile/appInfo") {
            AppInfoScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = "profile/login") {
            LoginScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = "profile/version") {
            VersionLogScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = "profile/login/signup") {
            SignUpScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = "profile/login/signup/auth") {
            SignUpAuthScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = "profile/login/signup/setnickname") {
            SignUpNicknameScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = "profile/like") {
            LikeScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = "profile/comments") {
            CommentsScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = "profile/login/signup/service_usage") {
            SignUpServiceUsage(navController = navController, viewModel = viewModel)
        }
        composable(route = "profile/login/signup/private_info") {
            SignUpPrivateInfo(navController = navController, viewModel = viewModel)
        }

    }
}