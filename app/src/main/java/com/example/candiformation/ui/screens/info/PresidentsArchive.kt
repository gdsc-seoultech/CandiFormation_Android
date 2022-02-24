package com.example.candiformation.ui.screens.info

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.candiformation.R
import com.example.candiformation.components.CustomTopAppBar
import com.example.candiformation.ui.SharedViewModel

@Composable
fun PresidentsArchive(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    var dialogState by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    var presidentArchiveNum by remember { mutableStateOf(0) }

    val presidentsArchiveList = listOf(
        PresidentsArchiveItem.former_1_lee,
        PresidentsArchiveItem.former_2_yoon,
        PresidentsArchiveItem.former_3_park,
        PresidentsArchiveItem.former_4_choi,
        PresidentsArchiveItem.former_5_chun,
        PresidentsArchiveItem.former_6_roh,
        PresidentsArchiveItem.former_7_kim,
        PresidentsArchiveItem.former_8_kim,
        PresidentsArchiveItem.former_9_roh,
        PresidentsArchiveItem.former_10_lee,
        PresidentsArchiveItem.former_11_park,
        PresidentsArchiveItem.former_12_moon
    )

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Presidents Archive",
                navBack = true
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.padding(top = 6.dp, bottom = 12.dp))
                Image(
                    painter = painterResource(id = R.drawable.president_archive_image),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.padding(top = 6.dp, bottom = 12.dp))
                FormerPresidentCard(
                    name = "이승만",
                    tenure = "1948. 7 ~ 1960. 4",
                    onClicked = {
                        presidentArchiveNum = 0
                        dialogState = true
                    })
                FormerPresidentCard(
                    name = "윤보선",
                    tenure = "1960. 8 ~ 1962. 3",
                    onClicked = {
                        presidentArchiveNum = 1
                        dialogState = true
                    })
                FormerPresidentCard(
                    name = "박정희",
                    tenure = "1962. 3 ~ 1979. 10(권한대행 기간포함)",
                    onClicked = {
                        presidentArchiveNum = 2
                        dialogState = true
                    })
                FormerPresidentCard(
                    name = "최규하",
                    tenure = "1979. 10 ~ 1980. 8(권한대행 기간포함)",
                    onClicked = {
                        presidentArchiveNum = 3
                        dialogState = true
                    })
                FormerPresidentCard(
                    name = "전두환",
                    tenure = "1980. 9 ~ 1988. 2",
                    onClicked = {
                        presidentArchiveNum = 4
                        dialogState = true
                    })
                FormerPresidentCard(
                    name = "노태우",
                    tenure = "1988. 2 ~ 1993. 2",
                    onClicked = {
                        presidentArchiveNum = 5
                        dialogState = true
                    })
                FormerPresidentCard(
                    name = "김영삼",
                    tenure = "1993. 2 ~ 1998. 2",
                    onClicked = {
                        presidentArchiveNum = 6
                        dialogState = true
                    })
                FormerPresidentCard(
                    name = "김대중",
                    tenure = "1998. 2 ~ 2003. 2",
                    onClicked = {
                        presidentArchiveNum = 7
                        dialogState = true
                    })
                FormerPresidentCard(
                    name = "노무현",
                    tenure = "2003. 2 ~ 2008. 2",
                    onClicked = {
                        presidentArchiveNum = 8
                        dialogState = true
                    })
                FormerPresidentCard(
                    name = "이명박",
                    tenure = "2008. 2 ~ 2013. 2",
                    onClicked = {
                        presidentArchiveNum = 9
                        dialogState = true
                    })
                FormerPresidentCard(
                    name = "박근혜",
                    tenure = "2013. 2 ~ 2017. 3",
                    onClicked = {
                        presidentArchiveNum = 10
                        dialogState = true
                    })
                FormerPresidentCard(
                    name = "문재인",
                    tenure = "2017.5.10 ~ 현재",
                    onClicked = {
                        presidentArchiveNum = 11
                        dialogState = true
                    })
                Spacer(modifier = Modifier.height(60.dp))
            }
            FormerPresidentDialog(
                dialogState = dialogState,
                onDismissRequest = { dialogState = !it },
                presidentArchiveNum = presidentArchiveNum,
                presidentsArchiveList = presidentsArchiveList
            )
        }
    )
}

@Composable
fun FormerPresidentDialog(
    dialogState: Boolean,
    onDismissRequest: (dialogState: Boolean) -> Unit,
    presidentArchiveNum: Int,
    presidentsArchiveList: List<PresidentsArchiveItem>
) {
    if (dialogState) {
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.5f),
            backgroundColor = Color.White,
            onDismissRequest = { onDismissRequest(dialogState) },
            text = {
                // 뒤에 정당 배경
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .alpha(0.4f),
                        painter = painterResource(id = R.drawable.president_archive_logo),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                }

                // Content
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .size(140.dp),
                            painter = painterResource(presidentsArchiveList[presidentArchiveNum].imageSrc),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column() {
                            Text(
                                text = presidentsArchiveList[presidentArchiveNum].name,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = presidentsArchiveList[presidentArchiveNum].birth,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = presidentsArchiveList[presidentArchiveNum].tenure,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = presidentsArchiveList[presidentArchiveNum].religion,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = presidentsArchiveList[presidentArchiveNum].family,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        }
                    }
//                    Spacer(modifier = Modifier.height(35.dp))
                }
            },
            shape = RoundedCornerShape(4.dp),
            buttons = {}
        )
    }
}


@Composable
fun FormerPresidentCard(
    name: String,
    tenure: String,
    onClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 9.dp)
            .border(width = 1.dp, color = Color.LightGray)
            .clickable {
                onClicked()
            },
        backgroundColor = Color.White,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = name,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp
            )
            Text(
                text = tenure,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
}