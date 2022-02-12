package com.example.candiformation.ui.screens.candidate

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.utils.Constants
import com.example.candiformation.R
import com.example.candiformation.ui.theme.GukkimColor
import com.example.candiformation.ui.theme.MinjudangColor
import com.example.candiformation.utils.Constants.CONTENT_INNER_PADDING

@Composable
fun CandidateScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            CandidateScreenTopAppBar()
        },
        content = {
            val scrollState = rememberScrollState()
            LaunchedEffect(Unit) { scrollState.animateScrollTo(0) }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = CONTENT_INNER_PADDING)
                    .verticalScroll(state = scrollState)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "이재명",
                        party = "민주",
                        colorId = MinjudangColor,
                        imgSrc = R.drawable.lee,
                        logoSrc = R.drawable.minjudang_logo
                    )
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "윤석열",
                        party = "국민의힘",
                        colorId = GukkimColor,
                        imgSrc = R.drawable.yoon,
                        logoSrc = R.drawable.minjudang_logo
                    )
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "심상정",
                        party = "정의당",
                        colorId = MinjudangColor,
                        imgSrc = R.drawable.lee,
                        logoSrc = R.drawable.minjudang_logo
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "안철수",
                        party = "국민의당",
                        colorId = GukkimColor,
                        imgSrc = R.drawable.yoon,
                        logoSrc = R.drawable.minjudang_logo
                    )
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "심상정",
                        party = "정의당",
                        colorId = MinjudangColor,
                        imgSrc = R.drawable.lee,
                        logoSrc = R.drawable.minjudang_logo
                    )
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "심상정",
                        party = "정의당",
                        colorId = MinjudangColor,
                        imgSrc = R.drawable.lee,
                        logoSrc = R.drawable.minjudang_logo
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 80.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "오준호",
                        party = "숭구리당당숭당당",
                        colorId = MinjudangColor,
                        imgSrc = R.drawable.lee,
                        logoSrc = R.drawable.minjudang_logo
                    )
                }
            }
        }
    )
}


@Composable
fun CandidateScreenTopAppBar() {
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "후보자",
                    fontSize = Constants.TOP_APP_BAR_FONT,
                    fontWeight = FontWeight.Bold
                )
            }
        },
    )
}