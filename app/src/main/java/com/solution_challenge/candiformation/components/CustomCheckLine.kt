package com.solution_challenge.candiformation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomCheckLine(
    modifier: Modifier,
    msg: String,
    checkState: Boolean,
    onCheckClicked: () -> Unit,
    onSpecClicked: () -> Unit,
    enabled: Boolean = true
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                onCheckClicked()
            },
            enabled = enabled
        ) {
            if (checkState) {
                Icon(
                    imageVector = Icons.Filled.CheckCircleOutline,
                    contentDescription = "CheckCircleOutline",
                    tint = Color.Red
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.CheckCircleOutline,
                    contentDescription = "CheckCircleOutline",
                    tint = Color.LightGray
                )
            }
        }
        Text(
            modifier = Modifier.padding(end = 12.dp),
            text = msg,
            fontSize = 16.sp
        )
        Text(
            modifier = Modifier
                .padding(end = 12.dp)
                .clickable { onSpecClicked() },
            text = "자세히 보기",
            fontSize = 13.sp
        )
    }
}