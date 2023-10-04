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
    private val _previousOperation: MutableStateFlow<Operator> = MutableStateFlow(Operator.NONE)

    private val inputStr = mutableStateOf("")
    private val firstArgument = mutableStateOf<BigDecimal?>(null)
    private val secondArgument = mutableStateOf<BigDecimal?>(null)
    private val previousArgument = mutableStateOf<BigDecimal?>(null)

    val displayingNumber: StateFlow<String> = _displayingNumber.asStateFlow()
    val operationsInProgress: StateFlow<Operator> = _operationsInProgress.asStateFlow()
    val previousOperation: StateFlow<Operator> = _previousOperation.asStateFlow()

    // 挿入
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

    // 文字数の足切り
    private fun trimInputString() {
        val maxLen = when {
            !inputStr.value.contains(".") && !inputStr.value.contains("-") -> 9
            inputStr.value.contains(".") && inputStr.value.contains("-") -> 11
            else -> 10
        }
        inputStr.value = inputStr.value.take(maxLen)
    }

    // 加算
    private fun addition() {
        inputStr.value = ""
        _operationsInProgress.value = Operator.ADDITION

        val secondArg = secondArgument?.value ?: return


        firstArgument.value = firstArgument.value?.plus(secondArg) ?: BigDecimal.ZERO.plus(secondArg)
        previousArgument.value = firstArgument.value
        _previousOperation.value = _operationsInProgress.value
    }

    // 減算
    private fun subtraction() {
        inputStr.value = ""
        _operationsInProgress.value = Operator.SUBTRACTION

        val secondArg = secondArgument?.value ?: return

        firstArgument.value = firstArgument.value?.minus(secondArg) ?: BigDecimal.ZERO.minus(secondArg)
        previousArgument.value = firstArgument.value
        _previousOperation.value = _operationsInProgress.value
    }

    // 乗算
    private fun multiply() {
        inputStr.value = ""
        _operationsInProgress.value = Operator.MULTIPLY

        val secondArg = secondArgument?.value ?: return

        firstArgument.value = firstArgument.value?.times(secondArg) ?: BigDecimal.ZERO.times(secondArg)
        previousArgument.value = firstArgument.value
        _previousOperation.value = _operationsInProgress.value
    }

    // 除算
    private fun divide() {
        inputStr.value = ""
        _operationsInProgress.value = Operator.DIVIDE

        val secondArg = secondArgument?.value ?: return

        firstArgument.value = firstArgument.value?.div(secondArg) ?: BigDecimal.ZERO.div(secondArg)
        previousArgument.value = firstArgument.value
        _previousOperation.value = _operationsInProgress.value
    }

    // 等号
    private fun equal() {
        if (operationsInProgress.value == Operator.ADDITION) {
            addition()
        } else if (operationsInProgress.value == Operator.SUBTRACTION) {
            subtraction()
        } else if (operationsInProgress.value == Operator.MULTIPLY) {
            multiply()
        } else if (operationsInProgress.value == Operator.DIVIDE) {
            divide()
        } else if ((operationsInProgress.value == Operator.NONE) && (previousOperation.value != Operator.NONE)) {
            val num = previousArgument?.value ?: return

            _operationsInProgress.value = previousOperation.value
            secondArgument.value = num

            equal()
        }

        _operationsInProgress.value = Operator.NONE
        secondArgument.value = null
    }
}