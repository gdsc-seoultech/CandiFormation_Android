package com.solution_challenge.candiformation.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.solution_challenge.candiformation.ui.SharedViewModel

@ExperimentalPagerApi
@Composable
fun MainArticlePager(
    viewModel: SharedViewModel,
    navController: NavHostController
) {
    val pagerState = rememberPagerState()
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
            ) { index ->
                Image(
                    painter = rememberImagePainter(
                        articleDataList!![index].images
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable{
                            viewModel.articleId.value = articleDataList!![index].id
                            viewModel.articleTitle.value = articleDataList!![index].title
                            viewModel.articleContent.value = articleDataList!![index].thumnail
                            viewModel.articleAgency.value = articleDataList!![index].news_agency
                            viewModel.articleLink.value = articleDataList!![index].link
                            viewModel.articleImage.value = articleDataList!![index].images
                            viewModel.articleLikeNum.value = articleDataList!![index].like_num
                            viewModel.articleCommentNum.value = articleDataList!![index].comment_num
                            viewModel.articleDateTime.value = articleDataList!![index].date_time

                            navController.navigate("news/articles/selectedArticle") {
                                popUpTo("news/articles") { inclusive = true }
                            }
                        }
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = articleDataList!![index].title,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }
            }
        }

        // Indicator
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            HorizontalPagerIndicator(
                modifier = Modifier
                    .padding(vertical = 4.dp),
                pagerState = pagerState,
                activeColor = Color.White,
                inactiveColor = Color.LightGray
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.4f)
                .background(Color.Black)
        ) {
            
        }
    }
}
