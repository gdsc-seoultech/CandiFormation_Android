package com.example.candiformation.ui.screens.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HowToVote
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
        delay(1000) // 테스트 거슬려서 줄여놨음
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
        Row(modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        )
        {
            Icon(imageVector = Icons.Default.HowToVote,
                contentDescription = "Candiformation_Icon",
                tint = Color.Red,
                modifier = Modifier.size(90.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Candidate +",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Information",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Candiformation",
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}