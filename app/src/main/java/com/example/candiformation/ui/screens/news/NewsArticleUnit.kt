package com.example.candiformation.ui.screens.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.models.ArticleResponse
import com.example.candiformation.ui.SharedViewModel

@Composable
fun NewsArticleUnit(
    navController: NavHostController,
    viewModel: SharedViewModel,
    articleResponse: ArticleResponse,
    likeIconClicked: () -> Unit,
    isLiked: Boolean
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .clickable {
                viewModel.articleId.value = articleResponse.id
                viewModel.articleTitle.value = articleResponse.title
                viewModel.articleContent.value = articleResponse.thumnail
                viewModel.articleAgency.value = articleResponse.news_agency
                viewModel.articleLink.value = articleResponse.link
                viewModel.articleImage.value = articleResponse.images
                viewModel.articleLikeNum.value = articleResponse.like_num
                viewModel.articleCommentNum.value = articleResponse.comment_num
                viewModel.articleDateTime.value = articleResponse.date_time

                navController.navigate("news/articles/selectedArticle") {
                    popUpTo("news/articles") { inclusive = true }
                }
            },
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp),
                text = articleResponse.title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
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
                    .padding(start = 12.dp, end = 12.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row() {
                    Box(
                        modifier = Modifier
                            .clickable { likeIconClicked() }
                    ) {
                        if (isLiked) {
                            Icon(
                                modifier = Modifier.padding(4.dp),
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = "Favorite Icon",
                                tint = Color.Red
                            )
                        } else {
                            Icon(
                                modifier = Modifier.padding(4.dp),
                                imageVector = Icons.Filled.FavoriteBorder,
                                contentDescription = "Favorite Icon",
                                tint = Color.Red
                            )
                        }
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
                }
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = articleResponse.news_agency,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

