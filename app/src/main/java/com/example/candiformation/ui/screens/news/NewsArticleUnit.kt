package com.example.candiformation.ui.screens.news

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.candiformation.models.ArticleResponse
import com.example.candiformation.models.LikeBody
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.theme.VeryLightGrey_type1

@Composable
fun NewsArticleUnit(
    navController: NavHostController,
    viewModel: SharedViewModel,
    articleResponse: ArticleResponse,
    likeIconClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(VeryLightGrey_type1)
            .clickable {
                viewModel.articleId.value = articleResponse.id
                viewModel.articleTitle.value = articleResponse.title
                viewModel.articleContent.value = articleResponse.thumnail
                viewModel.articleAgency.value = articleResponse.news_agency
                viewModel.articleLink.value = articleResponse.link
                viewModel.articleImage.value = articleResponse.images
                viewModel.articleLikeNum.value = articleResponse.like_num
                viewModel.articleCommentNum.value = articleResponse.comment_num

                navController.navigate("news/articles/selectedArticle") {
                    popUpTo("news/articles") { inclusive = true }
                }
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
                text = articleResponse.thumnail
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, bottom = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .border(2.dp, Color.Magenta)
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
                    text = articleResponse.like_num.toString()
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
                    text = articleResponse.comment_num.toString()
                )
                Spacer(modifier = Modifier.width(40.dp))
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = articleResponse.news_agency,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

