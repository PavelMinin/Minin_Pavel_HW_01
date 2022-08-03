package com.example.mininpavelhw01

import java.util.*

class StringExpressionAnalyzer {

    val calculator: ICalculator = Calculator()
    val priorityQueue: PriorityQueue<IHandlers> = PriorityQueue<IHandlers>(5,  HandlerComparator())

    private fun init() {
        priorityQueue.add(AddHandler(calculator))
        priorityQueue.add(SubtractHandler(calculator))
        priorityQueue.add(BracketsHandler(calculator))
        priorityQueue.add(MultiplyHandler(calculator))
        priorityQueue.add(DivideHandler(calculator))
    }

    fun calculateString(_str: String): Double {
        var str = _str

        while (true) {
            init()
            while(!priorityQueue.isEmpty()) {
                val handler = priorityQueue.poll()
                if (handler != null) {
                    str = handler.handle(str)
                }
            }
            try {
                return str.toDouble()
            } catch (e: Exception) { }
        }
    }
}