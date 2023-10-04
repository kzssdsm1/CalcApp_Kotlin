package com.example.calcapp_kotlin.ui.theme

import java.math.BigDecimal

fun BigDecimal.log(base: BigDecimal): BigDecimal? {
    if (this <= BigDecimal.ZERO || base <= BigDecimal.ZERO || base == BigDecimal.ONE) {
        return null
    } else if (this == BigDecimal.ONE) {
        return BigDecimal.ZERO
    } else if (this < BigDecimal.ONE) {
        return (((BigDecimal.ONE / this).log(base))?.times((BigDecimal("-1"))))
    } else if (base < BigDecimal.ONE) {
        return (log(BigDecimal.ONE / base)?.times((BigDecimal("-1"))))
    } else if (this < base) {
        return (BigDecimal.ONE / (base.log(this)!!))
    }

    var num = this
    var coef = BigDecimal.ONE
    var result = BigDecimal.ZERO
    var next = BigDecimal.ZERO

    while(true) {
        if (num == base) {
            result += coef
            break
        } else if (num == BigDecimal.ONE) {
            break
        } else if (num > base) {
            next += coef
            num /= base

            if (next == result) {
                break
            }

            result = next
        } else {
            coef /= BigDecimal("2")
            num *= num
        }
    }
    return result
}