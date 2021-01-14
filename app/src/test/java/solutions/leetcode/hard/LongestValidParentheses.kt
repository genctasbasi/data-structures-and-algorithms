package solutions.leetcode.hard

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/longest-valid-parentheses/
 *
 * Solution I have below is O(nlog(n)) but it can be improved to O(n).
 * The logn is coming from the sorting, something we don't really need.
 */
class LongestValidParentheses {

    @Test
    fun test1() {
        val result = longestValidParentheses(")()())")
        assertEquals(4, result)
    }

    class Node(var c: Char, var i: Int)

    fun longestValidParentheses(s: String): Int {

        val stack = Stack<Node>()
        var index = 0
        val list = mutableListOf<Int>()

        s.forEach {
            when (it) {
                ')' -> if (stack.isNotEmpty()) {
                    index++
                    list.add(index)
                    val popped = stack.pop()
                    list.add(popped.i)
                } else {
                    index++
                }
                '(' -> {
                    index++
                    stack.push(Node(it, index))
                }
            }
        }

        return countForm(list)
    }

    /**
     * This fun returns the length of longest consecutive numbers
     */
    private fun countForm(list: MutableList<Int>): Int {
        var currentNumber: Int? = null
        var count = 0
        var max = 0

        list.sorted().forEach {

            if (currentNumber == null) {
                currentNumber = it
                count = 1
            } else {

                if (it == (currentNumber!! + 1)) {
                    count++
                } else {

                    if (count > max)
                        max = count
                    // start over
                    count = 1
                }

                currentNumber = it
            }

            if (count > max)
                max = count
        }
        return max
    }

}