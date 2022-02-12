package com.example.candiformation.ui.screens.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.candiformation.ui.SharedViewModel


@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        HomeScreenContent(
            navController = navController,
            viewModel = viewModel
        )

        Text("서비스 준비중 : 주변 투표소 정보", fontSize = 20.sp)



        // 웹뷰로 주변 투표소 보여줄 계획
//        HomeWebView(
//            url = "https://velog.io/@suee97"
//        )
    }
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