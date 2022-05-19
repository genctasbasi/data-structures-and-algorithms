package solutions.leetcode.hard

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 * O(kn) solution is easy - just loop the array and with each slide, loop inside the window
 * O(nlogn) - using heap
 */
class SlidingWindowMaximum2 {

    @Test
    fun test1() {
        val result = maxSlidingWindow(intArrayOf(1, 3, -1, -3, 5, 3, 6, 7), 3)
        assertEquals(3, result[0])
        assertEquals(3, result[1])
        assertEquals(5, result[2])
        assertEquals(5, result[3])
        assertEquals(6, result[4])
        assertEquals(7, result[5])
    }

    private fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {

        if (nums.isEmpty() || k == 0) return intArrayOf()

        val output = mutableListOf<Int>()
        var startWindow = 0 - k + 1
        var endWindow = 0

        val heap = PriorityQueue<Int>(Collections.reverseOrder())

        while (endWindow <= nums.lastIndex) {

            val endValue = nums[endWindow]
            heap.offer(endValue)

            if (startWindow >= 0) {
                output.add(heap.peek())

                // leaving value
                val leavingValue = nums[startWindow]
                heap.remove(leavingValue)
            }

            startWindow++
            endWindow++
        }

        return output.toIntArray()
    }
}