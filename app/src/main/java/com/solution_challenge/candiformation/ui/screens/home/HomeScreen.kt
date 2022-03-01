package com.solution_challenge.candiformation.ui.screens.home


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.solution_challenge.candiformation.R
import com.solution_challenge.candiformation.ui.SharedViewModel
import com.solution_challenge.candiformation.utils.Constants
import com.solution_challenge.candiformation.utils.Constants.CONTENT_INNER_PADDING
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val scope = rememberCoroutineScope()
    val startIndex = Int.MAX_VALUE / 2
    val subPagerState = rememberPagerState(initialPage = startIndex)

    scope.launch {
        viewModel.getArticle()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeScreenTopAppBar()
        },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // MainArticlePager
                MainArticlePager(
                    viewModel = viewModel,
                    navController = navController
                )
                Spacer(modifier = Modifier.height(16.dp))

                // SubArticlePager
                Column(
                    modifier = Modifier
                        .padding(horizontal = CONTENT_INNER_PADDING),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SubPosterPager(
                        imgList = listOf(
                            R.drawable.poster_1,
                            R.drawable.poster_2,
                            R.drawable.poster_3,
                            R.drawable.poster_4,
                            R.drawable.poster_5,
                            R.drawable.poster_6,
                            R.drawable.poster_7,
                            R.drawable.poster_8,
                            R.drawable.poster_9,
                            R.drawable.poster_10,
                            R.drawable.poster_11,
                            R.drawable.poster_12,
                            R.drawable.poster_13,
                            R.drawable.poster_14
                        ),
                        pagerState_2 = subPagerState,
                        startIndex = startIndex
                    )
                }
            }
        }
    )
}



@Composable
fun HomeScreenTopAppBar() {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            backgroundColor = Color.White,
            title = {
                Text(
                    text = "Home",
                    fontSize = Constants.TOP_APP_BAR_FONT,
                    fontWeight = FontWeight.ExtraBold
                )
            },
            elevation = 0.dp
        )
    }
}

