package com.solution_challenge.candiformation.ui.screens.profile.login.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.solution_challenge.candiformation.components.CustomButton
import com.solution_challenge.candiformation.components.CustomCheckLine
import com.solution_challenge.candiformation.components.CustomTopAppBar
import com.solution_challenge.candiformation.ui.SharedViewModel
import com.solution_challenge.candiformation.ui.theme.VeryLightGrey_type2
import java.util.regex.Pattern

@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    viewModel.setSignUpInitEnabled()
    viewModel.setSignUpMsg("")

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
    val scope = rememberCoroutineScope()

    var emailText = remember { mutableStateOf("") }
    var pwdText = remember { mutableStateOf("") }

    val pattern: Pattern = android.util.Patterns.EMAIL_ADDRESS

    val isNextButtonEnabled by viewModel.signUpNextButtonEnabled.observeAsState()
    val isTextFieldEnabled by viewModel.signUpTextFieldEnabled.observeAsState()

    val serviceUsageCheck by viewModel.serviceUsageCheck.observeAsState()
    val privateInfoCheck by viewModel.privateInfoCheck.observeAsState()

    val signUpMsg by viewModel.signUpMsg.observeAsState()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
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

            isTextFieldEnabled?.let {
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
                    ),
                    enabled = it
                )
            }
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
            isTextFieldEnabled?.let {
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
                    visualTransformation = PasswordVisualTransformation(),
                    enabled = it
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        isNextButtonEnabled?.let {
            CustomButton(
                viewModel = viewModel,
                navController = navController,
                title = "중복 확인 및 진행",
                widthDp = 180.dp,
                onClick = {
                    if (emailText.value.isBlank() || pwdText.value.isBlank()) {
                        viewModel.setSignUpMsg("올바른 이메일 또는 비밀번호가 아닙니다.")
                    } else if (!pattern.matcher(emailText.value).matches()) {
                        viewModel.setSignUpMsg("올바른 이메일 형식이 아닙니다.")
                    } else if (!Pattern.matches(
                            "^(?=.*[A-Za-z])(?=.*[0-9]).{7,15}.\$",
                            pwdText.value
                        )
                    ) {
                        viewModel.setSignUpMsg("비밀번호는 숫자, 영어를 포함해 8~15자로 설정해주세요!")
                    } else if(serviceUsageCheck == false || privateInfoCheck == false) {
                        viewModel.setSignUpMsg("아래의 약관을 동의해야 회원가입을 할 수 있습니다.")
                    }
                    else {
                        viewModel.setSignUpMsg("")
                        viewModel.checkEmailDuplication(emailText.value)
                    }
                },
                enabled = !it
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        isNextButtonEnabled?.let {
            CustomButton(
                viewModel = viewModel,
                navController = navController,
                title = "다음",
                widthDp = 180.dp,
                onClick = {
                    viewModel.signUpBody.value.username = emailText.value
                    viewModel.signUpBody.value.password = pwdText.value
                    viewModel.authEmail.value = emailText.value
                    viewModel.emailAuth(viewModel.authEmail.value)
                    navController.navigate("profile/login/signup/auth") {
                        popUpTo(route = "profile/login/signup/auth") { inclusive = true }
                    }
                },
                enabled = it
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        signUpMsg?.let {
            Text(
                text = it
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Divider(modifier = Modifier.fillMaxWidth(.8f))
        Spacer(modifier = Modifier.height(12.dp))

        serviceUsageCheck?.let {
            CustomCheckLine(
                modifier = Modifier,
                msg = "서비스 이용 약관 동의    ",
                checkState = it,
                onCheckClicked = {
                    viewModel.setServiceUsageCheck()
                },
                onSpecClicked = {
                    navController.navigate(route = "profile/login/signup/info") {
                        popUpTo("profile/login/signup")
                    }
                },
                enabled = isTextFieldEnabled!!
            )
        }
        privateInfoCheck?.let {
            CustomCheckLine(
                modifier = Modifier,
                msg = "개인정보처리방침 동의  ",
                checkState = it,
                onCheckClicked = {
                    viewModel.setPrivateInfoCheck()
                },
                onSpecClicked = {
                    navController.navigate(route = "profile/login/signup/info2") {
                        popUpTo("profile/login/signup")
                    }
                },
                enabled = isTextFieldEnabled!!
            )
        }
    }
}



