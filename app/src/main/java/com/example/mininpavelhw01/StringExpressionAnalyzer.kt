package com.example.mininpavelhw01

import java.util.*
import kotlin.collections.ArrayList

class StringExpressionAnalyzer {

    val calculator: ICalculator = Calculator()
    val priorityQueue: PriorityQueue<IHandlers> = PriorityQueue<IHandlers>(5,  HandlerComparator())
    //val list = mutableListOf<IHandlers>()


    init {
        priorityQueue.add(AddHandler(calculator))
        priorityQueue.add(SubtractHandler(calculator))
        priorityQueue.add(BracketsHandler(calculator))
        priorityQueue.add(MultiplyHandler(calculator))
        priorityQueue.add(DivideHandler(calculator))

        //list.add(AddHandler(calculator))
        //list.add(SubtractHandler(calculator))
        //list.add(BracketsHandler(calculator))
        //list.add(MultiplyHandler(calculator))
        //list.add(DivideHandler(calculator))
        //list.sortWith(HandlerComparator())
    }

    fun calculateString(_str: String): Double {
        var str = _str

        while(!priorityQueue.isEmpty()) {
            val handler = priorityQueue.poll()
            if (handler != null) {
                str = handler.handle(str)
            }
        }
        /*
        for (handler in list) {
            str = handler.handle(str)
        }
         */
        return str.toDouble()
    }
}