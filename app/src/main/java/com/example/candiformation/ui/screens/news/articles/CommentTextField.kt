package com.example.candiformation.ui.screens.news.articles

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.candiformation.ui.SharedViewModel

@Composable
fun CommentTextField(
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box(

        ) {
            Text(
                text = "익명"
            )
        }
        TextField(
            value = "",
            onValueChange = {}
        )
        Box(

        ) {
            Icon(imageVector = Icons.Filled.ArrowRight, contentDescription = "")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CommentTextFieldPreview() {
    CommentTextField()
}