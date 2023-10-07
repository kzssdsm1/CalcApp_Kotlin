package com.example.calcapp_kotlin

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import com.example.calcapp_kotlin.ui.theme.log
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

class AppMainViewModel: ViewModel() {

    private val _displayingNumber: MutableStateFlow<String> = MutableStateFlow("0")
    private val _operationsInProgress: MutableStateFlow<Operator> = MutableStateFlow(Operator.NONE)
    private val _previousOperation: MutableStateFlow<Operator> = MutableStateFlow(Operator.NONE)
    private val _canShowDetailNumber: MutableStateFlow<Boolean> = MutableStateFlow(false)

    private val inputStr = mutableStateOf("")
    private val firstArgument = mutableStateOf<BigDecimal?>(null)
    private val secondArgument = mutableStateOf<BigDecimal?>(null)
    private val previousArgument = mutableStateOf<BigDecimal?>(null)

    val canShowDetailNumber: StateFlow<Boolean> = _canShowDetailNumber.asStateFlow()
    val displayingNumber: StateFlow<String> = _displayingNumber.asStateFlow()
    val operationsInProgress: StateFlow<Operator> = _operationsInProgress.asStateFlow()
    val previousOperation: StateFlow<Operator> = _previousOperation.asStateFlow()

    // 挿入
    fun insertNumString(str: String) {
        inputStr.value += str

        //trimInputString()

        _displayingNumber.value = inputStr.value

        if (operationsInProgress.value != Operator.NONE) {
            secondArgument.value = inputStr.value.toBigDecimalOrNull()
        } else {
            firstArgument.value = inputStr.value.toBigDecimalOrNull()
        }

        println(displayingNumber.value)
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
        _displayingNumber.value = arrangeDisplayNumber(firstArgument.value.toString())
        previousArgument.value = firstArgument.value
        _previousOperation.value = _operationsInProgress.value
    }

    // 減算
    private fun subtraction() {
        inputStr.value = ""
        _operationsInProgress.value = Operator.SUBTRACTION

        val secondArg = secondArgument?.value ?: return

        firstArgument.value = firstArgument.value?.minus(secondArg) ?: BigDecimal.ZERO.minus(secondArg)
        _displayingNumber.value = arrangeDisplayNumber(firstArgument.value.toString())
        previousArgument.value = firstArgument.value
        _previousOperation.value = _operationsInProgress.value
    }

    // 乗算
    private fun multiply() {
        inputStr.value = ""
        _operationsInProgress.value = Operator.MULTIPLY

        val secondArg = secondArgument?.value ?: return

        firstArgument.value = firstArgument.value?.times(secondArg) ?: BigDecimal.ZERO.times(secondArg)
        _displayingNumber.value = arrangeDisplayNumber(firstArgument.value.toString())
        previousArgument.value = firstArgument.value
        _previousOperation.value = _operationsInProgress.value
    }

    // 除算
    private fun divide() {
        inputStr.value = ""
        _operationsInProgress.value = Operator.DIVIDE

        val secondArg = secondArgument?.value ?: return

        firstArgument.value = firstArgument.value?.div(secondArg) ?: BigDecimal.ZERO.div(secondArg)
        _displayingNumber.value = arrangeDisplayNumber(firstArgument.value.toString())
        previousArgument.value = firstArgument.value
        _previousOperation.value = _operationsInProgress.value
    }

    // 符号反転
    private fun changeSign() {
        val str = inputStr.value.takeIf { !it.isEmpty() } ?: run {
            return
        }

        val num = str.toBigDecimalOrNull()
        val timed = num?.times(BigDecimal("-1")) ?: return

        inputStr.value = timed.toString()

        _displayingNumber.value = arrangeDisplayNumber(inputStr.value)

        if (operationsInProgress.value != Operator.NONE) {
            secondArgument.value = timed
        } else {
            firstArgument.value = timed
        }
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

    // 割合化
    private fun proportionation() {
        if (firstArgument == null) return

        val previousOperation = operationsInProgress.value
        val previousSecondArgument: BigDecimal? = secondArgument.value

        if (secondArgument.value != null) {
            val previousFirstArgument = firstArgument.value
            firstArgument.value = secondArgument.value
            secondArgument.value = BigDecimal("0.01")
            multiply()
            secondArgument.value = firstArgument.value
            firstArgument.value = previousFirstArgument
        } else {
            secondArgument.value = BigDecimal("0.01")
            multiply()
            secondArgument.value = previousSecondArgument
        }

        _operationsInProgress.value = previousOperation
    }

    // 小数点挿入
    fun insertDecimalPoint() {
        if (((!inputStr.value.contains(".")) && (inputStr.value.length < 9)) || ((!inputStr.value.contains(".")) && (inputStr.value.contains("-")) && (inputStr.value.length < 10))) return

        if (inputStr.value.isEmpty()) {
            inputStr.value = "0."
        } else {
            inputStr.value += "."
        }
    }

    // 情報のクリア
    private fun clearText() {
        _canShowDetailNumber.value = false
        _displayingNumber.value = "0"
        inputStr.value = ""
        _operationsInProgress.value = Operator.NONE
        _previousOperation.value = Operator.NONE
        firstArgument.value = null
        secondArgument.value = null
        previousArgument.value = null
    }

    fun setOperator(paramOperator: Operator) {
        when (paramOperator) {
            Operator.DETAIL -> return
            Operator.PLUS_MINUS -> changeSign()
            Operator.ALL_CLEAR -> clearText()
            Operator.PERCENT -> proportionation()
            Operator.DIVIDE -> divide()
            Operator.MULTIPLY -> multiply()
            Operator.SUBTRACTION -> subtraction()
            Operator.ADDITION -> addition()
            Operator.EQUAL -> equal()
            Operator.NONE -> return
        }
    }

    private fun arrangeDisplayNumber(displayStr: String): String {
        _canShowDetailNumber.value = false

        val num = BigDecimal(displayStr)

        if (num > BigDecimal("999999999.999997") || (num < BigDecimal("0.000000001") && !displayStr.contains("-")) || (displayStr.contains("-") && num < BigDecimal("0.000000001") && num != BigDecimal("0.0"))) {
            return calcExp(num)
        } else if (displayStr == "null") {
            return "Error"
        } else {
            return roundNumber(displayStr)
        }
    }

    private fun roundNumber(valueStr: String): String {
        val roundNum = BigDecimal(valueStr).setScale(9, RoundingMode.HALF_UP)
        val nf = NumberFormat.getNumberInstance(Locale.getDefault())
        val formattedNum = nf.format(roundNum.toDouble())

        return formattedNum
    }

    private fun calcExp(num: BigDecimal): String {
        var deci = num
        var isMinus = false

        if(deci < BigDecimal.ZERO) {
            isMinus = true
            deci *= BigDecimal("-1")
        }

        val e = BigDecimal.TEN
        val log = deci.log(e)

        var rounded = log?.setScale(0, RoundingMode.DOWN) ?: return "Error"
        var isRoundedMinus = false

        if (rounded < BigDecimal.ZERO) {
            isRoundedMinus = true
            var tempNum = rounded
            tempNum *= BigDecimal("-1")
            rounded = tempNum
        }

        val powed = Math.pow(10.0, rounded.toDouble())

        var divided: BigDecimal?

        if (!isRoundedMinus) {
            divided = deci / BigDecimal(powed)
        } else {
            divided = deci * BigDecimal(powed)
        }

        val rounded2 = divided?.setScale(5, RoundingMode.HALF_UP) ?: return "Error"

        var result = ""

        if (isMinus) {
            result = "-${rounded2}e${rounded}"
        } else {
            if (isRoundedMinus) {
                result = "${rounded2}e-${rounded}"
            } else {
                result = "${rounded2}e${rounded}"
            }
        }

        _canShowDetailNumber.value = true

        return result
    }
}