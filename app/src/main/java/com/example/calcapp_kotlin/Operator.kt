package com.example.calcapp_kotlin

enum class Operator {
    DETAIL,
    ALL_CLEAR,
    PLUS_MINUS,
    PERCENT,
    DIVIDE,
    MULTIPLY,
    SUBTRACTION,
    ADDITION,
    EQUAL,
    NONE;

    fun buttonText(isAC: Boolean): String {
        return when (this) {
            PERCENT -> "%"
            DIVIDE -> "÷"
            MULTIPLY -> "×"
            SUBTRACTION -> "-"
            ADDITION -> "+"
            EQUAL -> "="
            PLUS_MINUS -> "PM"
            ALL_CLEAR -> if (isAC) "AC" else "C"
            DETAIL -> "SD"
            NONE -> ""
        }
    }
}