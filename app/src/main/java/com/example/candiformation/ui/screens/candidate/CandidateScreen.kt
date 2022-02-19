package com.example.candiformation.ui.screens.candidate

import android.app.assist.AssistContent
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
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
    var dialogState by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    CandidateDialog(
        dialogState = dialogState,
        onDismissRequest = { dialogState = !it })

    Scaffold(
        topBar = {
            CandidateScreenTopAppBar()
        },
        content = {


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
                        navController = navController,
                        viewModel = viewModel,
                        name = "이재명",
                        party = "민주당",
                        imgSrc = R.drawable.lee,
                        onClick = { dialogState = true },
                    )
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "윤석열",
                        party = "국민의힘",
                        imgSrc = R.drawable.yoon,
                        onClick = { dialogState = true }
                    )
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "심상정",
                        party = "정의당",
                        imgSrc = R.drawable.shim,
                        onClick = { dialogState = true }
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
                        navController = navController,
                        viewModel = viewModel,
                        name = "안철수",
                        party = "국민의당",
                        imgSrc = R.drawable.ann,
                        onClick = { dialogState = true }
                    )
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "오준호",
                        party = "기본소득당",
                        imgSrc = R.drawable.oh_5,
                        onClick = { dialogState = true }
                    )
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "허경영",
                        party = "국가혁명당",
                        imgSrc = R.drawable.hu_6,
                        onClick = { dialogState = true }
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
                        navController = navController,
                        viewModel = viewModel,
                        name = "이백윤",
                        party = "노동당",
                        imgSrc = R.drawable.lee_7,
                        onClick = { dialogState = true }
                    )
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "옥은호",
                        party = "새누리당",
                        imgSrc = R.drawable.ook_8,
                        onClick = { dialogState = true }
                    )
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "김동연",
                        party = "새로운물결",
                        imgSrc = R.drawable.kim_9,
                        onClick = { dialogState = true }
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
                        navController = navController,
                        viewModel = viewModel,
                        name = "김경재",
                        party = "신자유민주연합",
                        imgSrc = R.drawable.kim_10,
                        onClick = { dialogState = true }
                    )
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "조원진",
                        party = "우리공화당",
                        imgSrc = R.drawable.choi_11,
                        onClick = { dialogState = true }
                    )
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "김재연",
                        party = "진보당",
                        imgSrc = R.drawable.kim_12,
                        onClick = { dialogState = true }
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
                        navController = navController,
                        viewModel = viewModel,
                        name = "이경희",
                        party = "통일한국당",
                        imgSrc = R.drawable.lee_13,
                        onClick = { dialogState = true }
                    )
                    CandidateCard(
                        navController = navController,
                        viewModel = viewModel,
                        name = "김민찬",
                        party = "한류연합당",
                        imgSrc = R.drawable.kim_14,
                        onClick = {
                            dialogState = true
                        }
                    )
                }
                Spacer(modifier = Modifier.size(60.dp))
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

@Composable
fun CandidateDialog(
    dialogState: Boolean,
    onDismissRequest: (dialogState: Boolean) -> Unit
) {
    if (dialogState) {
        AlertDialog(
            backgroundColor = Color.LightGray,
            onDismissRequest = {
                onDismissRequest(dialogState)
            },
            title = null,
            text = null,
            buttons = {

                Column(horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Spacer(modifier = Modifier.padding(vertical = 16.dp))

                    Image(
                        painter = painterResource(id = R.drawable.lee),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                    )

                    Spacer(modifier = Modifier.padding(vertical = 16.dp))

                    Text(
                        text = "이재명 후보가 궁금하신가요?",
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 12.sp,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.padding(vertical = 16.dp))

                    val context = LocalContext.current

                    TextButton(
                        modifier = Modifier
                            .height(40.dp)
                            .weight(1f)
                            .padding(all = 4.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(4.dp)),
                        onClick = {
                            onDismissRequest(dialogState)
                            Toast.makeText(context, "확인", Toast.LENGTH_LONG).show()
                        },
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {


                    }
                }

            },
            shape = RoundedCornerShape(4.dp)
        )
    }
}