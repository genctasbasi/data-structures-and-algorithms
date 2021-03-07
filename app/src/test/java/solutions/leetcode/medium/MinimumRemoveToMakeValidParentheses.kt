package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
 */
class MinimumRemoveToMakeValidParentheses {

    @Test
    fun test() {
        assertEquals("lee(t(c)o)de", minRemoveToMakeValid("lee(t(c)o)de)"))
    }

    @Test
    fun test1() {
        assertEquals("ab(c)d", minRemoveToMakeValid("a)b(c)d"))
    }

    @Test
    fun test2() {
        assertEquals("", minRemoveToMakeValid("))(("))
    }

    @Test
    fun test3() {
        assertEquals("a(b(c)d)", minRemoveToMakeValid("(a(b(c)d)"))
    }

    @Test
    fun test4() {
        assertEquals("()()", minRemoveToMakeValid("())()((("))
    }

    fun minRemoveToMakeValid(s: String): String {

        if (s.isEmpty()) return s
        if (s.count { it == '(' } == 0 && s.count { it == ')' } == 0) return s

        val validString = StringBuilder(s)
        val removeIndexList = mutableListOf<Int>()
        val stack = Stack<Int>()

        s.forEachIndexed { index, c ->
            if (c == '(') {
                stack.add(index)
            } else if (c == ')') {
                if (stack.isNotEmpty())
                    stack.pop()
                else {
                    removeIndexList.add(index)
                }
            }
        }

        while (stack.isNotEmpty()) {
            removeIndexList.add(stack.pop())
        }

        removeIndexList.sorted().reversed().forEach {
            validString.deleteCharAt(it)
        }

        return validString.toString()
    }
}