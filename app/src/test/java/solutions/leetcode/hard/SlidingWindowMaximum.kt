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
        val result = maxSlidingWindow(intArrayOf(1, 3, -1, -3, 5, 3, 6, 7), 3)
        assertEquals(3, result[0])
        assertEquals(3, result[1])
        assertEquals(5, result[2])
        assertEquals(5, result[3])
        assertEquals(6, result[4])
        assertEquals(7, result[5])
    }

    // deque solution
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