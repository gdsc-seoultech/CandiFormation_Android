package com.example.candiformation.ui.screens.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.utils.Constants.CONTENT_INNER_PADDING
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = Color.LightGray)

    LaunchedEffect(key1 = true) {
        delay(300) // 테스트 거슬려서 줄여놨음
        navController.navigate("home_root") {
            popUpTo("splash") { inclusive = true }
        }
        viewModel.bottomBarShown.value = true
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(CONTENT_INNER_PADDING)
    ) {
        Text(text = "스플래쉬 화면입니다.\n용수형이 할 것임", fontSize = 28.sp)
    }
}