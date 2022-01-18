package com.example.candiformation.navigation.navGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.screens.candidate.CandidateScreen

fun NavGraphBuilder.candidateNavGraph(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    navigation(
        startDestination = "candidate",
        route = "candidate_root"
    ) {
        composable(route = "candidate") {
            CandidateScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}