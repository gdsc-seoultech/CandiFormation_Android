package com.example.candiformation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.theme.VeryLightGrey_type1

@Composable
fun CustomButton(
    viewModel: SharedViewModel,
    navController: NavHostController,
    title: String,
    widthDp: Dp,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.width(widthDp).height(40.dp),
        shape = RoundedCornerShape(2.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ),
        onClick = { onClick() },
        border = BorderStroke(1.dp, Color.Black),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}