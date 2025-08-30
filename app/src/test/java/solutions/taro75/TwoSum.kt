package solutions.taro75

import org.junit.Assert
import org.junit.Test

/**
 * https://www.jointaro.com/interviews/questions/two-sum/?src=taro75
 * https://leetcode.com/problems/two-sum/
 */
class TwoSum {

    @Test
    fun `two sum`() {

        val r1 = twoSumHashSet(intArrayOf(2, 7, 11, 15), 9)
        val r2 = twoSumHashSet(intArrayOf(3, 2, 4), 6)
        val r3 = twoSumHashSet(intArrayOf(3, 3), 6)

        Assert.assertArrayEquals(intArrayOf(0, 1), r1)
        Assert.assertArrayEquals(intArrayOf(1, 2), r2)
        Assert.assertArrayEquals(intArrayOf(0, 1), r3)
    }

    /**
     * O(n)
     * O(n) space
     */
    fun twoSumHashSet(nums: IntArray, target: Int): IntArray {

        val map = hashMapOf<Int, Int>() // value, index

        nums.forEachIndexed { index, num ->
            map[num] = index
        }

        nums.forEachIndexed { index, num ->
            val needed = target - num

            if (map.contains(needed) && index != map[needed]) {
                return intArrayOf(index, map[needed]!!)
            }
        }

        return intArrayOf()
    }

    /**
     * O(n2)
     */
    fun twoSum(nums: IntArray, target: Int): IntArray {

        for (i in 0..nums.size - 1) {
            for (j in i + 1..nums.size - 1) {

                if (nums[i] + nums[j] == target)
                    return intArrayOf(nums[i], nums[j])
            }
        }

        return intArrayOf()
    }
}