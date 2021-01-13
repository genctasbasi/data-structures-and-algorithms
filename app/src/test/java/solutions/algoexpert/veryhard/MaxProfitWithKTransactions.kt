package solutions.algoexpert.veryhard

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Max%20Profit%20With%20K%20Transactions
 */
class MaxProfitWithKTransactions {

    @Test
    fun test() {
        val result = maxProfitWithKTransactions(listOf(5, 11, 3, 50, 60, 90), 2)
        assertEquals(93, result)
    }

    @Test
    fun test2() {
        val result = maxProfitWithKTransactions(listOf(5, 11, 3, 50, 40, 90), 2)
        assertEquals(97, result)
    }

    var previousMaxProfit: Int? = null

    fun maxProfitWithKTransactions(prices: List<Int>, k: Int): Int {

        if (prices.size < 2) return 0

        val profits = Array(prices.size) { 0 }
        val profitsPrev = Array(prices.size) { 0 }

        for (trans in 1..k) {
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
        prices: List<Int>,
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
        return max
    }
}