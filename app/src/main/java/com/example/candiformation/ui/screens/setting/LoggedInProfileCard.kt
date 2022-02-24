package com.example.candiformation.ui.screens.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel

@Composable
fun LoggedInProfileCard(
    navController: NavHostController,
    viewModel: SharedViewModel,
    logOutClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = Color.Black,
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = viewModel.currentUser.value.nickname,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 40.sp,
                    color = Color.White
                )
                Text(
                    text = viewModel.currentUser.value.username,
                    fontWeight = FontWeight.ExtraLight,
                    fontSize = 25.sp,
                    color = Color.White
                )
                Text(
                    text = "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.White
                )
                Text(
                    modifier = Modifier
                        .clickable {
                            logOutClicked()
                        },
                    text = "로그아웃",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }
    }
}

