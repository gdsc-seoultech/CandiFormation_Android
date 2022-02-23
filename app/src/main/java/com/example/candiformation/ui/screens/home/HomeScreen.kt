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
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    scope.launch {
        viewModel.getArticle()
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Home",
                navBack = false
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                MainArticlePager(
                    viewModel = viewModel,
                    navController = navController
                )
            }
            
        },
        scaffoldState = scaffoldState
    )
}

@ExperimentalPagerApi
@Composable
fun MainArticlePager(
    viewModel: SharedViewModel,
    navController: NavHostController
) {
    val articleDataList by viewModel.articleDataList.observeAsState()
    val pagerState = rememberPagerState()

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
                    painter = rememberImagePainter(articleDataList!![articleDataList!!.size-1-page].images),
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
                        text = articleDataList!![articleDataList!!.size-1-page].title,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }
            }

            HorizontalPagerIndicator(pagerState = pagerState)
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
