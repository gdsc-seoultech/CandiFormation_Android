package com.example.candiformation.ui.screens.setting.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.utils.Constants

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            LoginScreenTopAppBar(navController = navController)
        },
        content = {
            LoginScreenContent(
                navController = navController,
                viewModel = viewModel
            )
        }
    )
}

@Composable
fun LoginScreenContent(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    var idText by remember { mutableStateOf("") }
    var pwdText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = idText,
            onValueChange = {
                idText = it
            },
            placeholder = {
                Text("아이디")
            }
        )
        TextField(
            value = pwdText,
            onValueChange = {
                pwdText = it
            },
            placeholder = {
                Text("비밀번호")
            }
        )
        Button(onClick = { /*TODO*/ }) {
            Text("로그인")
        }
        Button(onClick = {
            navController.navigate("setting/login/signup")
        }) {
            Text("회원가입")
        }
    }
}

@Composable
fun LoginScreenTopAppBar(
    navController: NavHostController
) {
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Text(
                text = "로그인",
                fontSize = Constants.TOP_APP_BAR_FONT,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate("setting") {
                    popUpTo("setting") { inclusive = true }
                }
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Arrow Back")
            }
        }
    )
}