package com.example.candiformation.ui.screens.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate("home_root")
        viewModel.bottomBarShown.value = true
    }
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "스플래쉬 화면입니다", fontSize = 50.sp)
    }
}