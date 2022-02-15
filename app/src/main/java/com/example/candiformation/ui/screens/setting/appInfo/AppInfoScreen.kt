package com.example.candiformation.ui.screens.setting.appInfo


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.HowToVote
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.theme.VeryLightGrey_type1
import com.example.candiformation.utils.Constants

@Composable
fun AppInfoScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            AppInfoScreenTopAppBar(navController = navController)
        },
        content = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                AppInfoTopLogo()
                Spacer(modifier = Modifier.height(16.dp))
                AppInfoScreenContent()
            }
        }
    )
}

@Composable
fun AppInfoScreenTopAppBar(
    navController: NavHostController
) {
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Text(
                text = "앱 정보",
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

@Composable
fun AppInfoTopLogo() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Constants.CONTENT_INNER_PADDING)
    ) {
        Row(modifier = Modifier.fillMaxWidth()
            .padding(top = 10.dp))
        {
            Icon(
                imageVector = Icons.Default.HowToVote,
                contentDescription = "HowToVote Icon",
                tint = Color.Red,
                modifier = Modifier.size(70.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Candidate Information",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Candiformation",
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Candiformation은 선거 관련 정보 제공 및 후보자에 대한 객관적인 시각을 가질수 있도록 돕는 어플리케이션입니다.",
                    fontSize = 9.sp
                )
            }
        }
    }
}

@Composable
fun AppInfoScreenContent() {
    AppInfoScreenCard(title = "App version", description = "1.0")
    AppInfoScreenCard(title = "Developers", description = "Wee SR\nShin YB\nOh SU\nYang YS")
    AppInfoScreenCard(title = "Contacts / Bug Report", description = "atn1su@gmail.com")
    AppInfoScreenCard(title = "From.", description = "Google Solution Challenge")
}

@Composable
fun AppInfoScreenCard(
    title: String,
    description: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 9.dp)
            .border(width = 1.dp, color = Color.LightGray),
        backgroundColor = Color.White,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp
            )
            Text(
                text = description,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
}