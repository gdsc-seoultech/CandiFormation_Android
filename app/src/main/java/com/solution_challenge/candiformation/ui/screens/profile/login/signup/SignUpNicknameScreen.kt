package com.solution_challenge.candiformation.ui.screens.profile.login.signup

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.solution_challenge.candiformation.components.CustomButton
import com.solution_challenge.candiformation.components.CustomTopAppBar
import com.solution_challenge.candiformation.ui.SharedViewModel
import com.solution_challenge.candiformation.ui.theme.VeryLightGrey_type2
import kotlinx.coroutines.launch
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
    val nicknameMsg by viewModel.nicknameMsg.observeAsState()
    val checkNicknameDuplicationRes by viewModel.checkNicknameDuplicationRes.observeAsState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "????????? ?????????????????????.",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

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
                placeholder = { Text("?????????") },
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
            title = "???????????? ??????",
            widthDp = 140.dp,
            onClick = {
                viewModel.signUpBody.value.nickname = nicknameText.value

                if (nicknameText.value.isNullOrEmpty()) {
                    viewModel.setNicknameMsg("????????? ???????????? ??????????????????.")
                } else if (nicknameText.value.length > 13 || nicknameText.value.length < 3) {
                    viewModel.setNicknameMsg("???????????? ??????????????? 4~12??? ????????? ??????????????????.")
                } else {
                    viewModel.checkNicknameDuplication(
                        email = viewModel.signUpBody.value.username,
                        password = viewModel.signUpBody.value.password!!,
                        nickname = viewModel.signUpBody.value.nickname,
                        onSuccess = {
                            navController.navigate("profile/login") {
                                popUpTo("profile/login") { inclusive = true }
                            }
                            Toast.makeText(
                                context,
                                "??????????????? ?????????????????????.\n???????????? ????????????!",
                                Toast.LENGTH_LONG
                            ).show()
                        },
                        onFailure = {
                            viewModel.setNicknameMsg("????????? ???????????? ???????????????.")
                        }
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        nicknameMsg?.let {
            Text(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 32.dp),
                text = it
            )
        }
    }
}
