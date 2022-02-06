package com.example.candiformation.ui.screens.setting.appInfo


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HowToVote
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.screens.setting.SettingListUnit
import com.example.candiformation.ui.theme.VeryLightGrey
import com.example.candiformation.utils.Constants

@Composable
fun AppInfoScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                title = {
                    Text(
                        text = "앱 정보",
                        fontSize = Constants.TOP_APP_BAR_FONT,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                AppInfoTopAppBar()
                Spacer(modifier = Modifier.height(16.dp))
                AppInfoSpec()
            }
        }
    )
}

@Composable
fun AppInfoTopAppBar() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Constants.CONTENT_INNER_PADDING)
    ) {
        Row(modifier = Modifier.fillMaxWidth())
        {
            Icon(
                imageVector = Icons.Default.HowToVote,
                contentDescription = "Candiformation_Icon",
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
                    text = "Candiformation은 선거 정보를 위한 대선 정보 관련 에플리케이션입니다. \n후보에 대한 부정확 정보와 선거 관련 정보의 불평등을 해결하고자 출시한 어플입니다.",
                    fontSize = 9.sp
                )
            }
        }
    }
}

@Composable
fun AppInfoSpec() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 9.dp)
            .clip(RoundedCornerShape(8.dp)),
        backgroundColor = VeryLightGrey
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "App Version",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp
            )
            Text(
                text = "1.0",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 9.dp)
            .clip(RoundedCornerShape(8.dp)),
        backgroundColor = VeryLightGrey
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Developers",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp
            )
            Text(
                text = "위성률\n신유빈\n오승언\n양용수",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 9.dp)
            .clip(RoundedCornerShape(8.dp)),
        backgroundColor = VeryLightGrey
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Contacts / Bug Report",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp
            )
            Text(
                text = "atn1su@gmail.com",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 9.dp)
            .clip(RoundedCornerShape(8.dp)),
        backgroundColor = VeryLightGrey
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = "From.",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp
            )
            Text(
                text = "Google Solution Challenge",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
}