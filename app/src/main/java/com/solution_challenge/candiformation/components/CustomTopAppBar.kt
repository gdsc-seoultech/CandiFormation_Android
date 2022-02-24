package com.solution_challenge.candiformation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.solution_challenge.candiformation.utils.Constants

@Composable
fun CustomTopAppBar(
    navController: NavHostController,
    title: String,
    navBack: Boolean
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        if (navBack) {
            TopAppBar(
                backgroundColor = Color.White,
                title = {
                    Text(
                        text = title,
                        fontSize = Constants.TOP_APP_BAR_FONT,
                        fontWeight = FontWeight.ExtraBold
                    )
                },
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.alpha(1f),
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Arrow Back"
                        )
                    }
                },
                elevation = 0.dp
            )
        } else {
            TopAppBar(
                backgroundColor = Color.White,
                title = {
                    Text(
                        text = title,
                        fontSize = Constants.TOP_APP_BAR_FONT,
                        fontWeight = FontWeight.ExtraBold
                    )
                },
                elevation = 0.dp
            )
        }
        Divider(
            color = Color.Black,
            modifier = Modifier
                .height(5.dp)
                .padding(horizontal = 8.dp)
        )
    }
}