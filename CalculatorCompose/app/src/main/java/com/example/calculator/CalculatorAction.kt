package com.example.calculator

sealed class CalculatorAction {
    data class Expression(val expression: String) : CalculatorAction()
    object Clear : CalculatorAction()
    object Delete : CalculatorAction()
    object Calculation : CalculatorAction()
}

data class CalculatorData(val string: String = "")