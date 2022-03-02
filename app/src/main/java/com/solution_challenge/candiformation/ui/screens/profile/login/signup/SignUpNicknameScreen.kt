package com.solution_challenge.candiformation.ui.screens.profile.login.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.solution_challenge.candiformation.components.CustomButton
import com.solution_challenge.candiformation.components.CustomTopAppBar
import com.solution_challenge.candiformation.ui.SharedViewModel
import com.solution_challenge.candiformation.ui.theme.VeryLightGrey_type2
import java.util.regex.Pattern

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
    var nicknameText = remember { mutableStateOf("") }
    var msg by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(36.dp))

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

        CustomButton(
            viewModel = viewModel,
            navController = navController,
            title = "회원가입 완료",
            widthDp = 140.dp,
            onClick = {
                if (nicknameText.value.isNullOrEmpty()) {
                    msg = "올바른 닉네임을 입력해주세요."
                } else if (nicknameText.value.length > 12 || nicknameText.value.length < 4) {
                    msg = "닉네임의 길이는 영문자 기준 4~12자 이내로 설정해주세요."
                } else {
                    viewModel.signUpBody.value.nickname = nicknameText.value
                    viewModel.signUp()
                    viewModel.login(
                        idText = viewModel.signUpBody.value.username,
                        passwordText = viewModel.signUpBody.value.password!!,
                        onSuccess = {
                            navController.navigate("profile") {
                                popUpTo("profile") { inclusive = true }
                            }
                        },
                        onFailure = {

                        }
                    )
                }
            }
        )
        Text(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 32.dp),
            text = msg,
            fontSize = 16.sp,
            color = Color.Red
        )
    }
}
