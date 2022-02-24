package com.solution_challenge.candiformation.navigation.navGraph

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.solution_challenge.candiformation.ui.SharedViewModel
import com.solution_challenge.candiformation.ui.screens.setting.SettingScreen
import com.solution_challenge.candiformation.ui.screens.setting.appInfo.AppInfoScreen
import com.solution_challenge.candiformation.ui.screens.setting.comments.CommentsScreen
import com.solution_challenge.candiformation.ui.screens.setting.like.LikeScreen
import com.solution_challenge.candiformation.ui.screens.setting.login.LoginScreen
import com.solution_challenge.candiformation.ui.screens.setting.login.signup.SignUpAuthScreen
import com.solution_challenge.candiformation.ui.screens.setting.login.signup.SignUpNicknameScreen
import com.solution_challenge.candiformation.ui.screens.setting.login.signup.SignUpScreen

@ExperimentalMaterialApi
fun NavGraphBuilder.settingNavGraph(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    navigation(
        startDestination = "setting",
        route = "setting_root"
    ) {
        composable(route = "setting") {
            SettingScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = "setting/appInfo") {
            AppInfoScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = "setting/login") {
            LoginScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = "setting/login/signup") {
            SignUpScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = "setting/login/signup/auth") {
            SignUpAuthScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = "setting/login/signup/setnickname") {
            SignUpNicknameScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = "setting/like") {
            LikeScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = "setting/comments") {
            CommentsScreen(navController = navController, viewModel = viewModel)
        }

    }
}