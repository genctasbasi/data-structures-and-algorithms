package solutions.leetcode.hard

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/sliding-window-maximum/
 * O(N) complexity
 */
class SlidingWindowMaximumWithDequeue {

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

    @Test
    fun test2() {
        val result = maxSlidingWindow(intArrayOf(9, 8, 7, 6, 5, 4, 3, 2, 1), 4)
    }

    @Test
    fun test3() {
        val result = maxSlidingWindow(intArrayOf(1, 2, 3, 4, 3, 2, 7, 8, 9), 4)
    }

    @Test
    fun test4() {
        val result = maxSlidingWindow(intArrayOf(1, 3, 1, 2, 0, 5), 3)
    }

    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {

        if (nums.isEmpty() || k == 0) return intArrayOf()
        if (nums.size == k) return intArrayOf(nums.max()!!)

        val dequeue = ArrayDeque<Int>()
        val output = mutableListOf<Int>()

        var pL = 1 - k
        var pR = pL + k - 1

        while (pR <= nums.lastIndex) {

            // remove indexes less than pL
            while (dequeue.isNotEmpty() && dequeue.peek() < pL)
                dequeue.remove()

            // remove smaller numbers in k range as they are useless - it has to be from behind since that's where the small numbers are
            while (dequeue.isNotEmpty() && nums[dequeue.peekLast()] < nums[pR]) {
                dequeue.removeLast()
            }

            dequeue.addLast(pR) // we're adding 'last' because we keep the 'biggest' possible number at the 'peek'

            if (pR >= k - 1) // this means the last 'chance' to pick the 'max' of this window before it moves
            // 'peek' is always the biggest number in the window for two reasons:
            // 1. we added smaller numbers to the 'end' (with addLast)
            // 2. we clean the queue, removing smaller t
                output.add(nums[dequeue.peek()])

            pL++
            pR = pL + k - 1
        }

        return output.toIntArray()
    }
}