package com.example.candiformation.ui.screens.news

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.candiformation.components.CustomTopAppBar
import com.example.candiformation.models.ArticleResponse
import com.example.candiformation.models.LikeBody
import com.example.candiformation.ui.SharedViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@Composable
fun NewsScreen(
    viewModel: SharedViewModel,
    navController: NavHostController
) {
    val articleDataList by viewModel.articleDataList.observeAsState()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
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
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    if (viewModel.currentUser.value.nickname != "") {
        viewModel.whatArticleLiked(viewModel.currentUser.value.username)
        Log.d(
            "suee97", "what article liked >>> " +
                    "${viewModel.whatArticleLiked.value}"
        )
    }

    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()

    fun launchSnackBar() {
        snackScope.launch {
            snackState.showSnackbar(
                message = "로그인이 필요한 서비스입니다.",
                duration = SnackbarDuration.Short
            )
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = {
            viewModel.refresh()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 48.dp)
        ) {
            Log.d("suee97", "id순서 >> ${articleDataList}")

            if (articleDataList.isNullOrEmpty()) {

            } else {
                LazyColumn() {
                    items(articleDataList.size) { index ->
                        NewsArticleUnit(
                            navController = navController,
                            viewModel = viewModel,
                            articleResponse = articleDataList[index],
                            likeIconClicked = {
                                Log.d("suee97", "현재 누른 기사 index >>> ${index}")
                                Log.d("suee97", "현재 누른 기사 id >>> ${articleDataList[index].id}")
                                if (viewModel.currentUser.value.username.isNullOrEmpty()) {
                                    launchSnackBar()
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
                            isLiked = if (viewModel.whatArticleLiked.value.articles.contains(articleDataList[index].id)) {
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


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.9f),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SnackbarHost(
            hostState = snackState
        )
    }
}


