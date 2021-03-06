package com.solution_challenge.candiformation.ui.screens.profile.appInfo


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HowToVote
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.solution_challenge.candiformation.R
import com.solution_challenge.candiformation.components.CustomTopAppBar
import com.solution_challenge.candiformation.ui.SharedViewModel
import com.solution_challenge.candiformation.utils.Constants

@Composable
fun AppInfoScreen(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(navController = navController, title = "Information", navBack = true)
        },
        content = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                AppInfoTopLogo()
                Spacer(modifier = Modifier.height(16.dp))
                AppInfoScreenContent()
            }
        }
    )
}

@Composable
fun AppInfoTopLogo() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Constants.CONTENT_INNER_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )
        {
            Icon(
                imageVector = Icons.Default.HowToVote,
                contentDescription = "HowToVote Icon",
                tint = Color.Red,
                modifier = Modifier.size(70.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Candidate Information",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Candiformation",
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Candiformation??? ?????? ?????? ?????? ?????? ??? ???????????? ?????? ???????????? ????????? ?????? ??? ????????? ?????? ???????????????????????????.",
                    fontSize = 9.sp
                )
            }
        }
    }
}

@Composable
fun AppInfoScreenContent() {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        AppInfoScreenCard(
            title = "App version",
            description = stringResource(id = R.string.app_version)
        )
        AppInfoScreenCard(title = "Developers", description = "Wee SR\nShin YB\nOh SU\nYang YS")
        AppInfoScreenCard(title = "Contacts / Bug Report", description = "atn1su@gmail.com")
        AppInfoScreenCard(title = "From.", description = "Google Solution Challenge")
        AppInfoScreenCard(title = "Github", description = "CandiFormation_Android") {
            uriHandler.openUri("https://github.com/gdsc-seoultech/CandiFormation_Android")
        }
    }
}

@Composable
fun AppInfoScreenCard(
    title: String,
    description: String,
    onClicked: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 9.dp)
            .border(width = 1.dp, color = Color.Black)
            .clickable {
                onClicked()
            },
        backgroundColor = Color.White,
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp
            )
            Text(
                text = description,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
}