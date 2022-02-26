package com.solution_challenge.candiformation.ui.screens.setting.login.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.solution_challenge.candiformation.components.CustomButton
import com.solution_challenge.candiformation.components.CustomTopAppBar
import com.solution_challenge.candiformation.ui.SharedViewModel
import com.solution_challenge.candiformation.ui.theme.VeryLightGrey_type2
import java.util.regex.Pattern

@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Sign Up",
                navBack = true
            )
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
    var emailText = remember { mutableStateOf("") }
    var pwdText = remember { mutableStateOf("") }

    val pattern: Pattern = android.util.Patterns.EMAIL_ADDRESS

    var signUpMsg by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Row(
            modifier = Modifier
                .height(50.dp)
                .width(270.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(2.dp))
                .background(Color.Black),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box() {
                Icon(
                    modifier = Modifier
                        .padding(start = 14.dp, end = 14.dp),
                    imageVector = Icons.Filled.Email,
                    contentDescription = Icons.Filled.Email.toString(),
                    tint = Color.White
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            TextField(
                value = emailText.value,
                onValueChange = { emailText.value = it },
                shape = RoundedCornerShape(0.dp),
                placeholder = { Text("E-mail") },
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

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .height(50.dp)
                .width(270.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(2.dp))
                .background(Color.Black),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box() {
                Icon(
                    modifier = Modifier
                        .padding(start = 14.dp, end = 14.dp),
                    imageVector = Icons.Filled.Lock,
                    contentDescription = Icons.Filled.Lock.toString(),
                    tint = Color.White
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            TextField(
                value = pwdText.value,
                onValueChange = { pwdText.value = it },
                shape = RoundedCornerShape(0.dp),
                placeholder = { Text("Password") },
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
                visualTransformation = PasswordVisualTransformation()
            )
        }

        Text(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 32.dp),
            text = signUpMsg,
            fontSize = 16.sp,
            color = Color.Red
        )

        CustomButton(
            viewModel = viewModel,
            navController = navController,
            title = "인증하기",
            widthDp = 180.dp,
            onClick = {
                if (emailText.value.isBlank() || pwdText.value.isBlank()) {
                    signUpMsg = "이메일 또는 비밀번호를 작성해주세요."
                } else if (!pattern.matcher(emailText.value).matches()) {
                    signUpMsg = "올바른 이메일 형식이 아닙니다."
                } else if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9]).{8,15}.\$", pwdText.value)) {
                    signUpMsg = "비밀번호는 숫자, 문자를 포함하여 \n8~15자로 해주세요."
                } else {
                    viewModel.signUpBody.value.username = emailText.value
                    viewModel.signUpBody.value.password = pwdText.value

                    // 이메일 인증
                    viewModel.authEmail.value = emailText.value
                    viewModel.emailAuth(viewModel.authEmail.value)

                    navController.navigate("setting/login/signup/auth") {
                        popUpTo(route = "setting/login/signup/auth") { inclusive = true }
                    }
                }
            }
        )
    }
}


