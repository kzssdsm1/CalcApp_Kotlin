package com.example.calcapp_kotlin

import Component.CircleButton
import Component.NumberButton
import Component.OperatorButton
import Component.RectButton
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
        val displayNumber by viewModel.displayingNumber.collectAsState()

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f),
            textAlign = TextAlign.Right,
            text = displayNumber,
            fontSize = 60.sp,
            softWrap = false,
            maxLines = 1
        ) // Text

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "D",
                onTap = {
                    viewModel.setOperator(Operator.DETAIL)
                } // onTap
            ) // RectButton

            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "AC",
                onTap = {
                    viewModel.setOperator(Operator.ALL_CLEAR)
                } // onTap
            ) // RectButton

            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "%",
                onTap = {
                    viewModel.pro
                } // onTap
            ) // RectButton
        } // Row
    } // Column
}