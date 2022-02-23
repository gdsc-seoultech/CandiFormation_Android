package com.example.candiformation.ui.screens.setting.login

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.components.CustomButton
import com.example.candiformation.components.CustomTextField
import com.example.candiformation.components.CustomTopAppBar
import com.example.candiformation.data.google.GoogleApiContract
import com.example.candiformation.models.SignUpBody
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.utils.Constants
import com.example.candiformation.utils.Constants.CONTENT_INNER_PADDING
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.nio.charset.Charset


@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Login",
                navBack = true
            )
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
    // 데이터 넘기기
    var idText = remember { mutableStateOf("") }
    var passwordText = remember { mutableStateOf("") }

    // 로그인 메세지
    var loginMsg by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = CONTENT_INNER_PADDING),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        CustomTextField(
            placeHolderMsg = "E-mail",
            iconRes = Icons.Filled.Email,
            isVisible = true,
            text = idText
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            placeHolderMsg = "Password",
            iconRes = Icons.Filled.Lock,
            isVisible = false,
            text = passwordText
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = loginMsg,
            fontSize = 16.sp,
            color = Color.Red
        )
        CustomButton(
            viewModel = viewModel,
            navController = navController,
            title = "로그인",
            widthDp = 100.dp,
            onClick = {
                if (idText.value.isBlank() || passwordText.value.isBlank()) {
                    loginMsg = "올바른 아이디 또는 비밀번호가 아닙니다."
                } else {
                    viewModel.login(
                        idText = idText.value,
                        passwordText = passwordText.value,
                        onSuccess = {
                            navController.navigate("setting") {
                                popUpTo("setting") { inclusive = true }
                            }
                        },
                        onFailure = {
                            loginMsg = "아이디 또는 비밀번호를 확인해주세요."
                        }
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomButton(
            viewModel = viewModel,
            navController = navController,
            title = "회원가입",
            widthDp = 100.dp,
            onClick = {
                navController.navigate("setting/login/signup") {
                    popUpTo("setting/login")
                }
            }
        )

        // 구글 로그인
        val signInRequestCode = 1
        val authResultLauncher =
            rememberLauncherForActivityResult(contract = GoogleApiContract()) { task ->
                try {
                    Log.d("suee97", "task >>> ${task?.toString()}")
                    val gsa = task?.getResult(ApiException::class.java)

                    if (gsa != null) {
                        viewModel.signUpBody.value.username = gsa.email.toString()
                        viewModel.signUpBody.value.password = ""
                        viewModel.signUpBody.value.nickname = gsa.displayName.toString()

                        viewModel.googleLogin(
                            idText = viewModel.signUpBody.value.username,
                            passwordText = "",
                            onFailure = { },
                            onSuccess = { }
                        )

                        navController.navigate("setting") {
                            popUpTo("setting")
                        }
                    }
                } catch (e: ApiException) {
                    Log.d("suee97", "authResultLauncher 에러 >>> ${e.localizedMessage}")
                }
            }

        Button(onClick = {
            authResultLauncher.launch(signInRequestCode)
        }) {
            Text("구글 로그인")
        }
    }
}

