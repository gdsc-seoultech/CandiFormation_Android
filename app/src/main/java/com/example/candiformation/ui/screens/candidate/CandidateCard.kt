package com.example.candiformation.ui.screens.candidate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel

@Composable
fun CandidateCard(
    navController: NavHostController,
    viewModel: SharedViewModel,
    name: String,
    party: String,
    colorId: Color,
    imgSrc: Int,
    logoSrc: Int
) {
    Card(modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)) {
        Column(
            modifier = Modifier
                .width(120.dp)
                .height(180.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier.padding()
                ) {
                    Image(
                        painter = painterResource(id = imgSrc),
                        contentDescription = name
                    )
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = name,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = party,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Color.LightGray
                    )
                }
            }
        }
    }

}