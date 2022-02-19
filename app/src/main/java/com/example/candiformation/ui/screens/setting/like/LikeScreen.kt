package com.example.candiformation.ui.screens.setting.like

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.candiformation.components.CustomTopAppBar
import com.example.candiformation.ui.SharedViewModel

@Composable
fun LikeScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(navController = navController, title = "Likes", navBack = true)
        },
        content = {
            LikeScreenContent()
        }
    )
}

@Composable
fun LikeScreenContent() {

}