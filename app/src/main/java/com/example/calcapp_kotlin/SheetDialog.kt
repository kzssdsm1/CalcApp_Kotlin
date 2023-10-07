package com.example.calcapp_kotlin

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import java.math.BigDecimal

@Composable
fun SheetDialog(
    isShown: Boolean,
    detailNumber: BigDecimal,
    onHide: () -> Unit
) {
    if (isShown) {
        Dialog(onDismissRequest = onHide) {

        }
    }
}