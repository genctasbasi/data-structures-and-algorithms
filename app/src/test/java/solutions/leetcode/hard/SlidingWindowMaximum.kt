package solutions.leetcode.hard

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 */
class SlidingWindowMaximum {

    @Test
    fun test1() {
        val result = maxSlidingWindowBad(intArrayOf(1, 3, -1, -3, 5, 3, 6, 7), 3)
        assertEquals(3, result[0])
        assertEquals(3, result[1])
        assertEquals(5, result[2])
        assertEquals(5, result[3])
        assertEquals(6, result[4])
        assertEquals(7, result[5])
    }

    fun maxSlidingWindowHeap(nums: IntArray, k: Int): IntArray {

        if (nums.isEmpty() || k == 0) return intArrayOf()
        val q: PriorityQueue<Int> = PriorityQueue(Collections.reverseOrder())

        val output = mutableListOf<Int>()

        // add first window to the queue
        for (i in 0 until k) {
            q.add(nums[i])
        }

        output.add(q.peek())

        for (i in k until nums.size) {
            q.remove(nums[i - k])
            q.add(nums[i])
            output.add(q.peek())
        }

        return output.toIntArray()
    }

    /**
     * This is O(nk) solution
     */
    fun maxSlidingWindowBad(nums: IntArray, k: Int): IntArray {
        if (nums.isEmpty() || k == 0) return intArrayOf()

        val output = mutableListOf<Int>()
        var pL = 0
        var pR = pL + k - 1

        while (pR <= nums.lastIndex) {

            var localMax = Int.MIN_VALUE
            (pL..pR).forEach {
                localMax = Math.max(localMax, nums[it])
            }

            output.add(localMax)
            pL++
            pR++
        }

        return output.toIntArray()
    }
}