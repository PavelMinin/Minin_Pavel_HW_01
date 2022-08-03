package com.example.mininpavelhw01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

fun main() {
    val ALLOWED_SYMBOLS = "([\\d-+*/^|() ]*[.,]*)*"

    val calc = StringExpressionAnalyzer()
    println("Input math expression:")

    var str: String?

    do {
        str = readLine()
        val isCorrect = checkExpression(str, ALLOWED_SYMBOLS)
        if(!isCorrect) {
            println("Incorrect expression. Try again:")
        }
    } while (!isCorrect)

    val strForCalc = str as String
    val answer = calc.calculateString(str)
    println("Result: " + answer)
}

/**
 * Проверка входного выражения на корректность.
 */
fun checkExpression(_str: String?, regexp: String): Boolean {
    if(_str == null) return false
    val str = _str.replace(" +", "")
    return str.matches(regexp.toRegex())
}