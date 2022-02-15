package com.example.candiformation.ui.screens.news.articles

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.candiformation.ui.SharedViewModel
import com.example.candiformation.ui.theme.VeryLightGrey_type1
import com.example.candiformation.utils.Constants.CONTENT_INNER_PADDING

@ExperimentalMaterialApi
@Composable
fun ThumbnailBox(
    navController: NavHostController,
    viewModel: SharedViewModel
) {
    val uriHandler = LocalUriHandler.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                uriHandler.openUri(viewModel.articleLink.value)
            }
            .clip(RoundedCornerShape(4.dp))
            .border(1.dp, VeryLightGrey_type1, RoundedCornerShape(4.dp))
            .background(VeryLightGrey_type1)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(CONTENT_INNER_PADDING)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberImagePainter(viewModel.articleImage.value),
                    contentDescription = "Thumbnail Image",
                    modifier = Modifier.size(128.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = viewModel.articleTitle.value,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = viewModel.articleLink.value,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
