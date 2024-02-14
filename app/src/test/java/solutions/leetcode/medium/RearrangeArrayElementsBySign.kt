package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/rearrange-array-elements-by-sign
 */
class RearrangeArrayElementsBySign {

    @Test
    fun `rearrange array elements by sign`() {
        val result = rearrangeArray(intArrayOf(3, 1, -2, -5, 2, -4))
        val expected = listOf(3, -2, 1, -5, 2, -4)

        assertEquals(expected, result)
    }

    /**
     * O(n)
     */
    private fun rearrangeArray(nums: IntArray): List<Int> {

        val arrayPositive = nums.filter { it >= 0 }
        val arrayNegative = nums.filter { it < 0 }

        println(arrayPositive)
        println(arrayNegative)

        // length is same
        var currentIndex = 0
        var positiveToTake = true
        val result = mutableListOf<Int>()

        while (currentIndex < arrayPositive.size) {
            if (positiveToTake) {
                result.add(arrayPositive[currentIndex])
            } else {
                result.add(arrayNegative[currentIndex])
                currentIndex++
            }

            positiveToTake = !positiveToTake
        }

        println(result)
        return result
    }
}