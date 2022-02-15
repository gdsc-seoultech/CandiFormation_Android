package com.example.candiformation.ui.screens.news.articles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
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
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier.width(40.dp)
        ) {
            Text(
                text = "익명"
            )
        }
        TextField(value = "", onValueChange = {})
        Box(
            modifier = Modifier.width(40.dp)
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