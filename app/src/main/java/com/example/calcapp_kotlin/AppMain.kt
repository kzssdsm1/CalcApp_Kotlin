package com.example.calcapp_kotlin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AppMain(
    viewModel: AppMainViewModel = viewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val displayNumber by viewModel.displayingNumber.collectAsStateWithLifecycle()

        Text(
            modifier = Modifier.fillMaxWidth()
                .weight(1.0f),
            textAlign = TextAlign.Right,
            text = displayNumber,
            fontSize = 60.sp,
            softWrap = false,
            maxLines = 1
        )
    }
}