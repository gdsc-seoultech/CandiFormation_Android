package com.solution_challenge.candiformation.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.solution_challenge.candiformation.ui.SharedViewModel

@ExperimentalPagerApi
@Composable
fun MainArticlePager(
    viewModel: SharedViewModel,
    navController: NavHostController,
    pagerState: PagerState
) {
    val articleDataList by viewModel.articleDataList.observeAsState()

    if (!articleDataList.isNullOrEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.4f)
                .background(Color.Black)
        ) {
            HorizontalPager(
                count = 3,
                state = pagerState
            ) { page -> // 0, 1, 2
                Image(
                    painter = rememberImagePainter(articleDataList!![articleDataList!!.size - 1 - page].images),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = articleDataList!![articleDataList!!.size - 1 - page].title,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }
            }
        }
    }
}