package com.example.mininpavelhw01

/**
 * Калькулятор, реализующий математические функции.
 * Реализованы дополнительно возведение в степень, модуль и квадратный корень.
 */

interface ICalculator {
    fun add(a: Double, b: Double): Double
    fun subtract(a: Double, b: Double): Double
    fun multiply(a: Double, b: Double): Double
    fun divide(a: Double, b: Double): Double

    fun pow(a: Double, b: Double): Double
    fun abs(x: Double): Double
    fun sqrt(x: Double): Double
}

class Calculator: ICalculator {
    override fun add(a: Double, b: Double): Double {
        return a + b
    }

    override fun subtract(a: Double, b: Double): Double {
        return a - b
    }

    override fun multiply(a: Double, b: Double): Double {
        return a * b
    }

    override fun divide(a: Double, b: Double): Double {
        return a / b
    }

    override fun pow(a: Double, b: Double): Double {
        return Math.pow(a, b)
    }

    override fun abs(x: Double): Double {
        return Math.abs(x)
    }

    override fun sqrt(x: Double): Double {
        return Math.sqrt(x)
    }
}