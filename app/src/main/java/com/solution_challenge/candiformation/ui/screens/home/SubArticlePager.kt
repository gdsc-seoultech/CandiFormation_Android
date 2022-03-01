package com.solution_challenge.candiformation.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.solution_challenge.candiformation.utils.Constants

@ExperimentalPagerApi
@Composable
fun SubPosterPager(
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

private fun Int.floorMod(other: Int): Int = when (other) {
    0 -> this
    else -> this - floorDiv(other) * other
}