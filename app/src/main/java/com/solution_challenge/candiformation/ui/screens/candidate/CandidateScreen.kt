package com.solution_challenge.candiformation.ui.screens.candidate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.solution_challenge.candiformation.components.CustomTopAppBar
import com.solution_challenge.candiformation.ui.SharedViewModel
import com.solution_challenge.candiformation.utils.Constants.CONTENT_INNER_PADDING

@Composable
fun CandidateScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    var dialogState by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    var candidateNum by remember { mutableStateOf(0) }

    // 펼치지 마세요
    val candidateItemList = listOf(CandidateItem.lee_1, CandidateItem.yoon_2, CandidateItem.shim_3, CandidateItem.ann_4, CandidateItem.oh_5, CandidateItem.hu_6, CandidateItem.lee_7, CandidateItem.ook_8, CandidateItem.kim_9, CandidateItem.kim_10, CandidateItem.choi_11, CandidateItem.kim_12, CandidateItem.lee_13, CandidateItem.kim_14)

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "Candidates",
                navBack = false
            )
        },
        content = {
            CandidateDialog(
                dialogState = dialogState,
                onDismissRequest = { dialogState = !it },
                candidateNum = candidateNum,
                candidateList = candidateItemList
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = CONTENT_INNER_PADDING)
                    .verticalScroll(state = scrollState)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CandidateCard(
                        modifier = Modifier.weight(1f),
                        name = "이재명",
                        party = "민주당",
                        imgSrc = candidateItemList[0].imageSrc,
                        onClick = {
                            candidateNum = 0
                            dialogState = true
                        },
                    )
                    CandidateCard(
                        modifier = Modifier.weight(1f),
                        name = "윤석열",
                        party = "국민의힘",
                        imgSrc = candidateItemList[1].imageSrc,
                        onClick = {
                            candidateNum = 1
                            dialogState = true
                        }
                    )
                    CandidateCard(
                        modifier = Modifier.weight(1f),
                        name = "심상정",
                        party = "정의당",
                        imgSrc = candidateItemList[2].imageSrc,
                        onClick = {
                            candidateNum = 2
                            dialogState = true
                        }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CandidateCard(
                        modifier = Modifier.weight(1f),
                        name = "안철수",
                        party = "국민의당",
                        imgSrc = candidateItemList[3].imageSrc,
                        onClick = {
                            candidateNum = 3
                            dialogState = true
                        }
                    )
                    CandidateCard(
                        modifier = Modifier.weight(1f),
                        name = "오준호",
                        party = "기본소득당",
                        imgSrc = candidateItemList[4].imageSrc,
                        onClick = {
                            candidateNum = 4
                            dialogState = true
                        }
                    )
                    CandidateCard(
                        modifier = Modifier.weight(1f),
                        name = "허경영",
                        party = "국가혁명당",
                        imgSrc = candidateItemList[5].imageSrc,
                        onClick = {
                            candidateNum = 5
                            dialogState = true
                        }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CandidateCard(
                        modifier = Modifier.weight(1f),
                        name = "이백윤",
                        party = "노동당",
                        imgSrc = candidateItemList[6].imageSrc,
                        onClick = {
                            candidateNum = 6
                            dialogState = true
                        }
                    )
                    CandidateCard(
                        modifier = Modifier.weight(1f),
                        name = "옥은호",
                        party = "새누리당",
                        imgSrc = candidateItemList[7].imageSrc,
                        onClick = {
                            candidateNum = 7
                            dialogState = true
                        }
                    )
                    CandidateCard(
                        modifier = Modifier.weight(1f),
                        name = "김동연",
                        party = "새로운물결",
                        imgSrc = candidateItemList[8].imageSrc,
                        onClick = {
                            candidateNum = 8
                            dialogState = true
                        }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CandidateCard(
                        modifier = Modifier.weight(1f),
                        name = "김경재",
                        party = "신자유민주연합",
                        imgSrc = candidateItemList[9].imageSrc,
                        onClick = {
                            candidateNum = 9
                            dialogState = true
                        }
                    )
                    CandidateCard(
                        modifier = Modifier.weight(1f),
                        name = "조원진",
                        party = "우리공화당",
                        imgSrc = candidateItemList[10].imageSrc,
                        onClick = {
                            candidateNum = 10
                            dialogState = true
                        }
                    )
                    CandidateCard(
                        modifier = Modifier.weight(1f),
                        name = "김재연",
                        party = "진보당",
                        imgSrc = candidateItemList[11].imageSrc,
                        onClick = {
                            candidateNum = 11
                            dialogState = true
                        }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CandidateCard(
                        modifier = Modifier.weight(1f),
                        name = "이경희",
                        party = "통일한국당",
                        imgSrc = candidateItemList[12].imageSrc,
                        onClick = {
                            candidateNum = 12
                            dialogState = true
                        }
                    )
                    CandidateCard(
                        modifier = Modifier.weight(1f),
                        name = "김민찬",
                        party = "한류연합당",
                        imgSrc = candidateItemList[13].imageSrc,
                        onClick = {
                            candidateNum = 13
                            dialogState = true
                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.size(60.dp))
            }
        }
    )
}