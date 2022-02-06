package com.example.candiformation.ui.screens.candidate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel

@Composable
fun CandidateCard(
    navController: NavHostController,
    viewModel: SharedViewModel,
    name: String,
    colorId: Color,
    imgSrc: Int,
    logoSrc: Int
) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .border(2.dp, Color.Magenta)
            .background(colorId),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(vertical = 4.dp),
            painter = painterResource(id = logoSrc),
            contentDescription = "${name} 소속당 로고"
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .border(2.dp, Color.Black)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = imgSrc),
                    contentDescription = name
                )
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = name,
                    fontSize = 24.sp
                )
            }
        }
    }
}