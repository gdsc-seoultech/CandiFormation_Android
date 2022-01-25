package com.example.candiformation.ui.screens.news.articles

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.utils.Constants

@Composable
fun ArticleScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {

    Scaffold(
        topBar = {
            ArticleScreenTopAppBar(navController = navController)
        },
        content = {
            ArticleScreenContent(
                navController = navController,
                viewModel = viewModel
            )
        }
    )


}

@Composable
fun ArticleScreenContent(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        articleTitle(viewModel = viewModel)

    }
}

@Composable
fun articleTitle(
    viewModel: SharedViewModel
) {
    Box(
        modifier = Modifier.
                fillMaxWidth()
    ) {
        Text(
            text = viewModel.articleTitle.value,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 2
        )
    }
}

@Composable
fun ArticleScreenTopAppBar(
    navController: NavHostController
) {
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Text(
                text = "Article",
                fontSize = Constants.TOP_APP_BAR_FONT,
                fontWeight = FontWeight.Bold
            )

        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate("news") {
                    popUpTo("news") { inclusive = true }
                }
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Arrow Back")
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.IosShare, contentDescription = "Ios Share")
            }
        }
    )
}