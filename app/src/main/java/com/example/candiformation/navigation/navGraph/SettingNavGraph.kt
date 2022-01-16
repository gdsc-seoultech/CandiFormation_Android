package com.example.candiformation.navigation.navGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.screens.home.HomeScreen
import com.example.candiformation.ui.screens.setting.SettingScreen

fun NavGraphBuilder.settingNavGraph(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    navigation(
        startDestination = "setting",
        route = "setting_root"
    ) {
        composable(route = "setting") {
            SettingScreen()
        }
    }
}