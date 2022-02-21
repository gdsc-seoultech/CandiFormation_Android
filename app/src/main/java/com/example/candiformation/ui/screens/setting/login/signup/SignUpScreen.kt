package com.example.candiformation.ui.screens.setting.login.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.components.CustomTopAppBar
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.utils.Constants
import java.util.regex.Pattern

@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(navController = navController, title = "회원가입", navBack = true)
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

    val pattern: Pattern = android.util.Patterns.EMAIL_ADDRESS

    var signUpMsg by remember { mutableStateOf("") }

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

        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = signUpMsg,
            fontSize = 16.sp,
            color = Color.Red
        )

        Button(onClick = {
            if(emailText.isBlank() || pwdText.isBlank()) {
                signUpMsg = "이메일 또는 비밀번호를 작성해주세요."
            } else if (!pattern.matcher(emailText).matches()) {
                signUpMsg = "올바른 이메일 형식이 아닙니다."
            } else {
                viewModel.signUpBody.value.username = emailText
                viewModel.signUpBody.value.password = pwdText

                // 이메일 인증
                viewModel.tempEmail.value = emailText
                viewModel.emailAuth(viewModel.tempEmail.value)

                navController.navigate("setting/login/signup/auth") {
                    popUpTo(route = "setting/login/signup/auth") { inclusive = true }
                }
            }
        }) {
            Text("인증하기")
        }
    }
}


