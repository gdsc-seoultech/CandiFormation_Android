package com.example.candiformation.ui.screens.news

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.candiformation.models.ArticleResponse
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.theme.VeryLightGrey

@Composable
fun NewsArticleUnit(
    navController: NavHostController,
    viewModel: SharedViewModel,
    articleResponse: ArticleResponse
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(VeryLightGrey)
            .clickable {
                viewModel.articleId.value = articleResponse.id
                navController.navigate("news/articles/${articleResponse.id}")
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp),
                text = articleResponse.title,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 12.dp),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                text = articleResponse.content
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = "FavoriteBorder Icon"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = articleResponse.like_num.toString()
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.Filled.ChatBubbleOutline,
                    contentDescription = "ChatBubbleOutline Icon"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = articleResponse.comment_num.toString()
                )
                Spacer(modifier = Modifier.width(40.dp))
                Text(
                    text = articleResponse.news_agency,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

