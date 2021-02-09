package solutions.leetcode.hard

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/jump-game-ii/
 */
class JumpGame2 {

    @Test
    fun test1() {
        val result = jump(intArrayOf(2, 3, 1, 1, 4))
        assertEquals(2, result)
    }

    @Test
    fun test2() {
        // zeros are dead ends - I should have asked it to the interviewer!
        val result = jump(intArrayOf(2, 3, 0, 1, 4))
        assertEquals(2, result)
    }

    @Test
    fun test3() {
        // zeros are dead ends - I should have asked it to the interviewer!
        val result = jump(intArrayOf(5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0))
        assertEquals(3, result)
    }

    fun jump(nums: IntArray): Int {

        if (nums.size < 2) return 0
        val mem = mutableMapOf<Int, Int>()
        return jumpHelper(nums, 0, mem)
    }

    fun jumpHelper(
        nums: IntArray,
        startIndex: Int,
        mem: MutableMap<Int, Int>
    ): Int {   // mem: index, jump count

        if (mem[startIndex] != null) return mem[startIndex]!!
        if (startIndex >= nums.lastIndex) return 0

        var min = Int.MAX_VALUE
        val firstStep = nums[startIndex]
        if (firstStep == 0) return 100001

        // this is not needed normally, here just for the TLE on LC
        if (startIndex > 0 && nums[startIndex] < nums[startIndex - 1])
            return 100001

        (1..firstStep).forEach {
            val jumpCount = jumpHelper(nums, startIndex + it, mem)
            mem[startIndex + it] = jumpCount
            min = Math.min(min, jumpCount)
        }

        return min + 1  // this the min step from ahead plus 1 to jump there
    }
}