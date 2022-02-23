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
import com.example.candiformation.components.CustomTopAppBar
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.utils.Constants

@Composable
fun SignUpNicknameScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "회원가입", 
                navBack = false
            )
        },
        content = {
            SignUpNicknameScreenContent(
                navController = navController,
                viewModel = viewModel
            )
        }
    )
}

@Composable
fun SignUpNicknameScreenContent(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    var nicknameText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "인증완료. 닉네임 설정하고 버튼 눌러야 SignUp 실행")
        TextField(
            value = nicknameText,
            onValueChange = {
                nicknameText = it
            },
            placeholder = {
                Text("닉네임")
            }
        )
        Button(onClick = {
            viewModel.signUpBody.value.nickname = nicknameText
            viewModel.signUp()
        }) {
            Text("회원가입")
        }
    }
}
