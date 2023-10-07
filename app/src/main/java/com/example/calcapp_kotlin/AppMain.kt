package com.example.calcapp_kotlin

import Component.RectButton
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AppMain(
    viewModel: AppMainViewModel = viewModel()
) {
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
                    viewModel.showSheet()
                } // onTap
            ) // RectButton

            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "C",
                onTap = {
                    viewModel.setOperator(Operator.ALL_CLEAR)
                } // onTap
            ) // RectButton

            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "%",
                onTap = {
                    viewModel.setOperator(Operator.PERCENT)
                } // onTap
            ) // RectButton

            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "÷",
                onTap = {
                    viewModel.setOperator(Operator.DIVIDE)
                } // onTap
            ) // RectButton
        } // Row

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(1.0f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "7",
                onTap = {
                    viewModel.insertNumString("7")
                } // onTap
            ) // RectButton

            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "8",
                onTap = {
                    viewModel.insertNumString("8")
                } // onTap
            ) // RectButton

            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "9",
                onTap = {
                    viewModel.insertNumString("9")
                } // onTap
            ) // RectButton

            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "×",
                onTap = {
                    viewModel.setOperator(Operator.MULTIPLY)
                } // onTap
            ) // RectButton
        } // Row

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "4",
                onTap = {
                    viewModel.insertNumString("4")
                } // onTap
            ) // RectButton

            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "5",
                onTap = {
                    viewModel.insertNumString("5")
                } // onTap
            ) // RectButton

            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "6",
                onTap = {
                    viewModel.insertNumString("6")
                } // onTap
            ) // RectButton

            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "-",
                onTap = {
                    viewModel.setOperator(Operator.SUBTRACTION)
                } // onTap
            ) // RectButton
        } // Row

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "1",
                onTap = {
                    viewModel.insertNumString("1")
                } // onTap
            ) // RectButton

            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "2",
                onTap = {
                    viewModel.insertNumString("2")
                } // onTap
            ) // RectButton

            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "3",
                onTap = {
                    viewModel.insertNumString("3")
                } // onTap
            ) // RectButton

            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "+",
                onTap = {
                    viewModel.setOperator(Operator.ADDITION)
                } // onTap
            ) // RectButton
        } // Row

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "0",
                onTap = {
                    viewModel.insertNumString("0")
                } // onTap
            ) // RectButton

            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = ".",
                onTap = {
                    viewModel.setOperator(Operator.SETDECIMAL)
                } // onTap
            ) // RectButton

            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "P",
                onTap = {
                    viewModel.setOperator(Operator.PLUS_MINUS)
                } // onTap
            ) // RectButton

            RectButton(
                modifier = Modifier.weight(1.0f),
                buttonText = "=",
                onTap = {
                    viewModel.setOperator(Operator.EQUAL)
                } // onTap
            ) // RectButton
        } // Row
    } // Column

    val isSheetShown by viewModel.isSheetShown.collectAsState()

    SheetDialog(
        isShown = isSheetShown,
        detailNumber = viewModel.firstArgument.value
    ) { viewModel.hideSheet() }
}