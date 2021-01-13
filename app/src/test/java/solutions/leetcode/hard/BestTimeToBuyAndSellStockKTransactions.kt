package solutions.leetcode.hard

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 */
class BestTimeToBuyAndSellStockKTransactions {

    @Test
    fun test() {
        val result = maxProfit(intArrayOf(3, 3, 5, 0, 0, 3, 1, 4))
        Assert.assertEquals(6, result)
    }

    var previousMaxProfit: Int? = null

    fun maxProfit(prices: IntArray): Int {

        if (prices.size < 2) return 0

        val profits = Array(prices.size) { 0 }
        val profitsPrev = Array(prices.size) { 0 }

        for (trans in 1..2) {
            prices.forEachIndexed { index, it ->

                val yesterdaysProfit = if (index == 0) 0 else profits[index - 1]
                val ifSoldToday = if (index == 0) 0 else it  // since we cannot sell on day 1

                val todaysPotentialProfit = if (index == 0) 0 else
                    ifSoldToday + getMaxProfitPreviousTransaction(prices, profitsPrev, index - 1)

                profits[index] = Math.max(yesterdaysProfit, todaysPotentialProfit)
            }

            // TODO this could be improved
            profits.copyInto(profitsPrev)
            previousMaxProfit = null // needs to be cleared for the next 'prevProfits' array
        }

        return profits.last()
    }

    private fun getMaxProfitPreviousTransaction(
        prices: IntArray,
        profitsPrev: Array<Int>,
        index: Int
    ): Int {

        if (previousMaxProfit != null) {
            // just compare previousMaxProfit with last one (index)
            val profitOnTheDay = profitsPrev[index] - prices[index]
            return if (profitOnTheDay > previousMaxProfit!!) {
                previousMaxProfit = profitOnTheDay
                profitOnTheDay
            }else previousMaxProfit!!
        }

        var max = Int.MIN_VALUE
        for (i in 0..index) {
            val profitOnTheDay = profitsPrev[i] - prices[i]
            if (profitOnTheDay > max)
                max = profitOnTheDay
        }

        previousMaxProfit = max
        return max
    }
}