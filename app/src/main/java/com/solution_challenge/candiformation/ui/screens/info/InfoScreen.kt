package com.solution_challenge.candiformation.ui.screens.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.solution_challenge.candiformation.R
import com.solution_challenge.candiformation.components.CustomTopAppBar
import com.solution_challenge.candiformation.ui.SharedViewModel
import com.solution_challenge.candiformation.utils.Constants.CONTENT_INNER_PADDING

@Composable
fun InfoScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    var scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Information",
                navBack = false
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(horizontal = CONTENT_INNER_PADDING)
                    .fillMaxWidth()
            ) {
                InfoScreenContent(
                    navController = navController,
                    viewModel = viewModel,
                    scrollState = scrollState
                )
            }

        }
    )
}

@Composable
fun InfoScreenContent(
    navController: NavHostController,
    viewModel: SharedViewModel,
    scrollState: ScrollState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        InfoScreenMainLogo(
            navController = navController,
            viewModel = viewModel
        )
        InfoMainLogo(
            navController = navController,
            viewModel = viewModel
        )
    }
}

@Composable
fun InfoScreenMainLogo(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.dasun), contentDescription = null)
        Text(
            text = "D - ${viewModel.getLeftBonTime()}", // 2022년 3월 9일 D -
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "(사전투표 : D - ${viewModel.getLeftSazunTime()})",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}