package com.example.candiformation.ui.screens.news.articles

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.candiformation.ui.SharedViewModel

@Composable
fun CommentTextField(
    viewModel: SharedViewModel
) {
    var comment by remember { mutableStateOf("") }
    var isSecret by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .clickable { }
                .border(2.dp, Color.Magenta)
        ) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = "익명",
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
        TextField(
            value = comment,
            onValueChange = {
                comment = it
            }
        )
        Box(
            modifier = Modifier
                .clickable {
                    viewModel.currentCommentBody.value.articleId = viewModel.articleId.value
                    viewModel.currentCommentBody.value.content = comment
                    viewModel.currentCommentBody.value.nickname =
                        viewModel.currentUser.value.nickname
                    viewModel.writeComment(viewModel.currentCommentBody.value)
                    comment = ""
                }
                .border(2.dp, Color.Magenta)
        ) {
            Icon(
                modifier = Modifier.padding(12.dp),
                imageVector = Icons.Filled.ArrowRight,
                contentDescription = ""
            )
        }
    }
}