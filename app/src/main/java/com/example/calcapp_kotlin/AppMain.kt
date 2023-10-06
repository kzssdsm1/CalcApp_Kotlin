package com.example.calcapp_kotlin

import Component.NumberButton
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
    val numbers = arrayOf(
        arrayOf("7", "8", "9"),
        arrayOf("4", "5", "6"),
        arrayOf("1", "2", "3")
        )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val displayNumber by viewModel.displayingNumber.collectAsStateWithLifecycle()

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f),
            textAlign = TextAlign.Right,
            text = displayNumber,
            fontSize = 60.sp,
            softWrap = false,
            maxLines = 1
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1.0f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                (0..2).forEach { row ->
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1.0f),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        (0..2).forEach { col ->
                            NumberButton(
                                modifier = Modifier.weight(1.0f),
                                numberStr = numbers[row][col],
                                onTap = viewModel.insertNumString(numbers[row][col]))
                        }
                    }
                }
            } // Column
        } // Row
    } // Column
}