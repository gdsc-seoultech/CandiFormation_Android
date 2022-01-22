package com.example.candiformation.ui.screens.setting.login.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.utils.Constants

@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            SignUpScreenTopAppBar(navController = navController)
        },
        content = {
            SignUpScreenContent(
                navController = navController,
                viewModel = viewModel
            )
        }
    )
}

@Composable
fun SignUpScreenContent(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    var emailText by remember { mutableStateOf("") }
    var pwdText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = emailText,
            onValueChange = {
                emailText = it
            },
            placeholder = {
                Text("이메일")
            }
        )
        TextField(
            value = pwdText,
            onValueChange = {
                pwdText = it
            },
            placeholder = {
                Text("패스워드")
            }
        )
        Button(onClick = {
            navController.navigate("setting/login/signup/auth")
        }) {
            Text("다음")
        }
    }
}

@Composable
fun SignUpScreenTopAppBar(
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
                navController.navigate("setting/login") {
                    popUpTo("setting/login") { inclusive = true }
                }
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Arrow Back")
            }
        }
    )
}

