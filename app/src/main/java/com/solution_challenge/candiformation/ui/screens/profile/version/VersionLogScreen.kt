package com.solution_challenge.candiformation.ui.screens.profile.version

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.solution_challenge.candiformation.components.CustomTopAppBar
import com.solution_challenge.candiformation.ui.SharedViewModel

@Composable
fun VersionLogScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Version Log",
                navBack = true
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp)
            ) {
                VersionLogScreenContent()
            }
        }
    )
}

@Composable
fun VersionLogScreenContent() {
    VersionLogoCard(
        title = "1.1.1",
        desc = "약관동의화면 하단바를 숨깁니다.\n" +
                "기사를 불러오지 못할 시 로딩화면을 띄웁니다.\n" +
                "회원가입 화면 수정"
    )
}

@Composable
fun VersionLogoCard(
    title: String,
    desc: String
) {
    Card(
        modifier = Modifier.padding(4.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(4.dp)
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = desc,
                fontSize = 16.sp
            )
        }
    }
}