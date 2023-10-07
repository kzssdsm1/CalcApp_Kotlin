package com.example.calcapp_kotlin

import Component.NumberButton
import Component.OperatorButton
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
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
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Operator.values().forEach { it ->
                if(it == Operator.DETAIL || it == Operator.ALL_CLEAR || it == Operator.PERCENT) {
                    OperatorButton(
                        modifier = Modifier.weight(1.0f),
                        operatorStr = it.buttonText((viewModel.operationsInProgress.value == Operator.NONE && viewModel.previousOperation.value == Operator.NONE)),
                        onTap = { viewModel.setOperator(it) }
                    )
                } else {
                    EmptyView()
                }
            } // forEach
        } // Row

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                (0..2).forEach { row ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .weight(1.0f),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        (0..2).forEach { col ->
                            NumberButton(
                                modifier = Modifier.weight(1.0f),
                                numberStr = numbers[row][col],
                                onTap = { viewModel.insertNumString(numbers[row][col]) }
                            )
                        }
                    }
                }
            } // Column

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.0f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                NumberButton(
                    modifier = Modifier.weight(1.0f),
                    numberStr = "0",
                    onTap = { viewModel.insertNumString("0") }
                )

                OperatorButton(
                    modifier = Modifier.weight(1.0f),
                    operatorStr = ".",
                    onTap = { viewModel.insertDecimalPoint() }
                )

                OperatorButton(
                    modifier = Modifier.weight(1.0f),
                    operatorStr = "PM",
                    onTap = { viewModel.setOperator(Operator.PLUS_MINUS) }
                )
            } // Row

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.0f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Operator.values().forEach { it ->
                    if(it == Operator.DETAIL || it == Operator.ALL_CLEAR || it == Operator.PERCENT) {
                        EmptyView()
                    } else {
                        OperatorButton(
                            modifier = Modifier.weight(1.0f),
                            operatorStr = it.buttonText((viewModel.operationsInProgress.value == Operator.NONE && viewModel.previousOperation.value == Operator.NONE)),
                            onTap = { viewModel.setOperator(it) }
                        )
                    }
                } // forEach
            } // Row
        } // Row
    } // Column
}