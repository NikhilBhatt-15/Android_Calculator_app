package com.example.calculator
import java.util.ArrayDeque
fun evaluateExpression(expression: String): String {
    """
  Evaluates a simple arithmetic expression containing numbers, addition (+), subtraction (-), 
  multiplication (*), and division (/) operators, without spaces between them.

  Args:
      expression: A string representing the arithmetic expression without spaces.

  Returns:
      A string containing the evaluated result (formatted with 2 decimal places) or a concise error message (less than 10 words).
  """

    val operators = "+-*/"
    val precedence = mapOf('*' to 2, '/' to 2, '+' to 1, '-' to 1)

    fun infixToPostfix(expression: String): String {
        val stack = ArrayDeque<Char>()
        val postfix = StringBuilder()
        for (char in expression) {
            when (char) {
                in operators -> {
                    while (stack.isNotEmpty() && stack.peek() in operators &&
                        precedence[stack.peek()]!! >= precedence[char]!!) {
                        postfix.append(stack.pop())
                    }
                    stack.push(char)
                }
                else -> postfix.append(char)
            }
        }
        while (stack.isNotEmpty()) {
            postfix.append(stack.pop())
        }
        return postfix.toString()
    }

    fun evaluatePostfix(postfix: String): String {
        val stack = ArrayDeque<Double>()
        for (char in postfix) {
            if (char in operators) {
                val operand2 = stack.pop()
                val operand1 = stack.pop()
                val result = when (char) {
                    '+' -> operand1 + operand2
                    '-' -> operand1 - operand2
                    '*' -> operand1 * operand2
                    '/' -> {
                        if (operand2 == 0.0) return "Division by zero"
                        operand1 / operand2
                    }
                    else -> return "Invalid operator"
                }
                stack.push(result)
            } else {
                try {
                    val value = char.toString().toDouble()
                    stack.push(value)
                } catch (e: NumberFormatException) {
                    return "Invalid char"
                }
            }
        }

        if (stack.size != 1) return "Extra tokens"

        val finalResult = stack.pop()
        return String.format("%.2f", finalResult)
    }

    try {
        val postfix = infixToPostfix(expression)
        val result = evaluatePostfix(postfix)
        return result
    } catch (e: Exception) {
        return e.message?.take(10) ?: "Unknown error" // Return first 10 chars of error message
    }
}

