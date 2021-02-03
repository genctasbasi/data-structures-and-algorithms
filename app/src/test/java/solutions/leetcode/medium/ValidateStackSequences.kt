package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/validate-stack-sequences/
 *
 * TODO: Works fine but code needs cleaning (duplications) and complexity can be reduced from
 * O(3n) to proper O(n) I suppose
 */
class ValidateStackSequences {

    @Test
    fun test() {
        assertEquals(
            true,
            validateStackSequences(intArrayOf(1, 2, 3, 4, 5), intArrayOf(4, 5, 3, 2, 1))
        )
    }

    @Test
    fun test2() {
        assertEquals(
            false,
            validateStackSequences(intArrayOf(1, 2, 3, 4, 5), intArrayOf(4, 3, 5, 1, 2))
        )
    }

    @Test
    fun test3() {
        assertEquals(true, validateStackSequences(intArrayOf(1, 0), intArrayOf(1, 0)))
    }

    @Test
    fun test4() {
        assertEquals(
            false,
            validateStackSequences(intArrayOf(4, 0, 1, 2, 3), intArrayOf(4, 2, 3, 0, 1))
        )
    }

    fun validateStackSequences(pushed: IntArray, popped: IntArray): Boolean {

        if (popped.isEmpty()) return true

        val backup: Queue<Int> = LinkedList<Int>()
        pushed.forEach { backup.add(it) }

        val stack = java.util.Stack<Int>()

        var p = -1
        val pushUntil = popped[0]
        do {
            p++
            stack.push(pushed[p])
            backup.remove(pushed[p])

        } while (p < pushed.size && pushed[p] != pushUntil)

        popped.forEach {

            if (stack.isNotEmpty() && it == stack.peek()) {
                stack.pop()
            } else if (backup.contains(it)) {
                // push backup into stack until our number
                var found = false
                while (!found) {
                    val item = backup.remove()
                    stack.push(item)
                    found = item == it

                }

                stack.pop()
            } else {
                return false
            }

        }

        return true
    }
}