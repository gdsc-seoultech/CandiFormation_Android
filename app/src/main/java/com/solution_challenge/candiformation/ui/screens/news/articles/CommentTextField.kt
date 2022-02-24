package com.solution_challenge.candiformation.ui.screens.news.articles

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingFlat
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solution_challenge.candiformation.ui.SharedViewModel
import com.solution_challenge.candiformation.ui.theme.VeryLightGrey_type2

@Composable
fun CommentTextField(
    viewModel: SharedViewModel,
    comment: String,
    onValueChanged: (String) -> Unit,
    sendReq: () -> Unit,
    isSecret: Boolean,
    anonymousClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(Color.White)
            .border(1.dp, Color.Black),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier
            .fillMaxHeight()
            .weight(1f)
            .clickable { anonymousClicked() }
            .border(1.dp, Color.Black),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = "익명",
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                color = if(isSecret) Color.Black else Color.LightGray
            )
        }

        TextField(
            modifier = Modifier
                .fillMaxHeight()
                .weight(4f),
            value = comment,
            onValueChange = { onValueChanged(it) },
            placeholder = { Text("댓글을 입력해주세요") },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.LightGray,
                placeholderColor = VeryLightGrey_type2
            ),
        )

        Row(modifier = Modifier
            .fillMaxHeight()
            .weight(1f)
            .clickable { sendReq() }
            .border(1.dp, Color.Black),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.Filled.TrendingFlat,
                contentDescription = "TrendingFlat"
            )
        }
    }
}