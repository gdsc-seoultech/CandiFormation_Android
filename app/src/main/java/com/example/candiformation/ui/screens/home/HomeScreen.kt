package com.example.candiformation.ui.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel
import java.time.LocalDate

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    HomeScreenContent(
        navController = navController,
        viewModel = viewModel
    )
}

@Composable
fun HomeScreenContent(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "20대 대선",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "사전투표",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "2022년 3월 5일",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "본 투표",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "2022년 3월 9일",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }
}