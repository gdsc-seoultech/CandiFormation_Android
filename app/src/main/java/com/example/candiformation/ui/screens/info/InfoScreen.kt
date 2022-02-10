package com.example.candiformation.ui.screens.info

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.utils.Constants
import com.example.candiformation.utils.Constants.CONTENT_INNER_PADDING

@Composable
fun InfoScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {

    var scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            InfoScreenTopAppBar(
                navController = navController,
                scrollState = scrollState
            )
        },
        content = {
            Column(
                modifier = Modifier.padding(horizontal = CONTENT_INNER_PADDING)
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
        InfoMainLogo()
    }
}

@Composable
fun InfoScreenTopAppBar(
    navController: NavHostController,
    scrollState: ScrollState
) {
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Text(
                text = "정보",
                fontSize = Constants.TOP_APP_BAR_FONT,
                fontWeight = FontWeight.Bold
            )
        }
    )
}