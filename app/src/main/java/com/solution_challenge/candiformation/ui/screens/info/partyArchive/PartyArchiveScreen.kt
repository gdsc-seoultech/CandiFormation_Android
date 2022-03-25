package com.solution_challenge.candiformation.ui.screens.info.partyArchive

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
                title = "Party Archive",
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
//    Image(
//        painter = painterResource(id = R.drawable.republic_logo),
//        contentDescription = null,
//        modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight(),
//        alpha = 0.1f
//    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.padding(top = 6.dp, bottom = 12.dp))
        //민주당//

        Image(
            painter = painterResource(id = R.drawable.minjudang_logo),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 100.dp, vertical = 15.dp)
        )
        Text(
            text = "자유주의 / 사회자유주의 / 풀뿌리 민주주의",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "의원: 172/295 석",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "대표: 송영길",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "창당: 2014년",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "더불어민주당은 대한민국의 정당이다.\n대한민국의 집권 여당이자, 현 제21대 국회의 원내 제1당이며, 당원 수가 가장 많은 최대 규모의 정당이다. 2014년 3월 26일 '새정치민주연합'으로 창당되었고 새정치민주연합 분당 이후 문재인 당시 대표의 주도로 2015년 12월 28일 당명을 '더불어민주당'으로 바꾸었다. 2016년 10월 31일에는 민주당을, 제21대 국회의원 선거 이후인 2020년 5월 18일에는 위성정당인 더불어시민당을 흡수합당하였고, 2022년 1월 18일에는 민주당계 정당인 열린민주당을 흡수합당하였다.",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = Color.LightGray,
        )
        Divider(modifier = Modifier.padding(horizontal = 8.dp, vertical = 15.dp))

        //국민의힘//
        Image(
            painter = painterResource(id = R.drawable.poeplepowerparty_logo),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 100.dp, vertical = 15.dp)
        )
        Text(
            text = "보수주의 / 경제적 자유주의",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "의원: 106/295 석",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "대표: 이준석",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "창당: 2020년",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "국민의힘은 대한민국의 보수정당이다. \n 현재 대한민국의 제1야당이자 대한민국 국회(제21대 국회)의 원내 제2당이다. 2020년 2월 17일 자유한국당과 새로운보수당, 미래를향한전진4.0 등의 보수정당들이 신설 합당하여 미래통합당이라는 당명으로 창당하였고, 21대 총선 이후 자매정당인 미래한국당을 흡수하였으며, 김종인 비대위가 주도하는 당 쇄신정책의 일환으로 동년 9월 2일 당명을 국민의힘으로 변경하였다. 한국 보수진영의 계보를 잇는 적통이라고 할 수 있으며, 1997년 신한국당과 통합민주당의 신설합당을 통해 창당된 한나라당을 당의 공식적인 기원으로 규정하고 있다",
            modifier = Modifier.padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = Color.LightGray,

        )
        Divider(modifier = Modifier.padding(horizontal = 8.dp, vertical = 15.dp))

        //정의당//
        Image(
            painter = painterResource(id = R.drawable.justiceparty_logo),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 100.dp, vertical = 15.dp)
        )
        Text(
            text = "진보주의 / 사회민주주의 ",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "의원: 6/295 석",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "대표: 여영국",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "창당: 2012년",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "정의당은 대한민국의 제2야당으로 사회민주주의 진보정당이다.\n통합진보당의 당 내 패권주의와 종북주의를 우려한 비당권파들이 통합진보당을 탈당한 후 모여 결성한 정당이다. 2012년 10월 18일 창당대회를 열고 진보정의당이란 당명으로 정식 정당 등록하였다. 이후 2013년 7월 21일 당명을 정의당으로 개정하였다. 통합진보당이 헌법재판소의 판결에 의해 해산되면서 대한민국 국회에 진입한 유일한 진보정당이 되었다. 2015년 11월 22일, 통합당대회를 통해 기존 정의당, 노동당에서 탈당한 평등사회네트워크, 노동정치연대, 국민모임이 '노동의 희망, 시민의 꿈'을 슬로건으로 하는 새로운 정의당을 탄생시켰다.",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = Color.LightGray
        )
        Divider(modifier = Modifier.padding(horizontal = 8.dp, vertical = 15.dp))

        //국민의당//
        Image(
            painter = painterResource(id = R.drawable.thepeoplesparty_logo),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 100.dp, vertical = 15.dp)
        )
        Text(
            text = "보수자유주의 / 경제적 자유주의 ",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "의원: 3/295 석",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "대표: 안철수",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "창당: 2020년",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "2020년 1월 29일 안철수가 바른미래당을 탈당해, 동년 2월 23일 공식 창당한 정당이다. 자신이 4년 전에 창당한 당과 이름이 같다. 이는 처음 '국민당'으로 창당하려 한 이름을 선관위가 다른 정당과 당명이 유사하다며 사용을 불허하는 유권해석을 하였기 때문이다. 2022년 제20대 대통령 선거 후 국민의힘과 합당하기로 했다.",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = Color.LightGray
        )

        //기본소득당
        Image(
            painter = painterResource(id = R.drawable.basicincomeparty_logo),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 100.dp, vertical = 15.dp)
        )
        Text(
            text = "기본소득제 / 사회자유주의 ",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "의원: 1/295 석",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "대표: 신지혜",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "창당: 2020년",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "제20대 대통령 선거에 기본소득 베스트셀러 작가이자, 용혜인 의원 비서관을 지낸 오준호가 누구나 나답게 기본소득 대한민국을 슬로건으로 대통령 후보에 출마했다.",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = Color.LightGray
        )
        //시대전환
        Image(
            painter = painterResource(id = R.drawable.saroun_logo),
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 100.dp, vertical = 15.dp)
        )
        Text(
            text = "실용주의 / 중도주의 ",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "의원: 1/295 석",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "대표: 신지혜",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "창당: 2020년",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            text = "제20대 대통령 선거에 기본소득 베스트셀러 작가이자, 용혜인 의원 비서관을 지낸 오준호가 누구나 나답게 기본소득 대한민국을 슬로건으로 대통령 후보에 출마했다.",
            modifier = Modifier
                .padding(horizontal = 12.dp),
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.size(50.dp))

    }
}