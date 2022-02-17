package com.example.candiformation.ui.screens.news.articles

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.candiformation.models.CommentResponse
import com.example.candiformation.ui.theme.VeryLightGrey_type1
import com.example.candiformation.ui.theme.VeryLightGrey_type3
import com.example.candiformation.ui.theme.TimeColor
import com.example.candiformation.ui.theme.SemiRed

@Composable
fun CommentView(
    commentList: List<CommentResponse>
) {
    commentList.forEach { item ->
        CommentViewUnit(item)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun CommentViewUnit(
    commentResponse: CommentResponse
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, VeryLightGrey_type1, RoundedCornerShape(8.dp))
            .background(VeryLightGrey_type3)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    Text(
                        text = if (commentResponse.isSecret) "익명" else commentResponse.nickname,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Text(
                        text = "   02.17 18:49",
                        color = TimeColor
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "",
                        tint = SemiRed
                    )
                    Text(" 17 ")
                    Icon(
                        modifier = Modifier.size(22.dp),
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "",
                        tint = Color.LightGray
                    )
                }
            }
            Text(
                text = commentResponse.content
            )
        }
    }
}