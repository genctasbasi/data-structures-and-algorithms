package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 */
class KthLargestElementInAnArray {

    @Test
    fun test() {
        assertEquals(5, findKthLargest(intArrayOf(3, 2, 1, 5, 6, 4), 2))
    }

    @Test
    fun test1() {
        assertEquals(4, findKthLargest(intArrayOf(3, 2, 3, 1, 2, 4, 5, 5, 6), 4))
    }

    fun findKthLargest(nums: IntArray, k: Int): Int {

        val maxHeap = PriorityQueue<Int>()
        nums.forEach {

            if (maxHeap.size < k) {
                maxHeap.offer(it)
            } else {
                if (it > maxHeap.peek()) {
                    maxHeap.poll()
                    maxHeap.offer(it)
                }
            }
        }

        return maxHeap.remove()
    }
}