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
import com.example.candiformation.utils.Constants.TOP_APP_BAR_FONT

@Composable
fun SettingScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            SettingTopAppBar()
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                LoggedInProfileCard(
                    viewModel = viewModel,
                    navController = navController
                )
                Spacer(modifier = Modifier.height(20.dp))
                Divider()
                SettingList(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    )
}

@Composable
fun SettingTopAppBar() {
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Text(
                text = "설정",
                fontSize = TOP_APP_BAR_FONT,
                fontWeight = FontWeight.Bold
            )
        }
    )
}


@Composable
fun SettingList(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        SettingListUnit(
            navController = navController,
            viewModel = viewModel,
            title = "내가 쓴 댓글",
            onClicked = {/* 각자 맞는 화면으로 이동 */ }
        )
        SettingListUnit(
            navController = navController,
            viewModel = viewModel,
            title = "좋아요 누른 기사",
            onClicked = {/* 각자 맞는 화면으로 이동 */ }
        )
        Divider()
        SettingListUnit(
            navController = navController,
            viewModel = viewModel,
            title = "앱 정보",
            onClicked = {
                navController.navigate("setting/appInfo")
            }
        )
        SettingListUnit(
            navController = navController,
            viewModel = viewModel,
            title = "앱 공유",
            onClicked = {/* 각자 맞는 화면으로 이동 */ }
        )
        SettingListUnit(
            navController = navController,
            viewModel = viewModel,
            title = "설정",
            onClicked = {/* 각자 맞는 화면으로 이동 */ }
        )
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
                .padding(horizontal = 16.dp, vertical = 16.dp),
            text = title,
            fontSize = 18.sp
        )
    }
}