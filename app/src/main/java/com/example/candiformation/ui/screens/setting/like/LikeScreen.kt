package com.example.candiformation.ui.screens.setting.like

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.candiformation.components.CustomTopAppBar
import com.example.candiformation.models.LikeBody
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.screens.news.NewsArticleUnit
import com.example.candiformation.ui.screens.news.articles.CommentView


@Composable
fun LikeScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Likes",
                navBack = true
            )
        },
        content = {
            LikeScreenContent(
                navController = navController,
                viewModel = viewModel
            )
        }
    )
}

@Composable
fun LikeScreenContent(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val _getAllArticles by viewModel.articleDataList.observeAsState()
    val getAllArticles by remember { mutableStateOf(_getAllArticles?.asReversed()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 48.dp)
    ) {
        if (getAllArticles.isNullOrEmpty()) {

        } else {
            LazyColumn() {
                items(getAllArticles!!.size) { index ->
                    if (index == viewModel.whatArticleLiked.value.articles[index]) {
                        NewsArticleUnit(
                            navController = navController,
                            viewModel = viewModel,
                            articleResponse = getAllArticles!![index],
                            likeIconClicked = {
                                if (viewModel.currentUser.value.username.isNullOrEmpty()) {

                                } else {
                                    viewModel.articleId.value =
                                        getAllArticles!![index].id
                                    viewModel.like(
                                        likeBody = LikeBody(
                                            article_id = viewModel.articleId.value,
                                            username = viewModel.currentUser.value.username
                                        )
                                    )
                                }
                            },
                            isLiked = if (viewModel.whatArticleLiked.value.articles.contains(
                                    getAllArticles!!.size - 1 - index + 1
                                )
                            ) {
                                true
                            } else {
                                false
                            },
                            articleDataList = getAllArticles!![getAllArticles!!.size - 1 - index]
                        )
                        Divider(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                        )
                    }
                }
            }
        }
    }
}