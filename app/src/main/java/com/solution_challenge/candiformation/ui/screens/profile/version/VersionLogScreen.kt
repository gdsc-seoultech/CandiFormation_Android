package com.solution_challenge.candiformation.ui.screens.profile.version

import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.solution_challenge.candiformation.components.CustomTopAppBar
import com.solution_challenge.candiformation.ui.SharedViewModel

@Composable
fun VersionLogScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Version Log",
                navBack = true
            )
        },
        content = {
            VersionLogScreenContent()
        }
    )
}

@Composable
fun VersionLogScreenContent() {
    VersionLogoCard(
        title = "1.1.0"
    )
}

@Composable
fun VersionLogoCard(
    title: String
) {
    Card() {
        Text(
            text = title
        )
    }
}