package com.example.calcapp_kotlin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.flow.StateFlow
import java.math.BigDecimal

@Composable
fun SheetDialog(
    isShown: Boolean,
    detailNumber: BigDecimal?,
    onHide: () -> Unit
) {
    if (isShown) {
        Dialog(onDismissRequest = onHide) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .background(color = Color(0xFFFFFFFF)),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.0f),
                    textAlign = TextAlign.Right,
                    text = detailNumber.toString(),
                    fontSize = 40.sp,
                    softWrap = false
                ) // Text
            } // Column
        } // DiaLog
    }
}
