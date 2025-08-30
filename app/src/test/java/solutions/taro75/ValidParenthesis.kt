package solutions.taro75

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.Stack

/**
 * https://www.jointaro.com/interviews/questions/valid-parentheses/?src=taro75
 * https://leetcode.com/problems/valid-parentheses/
 */
class ValidParenthesis {

    @Test
    fun `valid parenthesis`() {
        assertEquals(true, isValid("()[]{}"))
        assertEquals(true, isValid("([{}])"))
        assertEquals(false, isValid("([{}]))"))
        assertEquals(false, isValid("{([{}]))"))
        assertEquals(false, isValid(")("))
    }

    fun isValid(s: String): Boolean {

        if (s.length.mod(2) == 1) return false

        val stack = ArrayDeque<Char>()

        s.forEach { char ->
            when (char) {
                '{' -> stack.addLast(char)
                '}' -> {
                    val removed = stack.removeLastOrNull()
                    if (removed != '{') return false
                }

                '[' -> stack.addLast(char)
                ']' -> {
                    val removed = stack.removeLastOrNull()
                    if (removed != '[') return false
                }

                '(' -> stack.addLast(char)
                ')' -> {
                    val removed = stack.removeLastOrNull()
                    if (removed != '(') return false
                }
            }
        }

        return stack.isEmpty()
    }
}