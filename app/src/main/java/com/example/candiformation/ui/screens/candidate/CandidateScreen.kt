package com.example.candiformation.ui.screens.candidate

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
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
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "이재명",
                        colorId = MinjudangColor,
                        imgSrc = R.drawable.lee,
                        logoSrc = R.drawable.minjudang_logo
                    )
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "윤석열",
                        colorId = GukkimColor,
                        imgSrc = R.drawable.yoon,
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
                        name = "이재명",
                        colorId = MinjudangColor,
                        imgSrc = R.drawable.lee,
                        logoSrc = R.drawable.minjudang_logo
                    )
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "윤석열",
                        colorId = GukkimColor,
                        imgSrc = R.drawable.yoon,
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
                        name = "이재명",
                        colorId = MinjudangColor,
                        imgSrc = R.drawable.lee,
                        logoSrc = R.drawable.minjudang_logo
                    )
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "윤석열",
                        colorId = GukkimColor,
                        imgSrc = R.drawable.yoon,
                        logoSrc = R.drawable.minjudang_logo
                    )
                }
            }
        }
    )
}

@Composable
fun CandidateCard(
    navController: NavHostController,
    viewModel: SharedViewModel,
    name: String,
    colorId: Color,
    imgSrc: Int,
    logoSrc: Int
) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .border(2.dp, Color.Magenta)
            .background(colorId),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(vertical = 4.dp),
            painter = painterResource(id = logoSrc),
            contentDescription = "${name} 소속당 로고"
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .border(2.dp, Color.Black)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = imgSrc),
                    contentDescription = name
                )
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = name,
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Composable
fun CandidateScreenTopAppBar() {
    TopAppBar(
        backgroundColor = Color.White,
        contentPadding = PaddingValues(horizontal = CONTENT_INNER_PADDING)
    ) {
        Text(
            text = "후보자",
            fontSize = Constants.TOP_APP_BAR_FONT,
            fontWeight = FontWeight.Bold
        )
    }
}