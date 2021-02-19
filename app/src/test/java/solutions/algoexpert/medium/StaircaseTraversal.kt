package solutions.algoexpert.medium

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://www.algoexpert.io/questions/Staircase%20Traversal
 */
class StaircaseTraversal {

    @Test
    fun testCase() {
        assertEquals(5, staircaseTraversal(4, 2))
    }

    /**
     * this should be O(n)
     */
    private fun staircaseTraversal(height: Int, maxSteps: Int): Int {

        val deque: Deque<Int> = LinkedList()
        val dp = Array(height) { 0 }
        dp[0] = 1
        deque.add(1)

        var prevSum = 1

        for (i in 1 until height) {

            // var newAdd = sum

            var thisCell = prevSum
            if (i < maxSteps) thisCell += 1

            deque.addFirst(thisCell)
            prevSum += thisCell

            if (deque.size > maxSteps) {
                val removed = deque.removeLast()
                prevSum -= removed
            }

            dp[i] = thisCell
        }

        return dp.last()
    }


    fun `staircaseTraversal O(kn)`(height: Int, maxSteps: Int): Int {

        val dp = Array(height) { 0 }
        dp[0] = 1

        for (i in 1 until height) {
            var sum = 0
            val bottomLimit = Math.max(0, i - maxSteps)
            (bottomLimit until i).forEach { index ->
                sum += dp[index]
            }

            dp[i] = sum
            if (i < maxSteps) dp[i] += 1
        }

        return dp.last()
    }
}