package com.example.candiformation.ui.screens.setting.comments

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.candiformation.components.CustomTopAppBar
import com.example.candiformation.ui.SharedViewModel

@Composable
fun CommentsScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(navController = navController, title = "Comments", navBack = true)
        },
        content = {
            CommentsScreenContent()
        }
    )
}

@Composable
fun CommentsScreenContent() {

}