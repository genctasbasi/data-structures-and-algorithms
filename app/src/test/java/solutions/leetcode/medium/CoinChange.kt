package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/coin-change/
 */
class CoinChange {

    @Test
    fun test() {
        val result = coinChange(intArrayOf(1, 2, 5), 11)
        assertEquals(3, result)
    }

    @Test
    fun test2() {
        val result = coinChange(intArrayOf(2), 3)
        assertEquals(-1, result)
    }

    fun coinChange(coins: IntArray, amount: Int): Int {

        val dp = Array<Int?>(amount + 1) { null }
        dp[0] = 0   // this is the 'base' case
        coins.forEachIndexed { _, coin ->

            for (index in 0..amount) {
                if (index >= coin) {

                    // option 1: don't take it
                    val option1 = dp[index]

                    // option 2: take it:
                    // +1 because we're 'taking' a new coin, plus whatever coins needed for the remaining amount
                    // and we already know (solved) that remaining part
                    val option2 = if (dp[index - coin] == null) null else (1 + dp[index - coin]!!)

                    // so the result it the min of this options, going back to dp[index
                    dp[index] =
                        if (option1 == null && option2 == null) null else Math.min(
                            option1 ?: Int.MAX_VALUE, option2 ?: Int.MAX_VALUE
                        )
                }
            }
        }

        return dp.last() ?: -1
    }

}