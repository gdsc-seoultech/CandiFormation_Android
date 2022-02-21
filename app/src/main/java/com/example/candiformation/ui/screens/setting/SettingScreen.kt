package com.example.candiformation.ui.screens.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.utils.Constants.CONTENT_INNER_PADDING
import com.example.candiformation.utils.Constants.TOP_APP_BAR_FONT

@Composable
fun SettingScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
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
                        logOutClicked = { viewModel.logOut() }
                    )
                }
                SettingList(
                    navController = navController,
                    viewModel = viewModel
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
            onClicked = { navController.navigate("setting/comments") {
                popUpTo("setting")
            } }
        )
        GreyDivider()
        SettingListUnit(
            navController = navController,
            viewModel = viewModel,
            title = "Likes",
            onClicked = {
                navController.navigate("setting/like") {
                    popUpTo("setting")
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
                navController.navigate("setting/appInfo")
            }
        )
        GreyDivider()
        SettingListUnit(
            navController = navController,
            viewModel = viewModel,
            title = "Share",
            onClicked = {/* 각자 맞는 화면으로 이동 */ }
        )
        GreyDivider()
        SettingListUnit(
            navController = navController,
            viewModel = viewModel,
            title = "Setting",
            onClicked = {/* 각자 맞는 화면으로 이동 */ }
        )
        GreyDivider()
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