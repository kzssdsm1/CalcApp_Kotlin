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

        trimInputString()

        _displayingNumber.value = inputStr.value

        if (operationsInProgress.value != Operator.NONE) {
            secondArgument.value = inputStr.value.toBigDecimalOrNull()
        } else {
            firstArgument.value = inputStr.value.toBigDecimalOrNull()
        }
    }

    private fun trimInputString() {
        val maxLen = when {
            !inputStr.value.contains(".") && !inputStr.value.contains("-") -> 9
            inputStr.value.contains(".") && inputStr.value.contains("-") -> 11
            else -> 10
        }
        inputStr.value = inputStr.value.take(maxLen)
    }

    private fun addition() {
        inputStr.value = ""
        _operationsInProgress.value = Operator.ADDITION

        val secondArg = secondArgument?.value ?: return

        firstArgument.value = firstArgument.value?.plus(secondArg) ?: BigDecimal.ZERO
    }
}