package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
class BestTimeToBuyAndSellStock {

    @Test
    fun test() {
        val result = maxProfit(intArrayOf(7, 1, 5, 3, 6, 4))
        Assert.assertEquals(5, result)
    }

    fun maxProfit(prices: IntArray): Int {
        return 0
    }
}