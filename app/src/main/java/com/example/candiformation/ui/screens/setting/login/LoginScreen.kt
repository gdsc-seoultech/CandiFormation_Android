package com.example.candiformation.ui.screens.setting.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.candiformation.components.CustomTextField
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.theme.VeryLightGrey
import com.example.candiformation.utils.Constants

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
                    .padding(start = 40.dp, end = 40.dp)
            ) {
                CustomTextField("ddd")

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
    var idText by remember { mutableStateOf("") }
    var pwdText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            value = idText,
            onValueChange = {
                idText = it
            },
            shape = CircleShape,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = VeryLightGrey,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.LightGray
            ),
            singleLine = true,
            maxLines = 1,
            label = {
                Text("이메일")
            }
        )
        Button(onClick = { /*TODO*/ }) {
            Text("로그인")
        }
        Button(onClick = {
            navController.navigate("setting/login/signup")
        }) {
            Text("회원가입")
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
                navController.navigate("setting") {
                    popUpTo("setting") { inclusive = true }
                }
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Arrow Back")
            }
        }
    )
}