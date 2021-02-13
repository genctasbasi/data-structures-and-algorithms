package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/coin-change-2/
 */
class CoinChangeCombinations {

    @Test
    fun test() {
        assertEquals(4, change(5, intArrayOf(1, 2, 5)))
    }

    fun change(amount: Int, coins: IntArray): Int {

        val dp = Array<Int?>(amount + 1) { null }
        dp[0] = 1   // the base case

        coins.forEach {coin ->
            for (index in 0..amount) {
                if (index >= coin) {

                    val feed1 = dp[index] ?: 0
                    val feed2 = dp[index - coin] ?: 0

                    dp[index] = feed1 + feed2
                }
            }
        }

        return dp.last() ?: 0
    }
}