package com.solution_challenge.candiformation.ui.screens.info.partyArchive

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.solution_challenge.candiformation.R
import com.solution_challenge.candiformation.components.CustomTopAppBar
import com.solution_challenge.candiformation.ui.SharedViewModel
import com.solution_challenge.candiformation.ui.screens.info.presidentArchive.FormerPresidentCard
import com.solution_challenge.candiformation.ui.screens.info.presidentArchive.FormerPresidentDialog

@Composable
fun PartyArchiveScreen(
    navController: NavHostController,
    viewModel: SharedViewModel,

    ) {
    var scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Presidents Archive",
                navBack = true
            )
        },
        content = {
            PartyArchiveContents(
                navController = navController,
                viewModel = viewModel,
                scrollState = scrollState
            )
        }
    )

}

@Composable
fun PartyArchiveContents(
    navController: NavHostController,
    viewModel: SharedViewModel,
    scrollState: ScrollState
) {
    Image(
        painter = painterResource(id = R.drawable.republic_logo),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        alpha = 0.2f
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .verticalScroll(scrollState)
    )
    {
        Spacer(modifier = Modifier.padding(top = 6.dp, bottom = 12.dp))
        Image(
            painter = painterResource(id = R.drawable.minjudang_logo),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "민주당",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 15.sp,
            color = Color.Black
        )
        Text(
            text = "자유주의 / 사회자유주의 / 풀뿌리 민주주의",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color.Gray
        )
        Text(
            text = "의원: 172/295 석",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = Color.LightGray
        )
        Text(
            text = "대표: 송영길",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = Color.LightGray
        )
        Text(
            text = "창당: 2014년",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = Color.LightGray
        )
        Text(
            text = "더불어민주당은 대한민국의 정당이다.\n대한민국의 집권 여당이자, 현 제21대 국회의 원내 제1당이며, 당원 수가 가장 많은 최대 규모의 정당이다. 2014년 3월 26일 '새정치민주연합'으로 창당되었고 새정치민주연합 분당 이후 문재인 당시 대표의 주도로 2015년 12월 28일 당명을 '더불어민주당'으로 바꾸었다. 2016년 10월 31일에는 민주당을, 제21대 국회의원 선거 이후인 2020년 5월 18일에는 위성정당인 더불어시민당을 흡수합당하였고, 2022년 1월 18일에는 민주당계 정당인 열린민주당을 흡수합당하였다.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 10.sp,
            color = Color.LightGray
        )

    }
}