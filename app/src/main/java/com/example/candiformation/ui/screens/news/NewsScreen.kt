package com.example.candiformation.ui.screens.news

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.candiformation.components.CustomTopAppBar
import com.example.candiformation.models.ArticleResponse
import com.example.candiformation.models.LikeBody
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.theme.VeryLightGrey_type1
import com.example.candiformation.utils.Constants
import com.example.candiformation.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun NewsScreen(
    viewModel: SharedViewModel,
    navController: NavHostController
) {
    LaunchedEffect(true) {
        viewModel.getArticle() // 화면 들어왔을 때 모든 기사 정보 불러오기
    }
    val articleDataList by viewModel.articleDataList.observeAsState()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Articles",
                navBack = false
            )
        },
        content = {
            NewsScreenContent(
                navController = navController,
                viewModel = viewModel,
                articleDataList = articleDataList!!
            )
        }
    )
}

@Composable
fun NewsScreenContent(
    viewModel: SharedViewModel,
    navController: NavHostController,
    articleDataList: List<ArticleResponse>
) {
    if(viewModel.currentUser.value.nickname != "") {
        viewModel.whatArticleLiked(viewModel.currentUser.value.username)
        Log.d("suee97", "what article liked >>> " +
                "${viewModel.whatArticleLiked.value}")
    }

//    val allArticleData = viewModel.getArticleData.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 48.dp)
    ) {
        if (articleDataList.isNullOrEmpty()) {
            Log.d("suee97", "getArticleData is null or empty")
        } else {
            LazyColumn() {
                items(articleDataList.size) { index ->
                    NewsArticleUnit(
                        navController = navController,
                        viewModel = viewModel,
                        articleResponse = articleDataList[index],
                        likeIconClicked = {
                            viewModel.articleId.value = articleDataList[index].id
                            viewModel.like(
                                likeBody = LikeBody(
                                    article_id = viewModel.articleId.value,
                                    username = viewModel.currentUser.value.username
                                )
                            )
                        },
                        isLiked = if(viewModel.whatArticleLiked.value.articles.contains(index+1)) {
                            true
                        } else {
                            false
                        },
                        articleDataList = articleDataList[index]
                    )
                }
            }
        }

//        scope.launch {
//            val result = viewModel.getArticleData()
//
//            if (result is Resource.Success) {
//                Log.d("suee97", "result is Resource.Success")
//            } else if (result is Resource.Error) {
//                Log.d("suee97", "result is Resource.Error")
//            }
//        }

//        if (!viewModel.isArticleLoading.value) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                CircularProgressIndicator(
//                    color = VeryLightGrey_type1
//                )
//            }
//        }
    }
}


