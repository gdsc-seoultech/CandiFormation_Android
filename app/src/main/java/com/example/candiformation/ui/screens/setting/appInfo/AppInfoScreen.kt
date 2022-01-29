package com.example.candiformation.ui.screens.setting.appInfo

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel

@Composable
fun AppInfoScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Text("준비중입니다.", fontSize = 50.sp)
}