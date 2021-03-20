package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/exclusive-time-of-functions/
 */
class ExclusiveTimeOfFunctions {

    @Test
    fun test() {
        val result = exclusiveTime(2, listOf("0:start:0", "1:start:2", "1:end:5", "0:end:6"))
        assertEquals(3, result[0])
        assertEquals(4, result[1])
    }

    fun exclusiveTime(n: Int, logs: List<String>): IntArray {

        val result = IntArray(n) { 0 }
        val stack = Stack<String>()

        logs.forEach {
            val values = it.split(':')
            val id = values[0].toInt()
            val operation = values[1]
            val time = values[2].toInt()

            if (operation == "start") {
                stack.push("$id:$time")

            } else {    // "ending"
                val popped = stack.pop().split(':')
                val poppedId = popped[0]
                val poppedTimeElapsed = time.minus(popped[1].toInt()) + 1
                result[poppedId.toInt()] += poppedTimeElapsed

                if (stack.isNotEmpty()) {
                    val peeked = stack.peek().split(':')
                    val peekedId = peeked[0]
                    result[peekedId.toInt()] -= poppedTimeElapsed
                }
            }
        }

        return result
    }

}