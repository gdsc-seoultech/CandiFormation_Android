package com.solution_challenge.candiformation.ui.screens.info

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.solution_challenge.candiformation.components.CustomSnackBar
import com.solution_challenge.candiformation.components.launchSnackBar
import com.solution_challenge.candiformation.ui.SharedViewModel
import com.solution_challenge.candiformation.ui.screens.profile.SettingListUnit
import com.solution_challenge.candiformation.ui.screens.profile.SettingTitleUnit
import kotlinx.coroutines.launch

@Composable
fun InfoList(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val snackState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxWidth()) {
        InfoTitleUnit("Archive")
        Divider(
            modifier = Modifier
                .height(5.dp)
                .padding(horizontal = 8.dp),
            color = Color.Black
        )
        InfoListUnit(
            navController = navController,
            viewModel = viewModel,
            title = "Presidents Archive",
            onClicked = {
                navController.navigate("info/presidentArchive") {
                    popUpTo("info")
                }
            }
        )
        GreyDivider()
        InfoListUnit(
            navController = navController,
            viewModel = viewModel,
            title = "Party Archive",
            onClicked = {
                navController.navigate(route = "info/partyArchive") {
                    popUpTo("info")
                }
            }
        )
        GreyDivider()
        Spacer(modifier = Modifier.height(24.dp))
        SettingTitleUnit("Events")
        Divider(
            modifier = Modifier
                .height(5.dp)
                .padding(horizontal = 8.dp),
            color = Color.Black
        )
        SettingListUnit(
            navController = navController,
            viewModel = viewModel,
            title = "Debates",
            onClicked = {
                launchSnackBar(
                    msg = "??????????????????.",
                    snackState = snackState,
                    snackScope = scope
                )
            }
        )
        GreyDivider()
    }
    CustomSnackBar(snackState = snackState, verticalFraction = .4f)
}

@Composable
fun InfoTitleUnit(
    title: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
fun InfoListUnit(
    navController: NavHostController,
    viewModel: SharedViewModel,
    title: String,
    onClicked: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClicked() }) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            text = title,
            fontSize = 18.sp
        )
    }
}

@Composable
fun GreyDivider() {
    Divider(modifier = Modifier.padding(horizontal = 8.dp))
}
