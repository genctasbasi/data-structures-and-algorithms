package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
 */
class MinimumAddToMakeParenthesesValid {

    @Test
    fun test() {
        assertEquals(4, minAddToMakeValid(")))("))
    }

    fun minAddToMakeValid(S: String): Int {

        val stack = Stack<Char>()
        var min = 0

        S.forEach {

            when (it) {
                ')' -> {
                    if (stack.isEmpty()) min++
                    else stack.pop()
                }

                '(' -> stack.add(it)
            }

        }

        return min + stack.size
    }

}