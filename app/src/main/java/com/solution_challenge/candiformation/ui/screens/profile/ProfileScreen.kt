package com.solution_challenge.candiformation.ui.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.solution_challenge.candiformation.components.CustomDialog_type_1
import com.solution_challenge.candiformation.ui.SharedViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val scope = rememberCoroutineScope()
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }

    scope.launch {
        viewModel.getAllComments(username = viewModel.currentUser.value.username)
    }

    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (viewModel.currentUser.value.username.isEmpty()) {
                    LoggedOutProfileCard(
                        viewModel = viewModel,
                        navController = navController
                    )
                } else {
                    LoggedInProfileCard(
                        viewModel = viewModel,
                        navController = navController,
                        logOutClicked = {
                            setShowDialog(true)
                        }
                    )
                }
                SettingList(
                    navController = navController,
                    viewModel = viewModel
                )
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CustomDialog_type_1(
                    showDialog = showDialog,
                    setShowDialog = setShowDialog,
                    title = "로그아웃 하시겠습니까?",
                    isConfirmed = {
                        viewModel.logOut()
                    },
                    isDismissed = {/* Cancel */}
                )
            }
        }
    )
}

@Composable
fun SettingList(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()

    fun launchSnackBar(msg: String) {
        snackScope.launch {
            snackState.showSnackbar(
                message = msg,
                duration = SnackbarDuration.Short
            )
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(24.dp))
        SettingTitleUnit("My Activity")
        Divider(
            modifier = Modifier
                .height(5.dp)
                .padding(horizontal = 8.dp),
            color = Color.Black
        )
        SettingListUnit(
            navController = navController,
            viewModel = viewModel,
            title = "Comments",
            onClicked = {
                if (viewModel.currentUser.value.username.isNullOrEmpty()) {
                    launchSnackBar("로그인이 필요한 서비스입니다.")
                } else {
                    navController.navigate("profile/comments") {
                        popUpTo("profile")
                    }
                }
            }
        )
        GreyDivider()
        SettingListUnit(
            navController = navController,
            viewModel = viewModel,
            title = "Likes",
            onClicked = {
                if (viewModel.currentUser.value.username.isNullOrEmpty()) {
                    launchSnackBar("로그인이 필요한 서비스입니다.")
                } else {
                    navController.navigate("profile/like") {
                        popUpTo("profile")
                    }
                }
            }
        )
        GreyDivider()
        Spacer(modifier = Modifier.height(24.dp))
        SettingTitleUnit("Application")
        Divider(
            modifier = Modifier
                .height(5.dp)
                .padding(horizontal = 8.dp),
            color = Color.Black
        )
        SettingListUnit(
            navController = navController,
            viewModel = viewModel,
            title = "Information",
            onClicked = {
                navController.navigate("profile/appInfo")
            }
        )
        GreyDivider()
        SettingListUnit(
            navController = navController,
            viewModel = viewModel,
            title = "Share",
            onClicked = {
                launchSnackBar("준비중입니다.")
            }
        )
        GreyDivider()
        SettingListUnit(
            navController = navController,
            viewModel = viewModel,
            title = "Setting",
            onClicked = {
                launchSnackBar("준비중입니다.")
            }
        )
        GreyDivider()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.5f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SnackbarHost(
                hostState = snackState
            )
        }

    }
}

@Composable
fun SettingListUnit(
    navController: NavHostController,
    viewModel: SharedViewModel,
    title: String,
    onClicked: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClicked() }) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            text = title,
            fontSize = 18.sp
        )
    }
}

@Composable
fun SettingTitleUnit(
    title: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun GreyDivider() {
    Divider(modifier = Modifier.padding(horizontal = 8.dp))
}