package com.example.candiformation.ui.screens.setting.login.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.utils.Constants

@Composable
fun SignUpAuthScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            SignUpAuthScreenTopAppBar(navController = navController)
        },
        content = {
            SignUpAuthScreenContent(
                navController = navController,
                viewModel = viewModel
            )
        }
    )
}

@Composable
fun SignUpAuthScreenContent(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text("인증 된다 치고", fontSize = 30.sp)
        Button(onClick = {
            navController.navigate("setting/login/signup/setnickname")
        }) {
            Text("다음")
        }
    }
}

@Composable
fun SignUpAuthScreenTopAppBar(
    navController: NavHostController
) {
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Text(
                text = "회원가입",
                fontSize = Constants.TOP_APP_BAR_FONT,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate("setting/login/signup") {
                    popUpTo("setting/signup") { inclusive = true }
                }
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Arrow Back")
            }
        }
    )
}