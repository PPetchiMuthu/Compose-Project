package com.example.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    var state by mutableStateOf(CalculatorData())
        private set

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Calculation -> calculateExpression()
            is CalculatorAction.Clear -> state = CalculatorData()
            is CalculatorAction.Delete -> state = state.copy(string = state.string.dropLast(1))
            is CalculatorAction.Expression -> findExpression(action.expression)
        }
    }

    private fun findExpression(expression: String) {
       state = state.copy(
            string = state.string + expression
        )
        println(state.string)
    }

    private fun calculateExpression() {
        state = state.copy(string = findExpressionValue(state.string))
    }

    private fun findExpressionValue(expression: String): String {
        val value = arrayListOf<Double>()
        val operator = arrayListOf<Char>()
        var temp = ""
        var result = 0.0
        val expressionArray = expression.toCharArray()
        for (i in expressionArray.indices) {
            if (expressionArray[i] != '+' && expressionArray[i] != '-' && expressionArray[i] != '*' && expressionArray[i] != '/') {
                temp += expressionArray[i]
            } else {
                if (expressionArray[i] == '-' || expressionArray[i] == '+') {
                    if (i == 0) {
                        temp += expressionArray[i]
                        continue
                    } else if (expressionArray[i - 1] == '+' || expressionArray[i - 1] == '-' || expressionArray[i - 1] == '*' || expressionArray[i - 1] == '/') {
                        temp += expressionArray[i]
                        continue
                    }
                }
                value.add(temp.toDouble())
                temp = ""
                operator.add(expressionArray[i])
            }
        }
        if (temp != "") value.add(temp.toDouble())
        if (value.size == 1) {
            return value[0].toString()
        }
        if (value.size != operator.size + 1) {
            return "Error"
        }
        for (i in 0 until operator.size) {
            val operatorPosition = getOperatorIndex(operator)
            val valueOne = value[operatorPosition]
            val valueTwo = value[operatorPosition + 1]
            when (operator[operatorPosition]) {
                '+' -> result = (valueOne + valueTwo)
                '-' -> result = (valueOne - valueTwo)
                '/' -> result = (valueOne / valueTwo)
                '*' -> result = (valueOne * valueTwo)
            }
            value.removeAt(operatorPosition)
            value[operatorPosition] = result
            operator.removeAt(operatorPosition)
        }
        return result.toString()
    }

    private fun getOperatorIndex(array: ArrayList<Char>): Int {
        if (array.contains('*')) {
            return array.indexOf('*')
        }
        if (array.contains('/')) {
            return array.indexOf('/')
        }
        return 0
    }
}