package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 */
class LongestContinuousSubArray {

    @Test
    fun test() {
        assertEquals(2, longestSubarray(intArrayOf(8, 2, 4, 7), 4))
    }

    @Test
    fun test2() {
        assertEquals(3, longestSubarray(intArrayOf(4, 2, 2, 2, 4, 4, 2, 2), 0))
    }

    @Test
    fun test3() {
        assertEquals(4, longestSubarray(intArrayOf(10, 1, 2, 4, 7, 2), 5))
    }

    @Test
    fun test4() {
        assertEquals(5, longestSubarray(intArrayOf(1, 5, 6, 7, 8, 10, 6, 5, 6), 4))
    }

    /**
     * With heap, O(nlog(n))
     */
    fun longestSubarray(nums: IntArray, limit: Int): Int {

        if (nums.isEmpty()) return 0

        var p0 = 0
        var p1 = p0 + 1

        val minHeap = PriorityQueue<Int>()
        val maxHeap = PriorityQueue<Int>(Collections.reverseOrder())

        var max = 1

        while (p0 <= nums.lastIndex && p1 <= nums.lastIndex) {

            while (p1 <= nums.lastIndex) {

                minHeap.offer(nums[p0])
                minHeap.offer(nums[p1])

                maxHeap.offer(nums[p0])
                maxHeap.offer(nums[p1])

                val diff = Math.abs(maxHeap.peek() - minHeap.peek())

                if (diff <= limit)
                    max = Math.max(max, p1 - p0 + 1)
                else {
                    break
                }

                p1++
            }

            minHeap.clear()
            maxHeap.clear()

            p0++
            p1 = p0 + 1
        }

        return max
    }

    /**
     * The 'not so good' solution: O(n4)
     */
    fun longestSubarrayBad(nums: IntArray, limit: Int): Int {

        if (nums.isEmpty()) return 0

        var p0 = 0
        var p1 = p0 + 1

        var max = 1

        while (p0 <= nums.lastIndex && p1 <= nums.lastIndex) {

            while (p1 <= nums.lastIndex) {

                val subArray = nums.toList().subList(p0, p1 + 1)
                val sorted = subArray.sorted()
                val diff = Math.abs(sorted[0] - sorted[sorted.lastIndex])
                if (diff <= limit)
                    max = Math.max(max, subArray.size)

                p1++
            }

            p0++
            p1 = p0 + 1
        }

        return max
    }
}