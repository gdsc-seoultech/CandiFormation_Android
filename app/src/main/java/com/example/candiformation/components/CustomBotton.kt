package com.example.candiformation.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel

@Composable
fun CustomButton(
    viewModel: SharedViewModel,
    navController: NavHostController,
    title: String,
    onClick: () -> Unit
) {
    Button(onClick = { onClick() }) {
        Text(
            text = title,
            fontSize = 13.sp
        )
    }
}