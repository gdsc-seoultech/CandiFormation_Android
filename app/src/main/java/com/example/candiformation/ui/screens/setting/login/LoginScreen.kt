package com.example.candiformation.ui.screens.setting.login

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavHostController
import com.example.candiformation.components.CustomButton
import com.example.candiformation.components.CustomTextField
import com.example.candiformation.data.google.GoogleApiContract
import com.example.candiformation.models.SignUpBody
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.utils.Constants
import com.example.candiformation.utils.Constants.CONTENT_INNER_PADDING
import com.google.android.gms.common.api.ApiException
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
            LoginScreenTopAppBar(navController = navController)
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = CONTENT_INNER_PADDING),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoginScreenContent(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    )
}

@Composable
fun LoginScreenContent(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    var idText = remember { mutableStateOf("") }
    var passwordText = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
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
        CustomButton(
            viewModel = viewModel,
            navController = navController,
            title = "로그인",
            widthDp = 100.dp,
            onClick = {
                if (idText.value.isBlank() || passwordText.value.isBlank()) {

                } else {
                    navController.navigate("setting") {
                        popUpTo("setting") { inclusive = true }
                    }
                    viewModel.login(
                        idText = idText.value,
                        passwordText = passwordText.value,
                        onSuccess = {},
                        onFailure = {}
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

                    if(gsa != null) {
                        viewModel.signUpBody.value.username = gsa.email.toString()
                        viewModel.signUpBody.value.password = ""
                        viewModel.signUpBody.value.nickname = gsa.displayName.toString()
                        viewModel.signUp()

                        viewModel.login(
                            idText = viewModel.signUpBody.value.username,
                            passwordText = "",
                            onFailure = {},
                            onSuccess = {
                                navController.navigate("setting") {
                                    popUpTo("setting")
                                }
                            }
                        )
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

@Composable
fun LoginScreenTopAppBar(
    navController: NavHostController
) {
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Text(
                text = "로그인",
                fontSize = Constants.TOP_APP_BAR_FONT,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Arrow Back")
            }
        }
    )
}

