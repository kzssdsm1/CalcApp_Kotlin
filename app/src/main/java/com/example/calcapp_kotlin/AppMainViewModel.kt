package com.example.calcapp_kotlin

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class AppMainViewModel: ViewModel() {
    private val _displayingNumber: MutableStateFlow<String> = MutableStateFlow("")

    val displayingNumber: StateFlow<String> = _displayingNumber.asStateFlow()
    val inputStr = mutableStateOf("")

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
    }
}