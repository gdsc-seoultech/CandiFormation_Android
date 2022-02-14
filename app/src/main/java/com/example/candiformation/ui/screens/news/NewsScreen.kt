package com.example.candiformation.ui.screens.news

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.candiformation.models.LikeBody
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.theme.VeryLightGrey_type1
import com.example.candiformation.utils.Constants
import com.example.candiformation.utils.Resource
import kotlinx.coroutines.launch

@Composable
fun NewsScreen(
    viewModel: SharedViewModel,
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            NewsScreenTopAppBar()
        },
        content = {
            NewsScreenContent(
                navController = navController,
                viewModel = viewModel
            )
        }
    )
}

@Composable
fun NewsScreenContent(
    viewModel: SharedViewModel,
    navController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val allArticleData = viewModel.getArticleData.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 48.dp)
    ) {
        if (viewModel.getArticleData.value.isNullOrEmpty()) {
            Log.d("suee97", "getArticleData is null or empty")
        } else {
            LazyColumn() {
                allArticleData.value?.let {
                    items(it.size) { index ->
                        NewsArticleUnit(
                            navController = navController,
                            viewModel = viewModel,
                            articleResponse = allArticleData.value!![index],
                            likeIconClicked = {
                                viewModel.articleId.value = allArticleData.value!![index].id
                                viewModel.like(
                                    likeBody = LikeBody(
                                        article_id = viewModel.articleId.value,
                                        username = viewModel.currentUser.value.username
                                    )
                                )
                            },
                            likeCount = allArticleData.value!![index].like_num
                        )
                    }
                }
            }
        }

        scope.launch {
            val result = viewModel.getArticleData()

            if (result is Resource.Success) {
                Log.d("suee97", "result is Resource.Success")
            } else if (result is Resource.Error) {
                Log.d("suee97", "result is Resource.Error")
            }
        }

        if(!viewModel.isArticleLoading.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = VeryLightGrey_type1
                )
            }
        }
    }
}


@Composable
fun NewsScreenTopAppBar() {
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Text(
                text = "뉴스 기사",
                fontSize = Constants.TOP_APP_BAR_FONT,
                fontWeight = FontWeight.Bold
            )
        }
    )
}