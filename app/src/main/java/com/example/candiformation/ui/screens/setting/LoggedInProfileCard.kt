package com.example.candiformation.ui.screens.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.theme.VeryLightGrey_type1

@Composable
fun LoggedInProfileCard(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        backgroundColor = VeryLightGrey_type1
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.SupervisedUserCircle,
                contentDescription = "Profile image",
                tint = Color.Black,
                modifier = Modifier.size(90.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Text(
                    text = viewModel.currentUser.value.nickname,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp
                )
                Text(
                    text = viewModel.currentUser.value.username,
                    fontWeight = FontWeight.ExtraLight,
                    fontSize = 12.sp
                )
                Text(
                    modifier = Modifier
                        .clickable {
                            navController.navigate("setting/login") {
                                popUpTo("setting/login") { inclusive = true }
                            }
                        },
                    text = "로그인",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                Text(
                    modifier = Modifier
                        .clickable {
                            viewModel.logOut()
                        },
                    text = "로그아웃",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }
    }
}