package com.solution_challenge.candiformation.ui.screens.profile.like

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.solution_challenge.candiformation.components.CustomTopAppBar
import com.solution_challenge.candiformation.models.ArticleResponse
import com.solution_challenge.candiformation.models.LikeBody
import com.solution_challenge.candiformation.ui.SharedViewModel
import com.solution_challenge.candiformation.ui.screens.news.NewsArticleUnit


@Composable
fun LikeScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val articleDataList by viewModel.articleDataList.observeAsState()
    val scaffoldState = rememberScaffoldState()

    if (viewModel.currentUser.value.nickname != "") {
        viewModel.whatArticleLiked(viewModel.currentUser.value.username)
        Log.d(
            "suee97", "what article liked id >>> " +
                    "${viewModel.whatArticleLiked.value.articles}"
        )
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Likes",
                navBack = true
            )
        },
        content = {
            if (!viewModel.whatArticleLiked.value.articles.isNullOrEmpty()) {
                LikeScreenContent(
                    navController = navController,
                    viewModel = viewModel,
                    articleDataList = articleDataList!!
                )
            }
        },
        scaffoldState = scaffoldState
    )
}

@Composable
fun LikeScreenContent(
    navController: NavHostController,
    viewModel: SharedViewModel,
    articleDataList: List<ArticleResponse>
) {
    Log.d("suee97", "data size >>> ${articleDataList.size}")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 48.dp)
    ) {
        if (articleDataList.isNullOrEmpty()) {

        } else {
            LazyColumn() {
                items(articleDataList.size) { index ->

                    if (viewModel.whatArticleLiked.value.articles.contains(
                            articleDataList[index].id
                        )
                    ) {
                        NewsArticleUnit(
                            navController = navController,
                            viewModel = viewModel,
                            articleResponse = articleDataList[index],
                            likeIconClicked = {
                                Log.d("suee97", "?????? ?????? ?????? index >>> ${index}")
                                Log.d("suee97", "?????? ?????? ?????? id >>> ${articleDataList[index].id}")
                                if (viewModel.currentUser.value.username.isNullOrEmpty()) {

                                } else {
                                    viewModel.articleId.value = articleDataList[index].id
                                    viewModel.like(
                                        likeBody = LikeBody(
                                            article_id = viewModel.articleId.value,
                                            username = viewModel.currentUser.value.username
                                        )
                                    )
                                }
                            },
                            isLiked = if (viewModel.whatArticleLiked.value.articles.contains(
                                    articleDataList[index].id
                                )
                            ) {
                                true
                            } else {
                                false
                            }
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