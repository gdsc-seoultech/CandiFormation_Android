package com.solution_challenge.candiformation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomDialog_type_1(
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    title: String,
    isConfirmed: () -> Unit,
    isDismissed: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            shape = RoundedCornerShape(0.dp),
            backgroundColor = Color.Black,
            onDismissRequest = {
                setShowDialog(false)
            },
            title = {
                Text(
                    modifier = Modifier.padding(bottom = 12.dp),
                    text = title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            dismissButton = {
                Button(
                    modifier = Modifier.padding(bottom = 12.dp, end = 4.dp),
                    shape = RoundedCornerShape(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White
                    ),
                    onClick = {
                        isDismissed()
                        setShowDialog(false)
                    },
                    elevation = ButtonDefaults.elevation(4.dp)
                ) {
                    Text("아니오")
                }
            },
            confirmButton = {
                Button(
                    modifier = Modifier.padding(bottom = 12.dp, end = 8.dp),
                    shape = RoundedCornerShape(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White
                    ),
                    onClick = {
                        isConfirmed()
                        setShowDialog(false)
                    },
                    elevation = ButtonDefaults.elevation(4.dp)
                ) {
                    Text("예")
                }
            }
        )
    }
}
