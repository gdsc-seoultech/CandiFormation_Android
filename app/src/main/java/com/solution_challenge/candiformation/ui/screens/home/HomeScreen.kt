package com.solution_challenge.candiformation.ui.screens.home


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
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
    val pagerState = rememberPagerState()
    val startIndex = Int.MAX_VALUE / 2
    val pagerState_2 = rememberPagerState(initialPage = startIndex)

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
//                modifier = Modifier
//                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainArticlePager(
                    viewModel = viewModel,
                    navController = navController,
                    pagerState = pagerState
                )
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
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .padding(horizontal = CONTENT_INNER_PADDING),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ShowPoster(
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
                        pagerState_2 = pagerState_2,
                        startIndex = startIndex
                    )
                }
            }
        }
    )
}

@ExperimentalPagerApi
@Composable
fun ShowPoster(
    imgList: List<Int>,
    pagerState_2: PagerState,
    startIndex: Int
) {
    val pageCount = 14

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.8f)
    ) {
        SmallTitle("OFFICIAL POSTER")

        HorizontalPager(
            count = Int.MAX_VALUE,
            state = pagerState_2,
            contentPadding = PaddingValues(horizontal = 80.dp)
        ) { index ->
            val page = (index - startIndex).floorMod(pageCount)
            Image(
                painter = painterResource(id = imgList[page]),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun SmallTitle(title: String) {
    Text(
        text = title,
        fontSize = Constants.TOP_APP_BAR_FONT,
        fontWeight = FontWeight.ExtraBold
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

private fun Int.floorMod(other: Int): Int = when (other) {
    0 -> this
    else -> this - floorDiv(other) * other
}