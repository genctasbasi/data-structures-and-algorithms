package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test
import java.util.*

/**
 * https://www.algoexpert.io/questions/Balanced%20Brackets
 */

class BalancedBrackets {

    @Test
    fun test() {
        val result = balancedBrackets("([])(){}(())()()")
        Assert.assertEquals(true, result)
    }

    @Test
    fun test2() {
        val result = balancedBrackets("([])(){}((())()()")
        Assert.assertEquals(true, result)
    }

    enum class BracketType {
        TYPE_1, TYPE_2, TYPE_3, NOT_BRACKET
    }

    enum class BracketDirection {
        OPENING, CLOSING, NOT_BRACKET
    }

    fun balancedBrackets(str: String): Boolean {

        val stack = Stack<Char>()

        str.toCharArray().forEach {

            when (getBracketDirection(it)) {
                BracketDirection.OPENING -> {
                    stack.add(it)
                }

                BracketDirection.CLOSING -> {

                    if (stack.isEmpty()) return false
                    val lastChar = stack.peek()

                    if (getBracketType(lastChar) != getBracketType(it)) {
                        return false
                    } else {
                        stack.pop()
                    }
                }
                else -> {
                }  // ignore
            }
        }

        return stack.isEmpty()
    }

    private fun getBracketDirection(bracket: Char): BracketDirection {
        return when (bracket) {
            '(', '{', '[' -> BracketDirection.OPENING
            ')', '}', ']' -> BracketDirection.CLOSING
            else -> BracketDirection.NOT_BRACKET
        }
    }

    private fun getBracketType(bracket: Char): BracketType {
        return when (bracket) {
            '(', ')' -> BracketType.TYPE_1
            '{', '}' -> BracketType.TYPE_2
            '[', ']' -> BracketType.TYPE_3
            else -> BracketType.NOT_BRACKET
        }
    }
}