package com.example.calcapp_kotlin

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import java.math.BigDecimal

class AppMainViewModel: ViewModel() {
    private val _displayingNumber: MutableStateFlow<String> = MutableStateFlow("")
    private val _operationsInProgress: MutableStateFlow<Operator> = MutableStateFlow(Operator.NONE)

    private val inputStr = mutableStateOf("")
    private val firstArgument = mutableStateOf<BigDecimal?>(null)
    private val secondArgument = mutableStateOf<BigDecimal?>(null)

    val displayingNumber: StateFlow<String> = _displayingNumber.asStateFlow()
    val operationsInProgress: StateFlow<Operator> = _operationsInProgress.asStateFlow()

    fun insertNumString(str: String) {
        inputStr.value += str

        if ((!inputStr.value.contains(".")) && (!inputStr.value.contains("-"))) {
            val taked = inputStr.value.take(9)
            inputStr.value = taked
        } else if ((inputStr.value.contains(".")) && (inputStr.value.contains("-"))) {
            val taked = inputStr.value.take(11)
            inputStr.value = taked
        } else {
            val taked = inputStr.value.take(10)
            inputStr.value = taked
        }

        _displayingNumber.value = inputStr.value

        if (operationsInProgress.value != Operator.NONE) {
            secondArgument.value = inputStr.value.toBigDecimal()
        } else {
            firstArgument.value = inputStr.value.toBigDecimal()
        }
    }

    private fun addition() {
        inputStr.value = ""
        _operationsInProgress.value = Operator.ADDITION

        val secondArg = secondArgument?.let {
            secondArgument
        } ?: run {
            return
        }

        firstArgument.value = firstArgument?.value ?: BigDecimal.ZERO

        firstArgument?.value.plus(secondArg)
    }
}