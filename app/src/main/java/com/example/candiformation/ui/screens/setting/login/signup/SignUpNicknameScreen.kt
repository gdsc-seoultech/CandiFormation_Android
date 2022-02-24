package com.example.candiformation.ui.screens.setting.login.signup

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.components.CustomButton
import com.example.candiformation.components.CustomTopAppBar
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.theme.VeryLightGrey_type2
import com.example.candiformation.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SignUpNicknameScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Sign Up",
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
    val scope = rememberCoroutineScope()
    var nicknameText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "인증이 완료되었습니다.",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .height(50.dp)
                .width(270.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(2.dp))
                .background(Color.Black),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = nicknameText.value,
                onValueChange = { nicknameText.value = it },
                shape = RoundedCornerShape(0.dp),
                placeholder = { Text("닉네임") },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.LightGray,
                    placeholderColor = VeryLightGrey_type2
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

//        TextField(
//            value = nicknameText,
//            onValueChange = {
//                nicknameText = it
//            },
//            placeholder = {
//                Text("닉네임")
//            }
//        )


        CustomButton(
            viewModel = viewModel,
            navController = navController,
            title = "회원가입 완료",
            widthDp = 140.dp,
            onClick = {
                scope.launch {
                    viewModel.signUpBody.value.nickname = nicknameText.value
                    viewModel.signUp()
                    viewModel.login(
                        idText = viewModel.signUpBody.value.username,
                        passwordText = viewModel.signUpBody.value.password!!,
                        onSuccess = {},
                        onFailure = {}
                    )
                    navController.navigate("setting") {
                        popUpTo("setting") { inclusive = true }
                    }
                }
            }
        )
    }
}
