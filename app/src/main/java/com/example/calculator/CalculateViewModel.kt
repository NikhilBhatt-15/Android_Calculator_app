package com.example.calculator
import java.util.ArrayDeque
import kotlin.math.*

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class CalculateViewModel {
    var answer by mutableStateOf("")
        private set





    fun evaluateExpression(expression: String) {
        if (answer == "Invalid op"||answer == "Division by zero" || answer == "Unknown error" || answer == "Extra tokens" || answer == "Invalid char" || answer == "Invalid operator") {
            answer = ""
            return
        }

        val operators = "+-*/"
        val precedence = mapOf('*' to 2, '/' to 2, '+' to 1, '-' to 1) // Negation has lower precedence than other operators

        fun infixToPostfix(expression: String): String {
            val stack = mutableListOf<Char>()
            val postfix = mutableListOf<String>()

            var currentToken = ""
            for (char in expression) {
                if (char.isDigit() || char == '.') {
                    currentToken += char
                } else if (char in operators) {
                    if (currentToken.isNotEmpty()) {
                        postfix.add(currentToken)
                        currentToken = ""
                    }
                    while (stack.isNotEmpty()) {
                        val lastChar = stack.removeAt(stack.lastIndex) // Pop the last element from the stack
                        if ((precedence[lastChar] ?: 0) >= (precedence[char] ?: 0)) {
                            postfix.add(lastChar.toString()) // Convert to string before adding
                        }
                    }
                    stack.add(char)
                }
            }

            if (currentToken.isNotEmpty()) {
                postfix.add(currentToken)
            }

            while (stack.isNotEmpty()) {
                postfix.add(stack.removeAt(stack.lastIndex).toString())
            }

            return postfix.joinToString(" ")
        }

        fun evaluatePostfix(postfix: String): Double {
            val stack = mutableListOf<Double>()
            for (token in postfix.split(" ")) {
                if (token in operators) {
                    val operand2 = stack.removeAt(stack.lastIndex)
                    val operand1 = if (stack.isNotEmpty()) stack.removeAt(stack.lastIndex) else 0.0

                    val result = when (token) {
                        "+" -> operand1 + operand2
                        "-" -> operand1 - operand2
                        "*" -> operand1 * operand2
                        "/" -> {
                            if (operand2 == 0.0) throw ArithmeticException("Division by zero")
                            operand1 / operand2
                        }
                        else -> throw IllegalArgumentException("Invalid operator")
                    }
                    stack.add(result)
                } else {
                    try {
                        val value = token.toDouble()
                        stack.add(value)
                    } catch (e: NumberFormatException) {
                        throw IllegalArgumentException("Invalid operand")
                    }
                }
            }

            if (stack.size != 1) throw IllegalArgumentException("Extra tokens")

            return stack.first()
        }

        try {
            val postfix = infixToPostfix(expression)
            val result = evaluatePostfix(postfix)

            // Check if the result is an integer (without a decimal)
            answer = if (result % 1 == 0.0) {
                result.toInt().toString()
            } else {
                String.format("%.2f", result)
            }
        } catch (e: Exception) {
            answer = e.message?.take(10) ?: "Unknown error" // Return first 10 chars of the error message
        }
    }






    fun inputText(input:String){
        if(answer == "Invalid op"||answer == "Division by zero" || answer =="Unknown error" || answer =="Extra tokens" || answer == "Invalid char" || answer == "Invalid operator"){
            answer=""
        }
        answer+=input
    }

    fun del(){
        if(answer == "Invalid op"||answer == "Division by zero" || answer =="Unknown error" || answer =="Extra tokens" || answer == "Invalid char" || answer == "Invalid operator"){
            answer=""
        }
        answer = answer.dropLast(1)
    }

    fun ac(){
        answer = ""
    }




}