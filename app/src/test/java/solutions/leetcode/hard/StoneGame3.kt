package solutions.leetcode.hard

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/stone-game-iii/
 */
class StoneGame3 {

    @Test
    fun test() {
        val result = stoneGameIII(intArrayOf(1, 2, 3, -1, -2, -3, 7))
        Assert.assertEquals("Alice", result)
    }

    @Test
    fun test1() {
        val result = stoneGameIII(intArrayOf(-1, -2, -3))
        Assert.assertEquals("Tie", result)
    }

    @Test
    fun test2() {
        val result = stoneGameIII(intArrayOf(1, 2, 3, 6))
        Assert.assertEquals("Tie", result)
    }

    @Test
    fun test3() {
        val result = stoneGameIII(intArrayOf(1, 2, 3, -9))
        Assert.assertEquals("Alice", result)
    }

    @Test
    fun test4() {
        val result = stoneGameIII(intArrayOf(1, 2, 3, 7))
        Assert.assertEquals("Bob", result)
    }

    fun stoneGameIII(stoneValue: IntArray): String {
        val dp = Array(stoneValue.size) { -1 }
        val diff = calc(stoneValue, dp, 0)
        return when {
            diff > 0 -> "Alice"
            diff < 0 -> "Bob"
            else -> "Tie"
        }
    }

    fun calc(stones: IntArray, dp: Array<Int>, currentItemIndex: Int): Int {

        if (currentItemIndex > stones.lastIndex) return 0
        if (dp[currentItemIndex] != -1) return dp[currentItemIndex]

        var maxDiff = Int.MIN_VALUE

        var totalStonesTaken = 0
        (0..2).forEach lit@{ taking ->
            if (currentItemIndex + taking > stones.lastIndex) return@lit

            totalStonesTaken += stones[currentItemIndex + taking]

            val bobTakes =
                calc(stones, dp, currentItemIndex + taking + 1)  // starting from the next slot

            val potentialGain = totalStonesTaken - bobTakes
            maxDiff = Math.max(maxDiff, potentialGain)
        }

        dp[currentItemIndex] = maxDiff
        return maxDiff
    }

}