package com.example.candiformation.ui.screens.home


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.candiformation.ui.SharedViewModel
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val scope = rememberCoroutineScope()
    scope.launch {
        viewModel.getArticle()
    }

    Scaffold(
        topBar = {},
        content = {
//            Image(
//                painter = ,
//                contentDescription = "Thumbnail Image",
//                modifier = Modifier.size(256.dp)
//            )
        }
    )
}

@Composable
fun HomeScreenContent(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "20대 대선",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "사전 투표",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "2022년 3월 5일 D - ${viewModel.getLeftSazunTime()}",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
        Text(
            text = "본 투표",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "2022년 3월 9일 D - ${viewModel.getLeftBonTime()}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
    }
}

@Composable
fun MainArticles() {

}