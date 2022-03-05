package com.solution_challenge.candiformation.ui.screens.profile.login

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.solution_challenge.candiformation.R
import com.solution_challenge.candiformation.components.CustomButton
import com.solution_challenge.candiformation.components.CustomTextField
import com.solution_challenge.candiformation.components.CustomTopAppBar
import com.solution_challenge.candiformation.data.google.GoogleApiContract
import com.solution_challenge.candiformation.ui.SharedViewModel
import com.solution_challenge.candiformation.ui.theme.googleColor
import com.solution_challenge.candiformation.utils.Constants.CONTENT_INNER_PADDING
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
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

@ExperimentalMaterialApi
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
        Spacer(modifier = Modifier.height(12.dp))
        CustomButton(
            viewModel = viewModel,
            navController = navController,
            title = "로그인",
            widthDp = 180.dp,
            onClick = {
                if (idText.value.isBlank() || passwordText.value.isBlank()) {
                    loginMsg = "올바른 아이디 또는 비밀번호가 아닙니다."
                } else {
                    viewModel.login(
                        idText = idText.value,
                        passwordText = passwordText.value,
                        onSuccess = {
                            navController.navigate("profile") {
                                popUpTo("profile") { inclusive = true }
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
            widthDp = 180.dp,
            onClick = {
                navController.navigate("profile/login/signup") {
                    popUpTo("profile/login")
                }
            }
        )
        Spacer(modifier = Modifier.height(12.dp))


        // 구글 로그인
        val scope = rememberCoroutineScope()
        val signInRequestCode = 1
        val authResultLauncher =
            rememberLauncherForActivityResult(contract = GoogleApiContract()) { task ->
                try {
                    Log.d("suee97", "task >>> ${task?.toString()}")
                    val gsa = task?.getResult(ApiException::class.java)

                    if (gsa != null) {
                        scope.launch {
                            viewModel.signUpBody.value.username = gsa.email.toString()
                            viewModel.signUpBody.value.password = ""
                            viewModel.signUpBody.value.nickname = gsa.displayName.toString()

                            viewModel.googleLogin(
                                idText = viewModel.signUpBody.value.username,
                                passwordText = "",
                                onFailure = {

                                },
                                onSuccess = {
                                    navController.navigate("profile") {
                                        popUpTo("profile")
                                    }
                                }
                            )
                        }
                    }
                } catch (e: ApiException) {
                    Log.d("suee97", "authResultLauncher 에러 >>> ${e.localizedMessage}")
                }
            }

        Spacer(modifier = Modifier.height(8.dp))
        
        SignInView(
            errorText = "",
            onClick = {
                authResultLauncher.launch(signInRequestCode)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = loginMsg,
            fontSize = 16.sp,
            color = Color.Red
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun SignInView(errorText: String?, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GoogleSignInButtonUi(
            text = "Sign In With Google",
            loadingText = "Signing In....",
            onClicked = {
                onClick()
            }
            // authResultLauncer launch : SignIn Intent 실행 및 결과 처리 (AuthResultContract)
        )
//        errorText?.let { // errorText가 전달된 경우 이를 표시
//            Spacer(modifier = Modifier.height(30.dp))
//            Text(text = it)
//        }
    }

}

@ExperimentalMaterialApi
@Composable
fun GoogleSignInButtonUi(
    text: String = "",
    loadingText: String = "",
    onClicked: () -> Unit
) {
    var clicked by remember { mutableStateOf(false) }
    val robotoFont = FontFamily(Font(R.font.roboto_medium))

    Surface(
        modifier = Modifier.height(40.dp),
        onClick = {
            onClicked()
            clicked = false
        },
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 10.5.dp,
                    bottom = 10.5.dp
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(18.dp),
                painter = painterResource(id = R.drawable.ic_google_icon),
                contentDescription = "Google Sign Button",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = if (clicked) loadingText else text,
                fontFamily = robotoFont,
                fontSize = 14.sp,
                color = googleColor
            ) // 클릭 시 loadingText 보여줌

            if (clicked) { // 클릭 시 실행
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(16.dp)
                        .width(16.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colors.primary
                )
                // authResultLauncer launch : SignIn Intent 실행 및 결과 처리 (AuthResultContract)
            }
        }
    }
}