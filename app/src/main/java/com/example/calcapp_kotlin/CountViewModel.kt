package com.example.calcapp_kotlin

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel

class CountViewModel: ViewModel() {
    private val _count: MutableStateFlow<Int> = MutableStateFlow(0)

    val count: StateFlow<Int> = _count.asStateFlow()

    fun onCountUpTapped() {
        _count.value++
    }
}