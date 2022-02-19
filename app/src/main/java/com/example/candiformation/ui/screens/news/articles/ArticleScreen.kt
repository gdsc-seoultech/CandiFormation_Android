package com.example.candiformation.ui.screens.news.articles

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.IosShare
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.models.CommentResponse
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.utils.Constants
import com.example.candiformation.utils.Constants.CONTENT_INNER_PADDING

@ExperimentalMaterialApi
@Composable
fun ArticleScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val commentList by viewModel.selectedArticleComments.observeAsState()
    viewModel.getSelectedArticleComments(viewModel.articleId.value)
    var comment by remember { mutableStateOf("") }
    var isSecret by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            ArticleScreenTopAppBar(
                navController = navController,
                viewModel = viewModel
            )
        },
        content = {
            ArticleScreenContent(
                navController = navController,
                viewModel = viewModel,
                commentList = commentList!!
            )
        },
        bottomBar = {
            CommentTextField(
                viewModel = viewModel,
                comment = comment,
                sendReq = {
                    if (comment.isNotBlank()) {
                        viewModel.currentCommentBody.value.articleId = viewModel.articleId.value
                        viewModel.currentCommentBody.value.content = comment
                        viewModel.currentCommentBody.value.nickname =
                            viewModel.currentUser.value.nickname
                        viewModel.currentCommentBody.value.isSecret = isSecret
                        viewModel.writeComment(viewModel.currentCommentBody.value) // POST
                        comment = ""
                    }
                },
                onValueChanged = { comment = it },
                isSecret = isSecret,
                anonymousClicked = {
                    isSecret = !isSecret
                }
            )
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun ArticleScreenContent(
    navController: NavHostController,
    viewModel: SharedViewModel,
    commentList: List<CommentResponse>
) {
    var scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = CONTENT_INNER_PADDING, end = CONTENT_INNER_PADDING, top = 12.dp)
            .verticalScroll(scrollState)
    ) {
        articleTitle(viewModel = viewModel)
        ArticleTime()
        Spacer(modifier = Modifier.height(24.dp))
        articleContent(navController = navController, viewModel = viewModel)
        Spacer(modifier = Modifier.height(12.dp))
        ThumbnailBox(navController = navController, viewModel = viewModel)
        Spacer(modifier = Modifier.height(8.dp))
        LikeAndComments(
            viewModel = viewModel,
            likeIconClicked = {},
            isLiked = viewModel.whatArticleLiked.value.articles.contains(viewModel.articleId.value),
            likeNum = 0,
            commentNum = commentList.size
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        CommentView(commentList = commentList, viewModel = viewModel)
        Spacer(modifier = Modifier.height(48.dp))
    }
}

@Composable
fun articleContent(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = viewModel.articleContent.value,
            fontSize = 18.sp
        )
    }
}

@Composable
fun articleTitle(
    viewModel: SharedViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = viewModel.articleTitle.value,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ArticleScreenTopAppBar(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "News",
                    fontSize = Constants.TOP_APP_BAR_FONT,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Arrow Back"
                )
            }
        },
        actions = {
            IconButton(
                onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.IosShare,
                    contentDescription = "Ios Share",
                    tint = Color.Black
                )
            }
        }
    )
}

@Composable
fun ArticleTime() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = "01/11 17:35",
            fontSize = 14.sp,
            color = Color.LightGray
        )
    }
}