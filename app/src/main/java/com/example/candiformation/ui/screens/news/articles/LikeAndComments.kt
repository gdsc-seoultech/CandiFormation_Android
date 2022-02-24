package com.example.candiformation.ui.screens.news.articles

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.candiformation.models.ArticleResponse
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.theme.SemiRed

@Composable
fun LikeAndComments(
    viewModel: SharedViewModel,
    likeIconClicked: () -> Unit,
    isLiked: Boolean,
    likeNum: Int,
    commentNum: Int
) {
    Log.d("suee97", "articleID >>> ${viewModel.articleId.value}")

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
                imageVector = if(isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "Favorite Icon",
                tint = SemiRed
            )
        }
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            modifier = Modifier.padding(4.dp),
            text = likeNum.toString()
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
            text = commentNum.toString()
        )
    }
}