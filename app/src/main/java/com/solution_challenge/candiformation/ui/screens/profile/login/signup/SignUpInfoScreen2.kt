package com.solution_challenge.candiformation.ui.screens.profile.login.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.solution_challenge.candiformation.R
import com.solution_challenge.candiformation.components.CustomTopAppBar
import com.solution_challenge.candiformation.ui.SharedViewModel
import com.solution_challenge.candiformation.utils.Constants

@Composable
fun SignUpInfoScreen2(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "개인정보처리방침",
                navBack = true
            )
        },
        content = {
            SignUpInfoScreenContent2()
        }
    )
}

@Composable
fun SignUpInfoScreenContent2() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Constants.CONTENT_INNER_PADDING)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = stringResource(id = R.string.private_info)
        )
    }
}