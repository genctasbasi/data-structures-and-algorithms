package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * I think I got this question wrong a bit.
 * The expected solution was O(logn) whereas mine is a simple loop with O(n)
 * I had done a similar one (in O(logn)) at AlgoExpert portal
 */
class SearchInRotatedSortedArray {

    @Test
    fun test() {
        val result = search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 0)
        assertEquals(4, result)
    }

    fun search(nums: IntArray, target: Int): Int {

        nums.forEachIndexed { index, it ->

            if (it == target) return index
        }

        return -1
    }
}