package solutions.leetcode.hard

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/trapping-rain-water/
 */
class TrappingRainWater {

    @Test
    fun test1() {
        val result = trap(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1))
        assertEquals(6, result)
    }

    // this is O(n2) but can get better!
    fun trap(height: IntArray): Int {

        var trappedWater = 0
        var index = 0
        height.forEach {

            val leftBlock = getHighestLeftBlock(height, index)
            val rightBlock = getHighestRightBlock(height, index)

            val lowestBlock = Math.min(leftBlock, rightBlock)

            if (lowestBlock > it)
                trappedWater += (lowestBlock - it)

            index++
        }

        return trappedWater
    }

    fun getHighestRightBlock(height: IntArray, index: Int): Int {

        if (index == height.lastIndex) return 0

        var max = 0
        for (i in (index + 1)..height.lastIndex) {
            if (height[i] > max)
                max = height[i]
        }

        return max
    }

    fun getHighestLeftBlock(height: IntArray, index: Int): Int {

        if (index == 0) return 0

        var max = 0
        for (i in (index - 1) downTo 0) {
            if (height[i] > max)
                max = height[i]
        }

        return max
    }
}