package com.example.candiformation.ui.screens.news.articles

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.candiformation.ui.SharedViewModel

@Composable
fun LikeAndComments(
    viewModel: SharedViewModel,
    likeIconClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .clickable { likeIconClicked() }
        ) {
            Icon(
                modifier = Modifier.padding(4.dp),
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = "FavoriteBorder Icon"
            )
        }
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            modifier = Modifier.padding(4.dp),
            text = viewModel.articleLikeNum.value.toString()
        )
        Spacer(modifier = Modifier.width(8.dp))
        Box() {
            Icon(
                modifier = Modifier.padding(4.dp),
                imageVector = Icons.Filled.ChatBubbleOutline,
                contentDescription = "ChatBubbleOutline Icon"
            )
        }
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            modifier = Modifier.padding(4.dp),
            text = viewModel.articleCommentNum.value.toString()
        )
    }
}