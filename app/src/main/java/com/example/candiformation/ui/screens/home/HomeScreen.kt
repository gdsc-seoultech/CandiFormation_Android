package com.example.candiformation.ui.screens.home

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

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