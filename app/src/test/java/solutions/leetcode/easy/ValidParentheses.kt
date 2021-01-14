package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/valid-parentheses/
 */
class ValidParentheses {

    @Test
    fun test() {
        val result = isValid("()[[]{}")
        Assert.assertEquals(true, result)
    }

    fun isValid(s: String): Boolean {

        val stack = Stack<Char>()
        s.forEach {

            when (it) {
                '(' -> stack.push(it)
                ')' -> if (stack.isEmpty() || stack.peek() == '(') stack.pop() else return false
                '{' -> stack.push(it)
                '}' -> if (stack.isEmpty() ||stack.peek() == '{') stack.pop() else return false
                '[' -> stack.push(it)
                ']' -> if (stack.isEmpty() ||stack.peek() == '[') stack.pop() else return false
            }
        }

        return stack.isEmpty()
    }
}