package com.example.candiformation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.candiformation.ui.theme.VeryLightGrey_type2
import com.example.candiformation.ui.theme.VeryLightGrey_type3

@Composable
fun CustomTextField(
    placeHolderMsg: String,
    iconRes: ImageVector,
    isVisible: Boolean,
    text: MutableState<String>
) {
    Row(
        modifier = Modifier
            .height(50.dp)
            .width(270.dp)
            .border(1.dp, VeryLightGrey_type2, RoundedCornerShape(8.dp))
            .background(VeryLightGrey_type3),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box() {
            Icon(
                modifier = Modifier
                    .padding(start = 14.dp, end = 14.dp),
                imageVector = iconRes,
                contentDescription = iconRes.toString(),
                tint = VeryLightGrey_type2
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        TextField(
            value = text.value,
            onValueChange = { text.value = it },
            shape = RoundedCornerShape(4.dp),
            placeholder = { Text(placeHolderMsg) },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.LightGray,
                placeholderColor = VeryLightGrey_type2
            ),
            visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
    }
}
