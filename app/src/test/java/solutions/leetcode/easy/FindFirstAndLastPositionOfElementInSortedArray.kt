package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
class FindFirstAndLastPositionOfElementInSortedArray {

    @Test
    fun test() {
        val result = searchRange(intArrayOf(5, 7, 7, 8, 8, 10), 8)
        Assert.assertEquals(intArrayOf(3, 4), result)
    }

    fun searchRange(nums: IntArray, target: Int): IntArray {

        if (nums.size == 1)
            return if (nums[0] == target) intArrayOf(0, 0) else intArrayOf(-1, -1)

        var list = mutableListOf<Int>()

        var started = false
        nums.forEachIndexed { index, it ->


            if (it == target) {
                if (started == false) {
                    started = true
                    list.add(index)
                }
            } else {

                if (index != 0 && nums[index - 1] == target) {
                    list.add(index - 1)
                    return list.toIntArray()
                }
            }

        }

        return if (list.size == 1) {
            list.add(nums.lastIndex)
            list.toIntArray()
        } else intArrayOf(-1, -1)
    }
}