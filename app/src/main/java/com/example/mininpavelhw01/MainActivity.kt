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
    val calc = StringExpressionAnalyzer()
    val strForCalc = "4 + 1 / 3 - 10"
    println(strForCalc)
    val answer = calc.calculateString(strForCalc)
    println(answer)
}