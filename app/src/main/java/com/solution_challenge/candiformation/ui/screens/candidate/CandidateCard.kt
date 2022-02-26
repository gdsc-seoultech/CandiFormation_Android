package com.solution_challenge.candiformation.ui.screens.candidate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.solution_challenge.candiformation.ui.SharedViewModel

@Composable
fun CandidateCard(
    modifier: Modifier,
    name: String,
    party: String,
    imgSrc: Int,
    onClick: () -> Unit
) {
    Card(modifier = modifier
        .padding(top = 4.dp, bottom = 4.dp)
        .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
                    .background(Color.White)
            ) {
                Column() {
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