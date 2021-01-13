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

    @Test
    fun test1() {
        val result = maxProfit(intArrayOf(7, 6, 4, 3, 1))
        Assert.assertEquals(0, result)
    }

    // one pass - O(n)
    fun maxProfit(prices: IntArray): Int {

        var maxProfit = 0
        var minBuyPrice = Int.MAX_VALUE
        prices.forEach { it ->

            if (it < minBuyPrice) {
                minBuyPrice = it
            } else {
                val potentialProfit = it - minBuyPrice
                if (potentialProfit > maxProfit) {
                    maxProfit = potentialProfit
                }
            }
        }

        return maxProfit
    }
}