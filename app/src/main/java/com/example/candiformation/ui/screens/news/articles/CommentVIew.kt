package com.example.candiformation.ui.screens.news.articles

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.candiformation.models.CommentResponse

@Composable
fun CommentView(
    commentList: List<CommentResponse>
) {
    commentList.forEach { item ->
        Text(text = "작성자 : ${item.nickname}")
        Text(text = item.content)
    }
}