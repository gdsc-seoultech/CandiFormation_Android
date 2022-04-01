package com.solution_challenge.candiformation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CustomSnackBar(
    snackState: SnackbarHostState,
    verticalFraction: Float
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(verticalFraction),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SnackbarHost(
            hostState = snackState
        )
    }
}

fun launchSnackBar(msg: String, snackState: SnackbarHostState, snackScope: CoroutineScope) {
    snackScope.launch {
        snackState.showSnackbar(
            message = msg,
            duration = SnackbarDuration.Short
        )
    }
}