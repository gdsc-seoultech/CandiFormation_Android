package com.example.candiformation.ui.screens.setting.comments

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.candiformation.components.CustomTopAppBar
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.screens.news.articles.CommentView
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CommentsScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    viewModel.getAllComments(viewModel.currentUser.value.username)
    val commentList by viewModel.userComments.observeAsState()

    Log.d("suee97", "commentList >>> ${commentList}")

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Comments",
                navBack = true
            )
        },
        content = {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                CommentView(
                    commentList = commentList!!.asReversed(),
                    viewModel = viewModel,
                    scrollState = scrollState,
                    isDeletable = false
                )
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    )
}
