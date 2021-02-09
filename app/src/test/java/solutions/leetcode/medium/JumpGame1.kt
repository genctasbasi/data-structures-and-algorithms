package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/jump-game/
 */
class JumpGame1 {

    @Test
    fun test() {
        assertEquals(true, canJump(intArrayOf(2, 5, 3, 1, 1, 0, 5, 4)))
    }

    @Test
    fun test2() {
        assertEquals(false, canJump(intArrayOf(3, 2, 1, 0, 4)))
    }

    fun canJump(nums: IntArray): Boolean {

        val canJump = Array(nums.size) { false }
        canJump[canJump.lastIndex] = true

        (nums.lastIndex downTo 0).forEach { elementIndex ->
            val maxJump = nums[elementIndex]
            for (currentJump in 1..maxJump) {
                if (currentJump + elementIndex >= nums.lastIndex
                    || canJump[currentJump + elementIndex]
                ) {
                    canJump[elementIndex] = true
                    break
                }
            }
        }

        return canJump[0]
    }
}