package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/two-sum/
 */
class TwoSum {

    @Test
    fun test() {
        val result = twoSum(intArrayOf(2, 7, 11, 15), 9)
        Assert.assertEquals(intArrayOf(0, 1), result)
    }

    fun twoSum(nums: IntArray, target: Int): IntArray {

        val map = mutableMapOf<Int, Int>()

        for (i in nums.indices) {
            val complement = target - nums[i]
            if (map.contains(complement)) {
                return listOf(i, map[complement]!!).toIntArray()
            }

            map[nums[i]] = i

        }

        return emptyArray<Int>().toIntArray()
    }
}