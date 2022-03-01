package com.solution_challenge.candiformation.ui.screens.news.articles

import android.annotation.SuppressLint
import android.content.ClipData
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.solution_challenge.candiformation.components.CustomTopAppBar
import com.solution_challenge.candiformation.models.ArticleResponse
import com.solution_challenge.candiformation.models.CommentResponse
import com.solution_challenge.candiformation.models.LikeBody
import com.solution_challenge.candiformation.ui.SharedViewModel
import com.solution_challenge.candiformation.utils.Constants
import com.solution_challenge.candiformation.utils.Constants.CONTENT_INNER_PADDING
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalMaterialApi
@Composable
fun ArticleScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    // 코멘트, 전체 기사 불러오기
    val scope = rememberCoroutineScope()
    val commentList by viewModel.selectedArticleComments.observeAsState()
    val articleDataList by viewModel.articleDataList.observeAsState()

    scope.launch {
        viewModel.getSelectedArticleComments(viewModel.articleId.value)
        viewModel.getArticle() // 화면 들어왔을 때 모든 기사 정보 불러오기
        viewModel.getArticleLikes(viewModel.articleId.value)
    }

    // 스크롤 컨트롤
    var scrollState = rememberScrollState()

    // 코멘트 작성
    var comment by remember { mutableStateOf("") }
    var isSecret by remember { mutableStateOf(true) }

    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()

    fun launchSnackBar(msg: String) {
        snackScope.launch {
            snackState.showSnackbar(
                message = msg,
                duration = SnackbarDuration.Short
            )
        }
    }

    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    Scaffold(
        topBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                TopAppBar(
                    backgroundColor = Color.White,
                    title = {
                        Text(
                            text = "Article",
                            fontSize = Constants.TOP_APP_BAR_FONT,
                            fontWeight = FontWeight.ExtraBold
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            modifier = Modifier.alpha(1f),
                            onClick = { navController.popBackStack() }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Arrow Back"
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            modifier = Modifier.alpha(1f),
                            onClick = {
                                clipboardManager.setText(AnnotatedString(viewModel.articleLink.value))
                                launchSnackBar("링크가 클립보드에 복사되었습니다.")
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ContentCopy,
                                contentDescription = "Content Copy",
                                tint = Color.Black
                            )
                        }
                    },
                    elevation = 0.dp
                )
                Divider(
                    color = Color.Black,
                    modifier = Modifier
                        .height(5.dp)
                        .padding(horizontal = 8.dp)
                )
            }
        },
        content = {
            ArticleScreenContent(
                navController = navController,
                viewModel = viewModel,
                commentList = commentList!!,
                articleDataList = articleDataList!!,
                scrollState = scrollState
            )
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
        },
        bottomBar = {
            CommentTextField(
                viewModel = viewModel,
                comment = comment,
                sendReq = {
                    if (viewModel.currentUser.value.username.isNullOrEmpty()) {
                        launchSnackBar("로그인이 필요한 서비스입니다.")
                    } else if (comment.isBlank()) {
                        launchSnackBar("댓글을 작성해주세요.")
                    } else {
                        viewModel.currentCommentBody.value.articleId = viewModel.articleId.value
                        viewModel.currentCommentBody.value.content = comment
                        viewModel.currentCommentBody.value.nickname =
                            viewModel.currentUser.value.nickname
                        viewModel.currentCommentBody.value.isSecret = isSecret
                        viewModel.writeComment(viewModel.currentCommentBody.value) // POST
                        comment = ""

                        scope.launch {
                            scrollState.animateScrollBy(
                                animationSpec = tween(400, 100),
                                value = 10000f
                            )
                        }
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
    commentList: List<CommentResponse>,
    articleDataList: List<ArticleResponse>,
    scrollState: ScrollState
) {
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = CONTENT_INNER_PADDING, end = CONTENT_INNER_PADDING, top = 4.dp)
            .verticalScroll(scrollState)
    ) {
        articleTitle(viewModel = viewModel)
        ArticleTime(viewModel = viewModel)
        Spacer(modifier = Modifier.height(24.dp))
        articleContent(navController = navController, viewModel = viewModel)
        Spacer(modifier = Modifier.height(12.dp))
        ThumbnailBox(navController = navController, viewModel = viewModel)
        Spacer(modifier = Modifier.height(8.dp))
        LikeAndComments(
            viewModel = viewModel,
            likeIconClicked = {
                if (viewModel.currentUser.value.username.isNullOrEmpty()) {
                    launchSnackBar()
                } else {
                    viewModel.like(
                        likeBody = LikeBody(
                            article_id = viewModel.articleId.value,
                            username = viewModel.currentUser.value.username
                        )
                    )
                }
            },
            isLiked = viewModel.whatArticleLiked.value.articles.contains(viewModel.articleId.value),
            likeNum = viewModel.getArticleLikes.value,
            commentNum = commentList.size
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider()
        Spacer(modifier = Modifier.height(8.dp))
        CommentView(
            commentList = commentList,
            viewModel = viewModel,
            scrollState = scrollState,
            isDeletable = true
        )
        Spacer(modifier = Modifier.height(48.dp))
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
            .padding(top = 8.dp)
    ) {
        Text(
            text = viewModel.articleTitle.value,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ArticleTime(
    viewModel: SharedViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = "${viewModel.articleDateTime.value}",
            fontSize = 14.sp,
            color = Color.LightGray
        )
    }
}