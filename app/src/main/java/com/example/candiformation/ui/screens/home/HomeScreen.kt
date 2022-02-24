package com.example.candiformation.ui.screens.home


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.candiformation.components.CustomTopAppBar
import com.example.candiformation.ui.SharedViewModel
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
    val pagerState = rememberPagerState()
    scope.launch {
        viewModel.getArticle()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Home",
                navBack = false
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                MainArticlePager(
                    viewModel = viewModel,
                    navController = navController,
                    pagerState = pagerState
                )
                Spacer(modifier = Modifier.height(12.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HorizontalPagerIndicator(
                        modifier = Modifier
                            .padding(vertical = 4.dp),
                        pagerState = pagerState
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                TimeCalculateComposable(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    )
}

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

@Composable
fun TimeCalculateComposable(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "20대 대선",
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "사전 투표",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "2022년 3월 5일 D - ${viewModel.getLeftSazunTime()}",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
        Text(
            text = "본 투표",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "2022년 3월 9일 D - ${viewModel.getLeftBonTime()}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
    }
}

