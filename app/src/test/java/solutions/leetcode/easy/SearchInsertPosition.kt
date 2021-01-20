package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/search-insert-position/
 */
class SearchInsertPosition {

    @Test
    fun test() {
        val result = searchInsert(intArrayOf(1, 3, 5, 6), 5)
        Assert.assertEquals(2, result)
    }

    fun searchInsert(nums: IntArray, target: Int): Int {

        if (nums.isEmpty()) return 0
        if (nums[0] >= target) return 0

        var prev: Int? = null
        nums.forEachIndexed { index, it ->

            if (it >= target)
                return index
        }

        return nums.lastIndex + 1
    }
}