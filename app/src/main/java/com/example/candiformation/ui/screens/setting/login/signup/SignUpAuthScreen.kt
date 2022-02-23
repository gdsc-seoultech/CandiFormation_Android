package com.example.candiformation.ui.screens.setting.login.signup

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.candiformation.components.CustomTopAppBar
import com.example.candiformation.ui.SharedViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SignUpAuthScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(navController = navController, title = "회원가입", navBack = true)
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        var tempVerifyCode by remember { mutableStateOf("") }

        Text(
            text = "${viewModel.authEmail.value} 로 인증코드를 보냈습니다."
        )
        TextField(
            value = tempVerifyCode,
            onValueChange = {
                tempVerifyCode = it
            }
        )

        Button(onClick = {
            scope.launch {
                viewModel.verifyCode(tempVerifyCode)
                delay(1000)
                if (isVerified!!.verify){
                    Log.d("suee97", "회원가입 성공~!!!!!!!!!")

                    navController.navigate("setting/login/signup/setnickname") {
                        popUpTo(route = "setting/login/signup/setnickname") { inclusive = true }
                    }
                } else {
                    Log.d("suee97", "회원가입 실패~!!!!!!!!!")
                }
            }
        }) {
            Text("인증하기")
        }
    }
}
