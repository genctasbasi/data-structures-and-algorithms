package solutions.taro75

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.jointaro.com/interviews/questions/best-time-to-buy-and-sell-stock/?src=taro75
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 */
class BestTimeToBuyAndSell {

    @Test
    fun `best time to buy and sell`() {
        val r1 = maxProfit(intArrayOf(7, 1, 5, 3, 6, 4))
        assertEquals(5, r1)
    }

    fun maxProfit(prices: IntArray): Int {
        var max = 0
        var minBuy = prices[0]

        prices.forEach { price ->
            if (price > minBuy) { // went up
                val potentialProfit = price - minBuy
                if (potentialProfit > max)
                    max = potentialProfit

            }

            if (price < minBuy) minBuy = price
        }

        return max
    }
}