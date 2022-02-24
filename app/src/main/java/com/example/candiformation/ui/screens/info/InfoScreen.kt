package com.example.candiformation.ui.screens.info

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.candiformation.R
import com.example.candiformation.components.CustomTopAppBar
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
            CustomTopAppBar(
                navController = navController,
                title = "Information",
                navBack = false
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
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.election_visual),
                contentDescription = null,
                modifier = Modifier.size(200.dp),
                alpha = 0.5f
            )
        }
        InfoMainLogo(
            navController = navController,
            viewModel = viewModel
        )

    }
}