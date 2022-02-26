package com.solution_challenge.candiformation.ui.screens.setting.comments

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.solution_challenge.candiformation.components.CustomDialog_type_1
import com.solution_challenge.candiformation.components.CustomTopAppBar
import com.solution_challenge.candiformation.ui.SharedViewModel
import com.solution_challenge.candiformation.ui.screens.news.articles.CommentView

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
                    isDeletable = true
                )
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    )
}
