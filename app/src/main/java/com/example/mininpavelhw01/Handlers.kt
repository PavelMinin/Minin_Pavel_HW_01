package com.example.mininpavelhw01

import java.util.regex.Matcher
import java.util.regex.Pattern

val NUMBER_PATTERN: String
    get() = "(-?\\d+(\\.\\d+){0,1})"

val ADD_PATTERN: String
    get() = " *\\+ *"

val OP_BR_PATTERN: String
    get() = "\\("

val CL_BR_PATTERN: String
    get() = "\\)"

val DIVIDE_PATTERN: String
    get() = " *\\/ *"

val MULT_PATTERN: String
    get() = " *\\* *"

val SUBTRACT_PATTERN: String
    get() = " *\\- *"

interface IHandlers {

    val priority: Int

    fun handle (_str: String) : String
}

abstract class OperatorHandlers(_regexp: String): IHandlers {

    val regexp = _regexp
    
    override fun handle (_str: String) : String {
        var str = _str
        val pattern: Pattern = Pattern.compile(regexp)
        do{
            val matcher: Matcher = pattern.matcher(str)
            val isMatch = matcher.find()
            if(isMatch) {
                val result = calculate(matcher)
                do {
                    str = str.replace(matcher.group(), result.toString())
                } while (str.contains(matcher.group()))
            }
        } while(isMatch)
        return str
    }

    abstract fun calculate(matcher: Matcher): Double
}

class AddHandler(_calculator: ICalculator) : IHandlers,
    OperatorHandlers(NUMBER_PATTERN + ADD_PATTERN + NUMBER_PATTERN) {

    private val calculator = _calculator

    override val priority: Int
        get() {
            return 1
        }

    override fun calculate(matcher: Matcher): Double {
        val operand1 = matcher.group(1)
        val operand2 = matcher.group(3)
        if (operand1 != null && operand2 != null) {
            return calculator.add(operand1.toDouble(), operand2.toDouble())
        } else {
            return Double.NaN
        }
    }
}

class BracketsHandler(_calculator: ICalculator) : IHandlers {

    private val calculator = _calculator

    override val priority: Int
        get() {
            return 5
        }


    override fun handle(_str: String): String {
        val pattern: Pattern = Pattern.compile("(.*)" + "(" + OP_BR_PATTERN + ".+" + CL_BR_PATTERN + ")" + "(.*)")
        var str = _str
        do {
            val matcher: Matcher = pattern.matcher(str)
            val isMatch = matcher.find()
            if (isMatch) {
                val stringAnalyzer = StringExpressionAnalyzer()
                val withoutBrackets = matcher.group(2).substring(1, matcher.group(2).length - 1)
                var temp = stringAnalyzer.calculateString(withoutBrackets)

                str = str.replace(matcher.group(2),temp.toString())
            }
        } while (isMatch)
        return str
    }
}

class DivideHandler(_calculator: ICalculator) : IHandlers,
    OperatorHandlers(NUMBER_PATTERN + DIVIDE_PATTERN + NUMBER_PATTERN) {

    private val calculator = _calculator

    override val priority: Int
        get() {
            return 2
        }

    override fun calculate(matcher: Matcher): Double {
        val operand1 = matcher.group(1)
        val operand2 = matcher.group(3)
        if (operand1 != null && operand2 != null) {
            return calculator.divide(operand1.toDouble(), operand2.toDouble())
        } else {
            return Double.NaN
        }
    }
}

class MultiplyHandler(_calculator: ICalculator) : IHandlers,
    OperatorHandlers(NUMBER_PATTERN + MULT_PATTERN + NUMBER_PATTERN) {

    private val calculator = _calculator

    override val priority: Int
        get() {
            return 2
        }

    override fun calculate(matcher: Matcher): Double {
        val operand1 = matcher.group(1)
        val operand2 = matcher.group(3)
        if (operand1 != null && operand2 != null) {
            return calculator.multiply(operand1.toDouble(), operand2.toDouble())
        } else {
            return Double.NaN
        }
    }
}

class SubtractHandler(_calculator: ICalculator) : IHandlers,
    OperatorHandlers (NUMBER_PATTERN + SUBTRACT_PATTERN + NUMBER_PATTERN) {

    private val calculator = _calculator

    override val priority: Int
        get() {
            return 1
        }

    override fun calculate(matcher: Matcher): Double {
        val operand1 = matcher.group(1)
        val operand2 = matcher.group(3)
        if (operand1 != null && operand2 != null) {
            return calculator.subtract(operand1.toDouble(), operand2.toDouble())
        } else {
            return Double.NaN
        }
    }
}

class HandlerComparator: Comparator<IHandlers> {
    override fun compare(p0: IHandlers?, p1: IHandlers?): Int {
        if (p0 == null && p1 == null) {
            return 0
        } else if (p0 == null) {
            return -1
        } else if (p1 == null) {
            return 1
        }
        return p1.priority - p0.priority
    }
}