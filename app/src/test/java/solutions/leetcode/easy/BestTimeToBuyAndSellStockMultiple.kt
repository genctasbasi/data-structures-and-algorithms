package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
class BestTimeToBuyAndSellStockMultiple {

    @Test
    fun test() {
        val result = maxProfit(intArrayOf(7, 1, 5, 3, 6, 4))
        Assert.assertEquals(7, result)
    }

    @Test
    fun test1() {
        val result = maxProfit(intArrayOf(7, 6, 4, 3, 1))
        Assert.assertEquals(0, result)
    }

    @Test
    fun test2() {
        val result = maxProfit(intArrayOf(1, 2, 3, 4, 5))
        Assert.assertEquals(4, result)
    }

    @Test
    fun test3() {
        val result = maxProfit(intArrayOf(2, 4, 1))
        Assert.assertEquals(2, result)
    }

    // one pass - O(n)
    fun maxProfit(prices: IntArray): Int {

        var maxProfit = 0
        var valleyStart = prices[0]
        var prev = prices[0]

        prices.forEachIndexed { index, it ->

            if (it < prev) {
                maxProfit += (prev - valleyStart)
                valleyStart = it
            } else if (index == prices.lastIndex) {
                maxProfit += (it - valleyStart)
            }
            prev = it
        }

        return maxProfit
    }
}