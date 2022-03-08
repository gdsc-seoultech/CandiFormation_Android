package com.solution_challenge.candiformation.ui.screens.profile.login.signup

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SignUpAuthScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    viewModel.setAuthInitEnabled()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Sign Up",
                navBack = true
            )
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
    val scope = rememberCoroutineScope()
    val isVerified by viewModel.isVerified.observeAsState()
    var tempVerifyCode = remember { mutableStateOf("") }

    val authNextButtonEnabled by viewModel.authNextButtonEnabled.observeAsState()
    val authTextFieldEnabled by viewModel.authTextFieldEnabled.observeAsState()

    val authMsg by viewModel.authMsg.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${viewModel.authEmail.value} ",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "로 인증코드를 보냈습니다.",
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .height(50.dp)
                .width(270.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(2.dp))
                .background(Color.Black),
            verticalAlignment = Alignment.CenterVertically
        ) {
            authTextFieldEnabled?.let {
                TextField(
                    value = tempVerifyCode.value,
                    onValueChange = { tempVerifyCode.value = it },
                    shape = RoundedCornerShape(0.dp),
                    placeholder = { Text("인증코드") },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.LightGray,
                        placeholderColor = VeryLightGrey_type2
                    ),
                    enabled = it
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))

        authNextButtonEnabled?.let {
            CustomButton(
                viewModel = viewModel,
                navController = navController,
                title = "인증 확인",
                widthDp = 180.dp,
                onClick = {
                    if(tempVerifyCode.value.isBlank()) {
                        viewModel.setAuthMsg("인증코드를 입력해주세요.")
                    } else {
                        viewModel.verifyCode(tempCode = tempVerifyCode.value)
                    }
                },
                enabled = !it
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        authNextButtonEnabled?.let {
            CustomButton(
                viewModel = viewModel,
                navController = navController,
                title = "다음",
                widthDp = 180.dp,
                onClick = {
                    navController.navigate("profile/login/signup/setnickname") {
                        popUpTo(route = "profile/login/signup/setnickname") { inclusive = true }
                    }
                },
                enabled = it
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        authMsg?.let {
            Text(
                text = it
            )
        }
    }
}
