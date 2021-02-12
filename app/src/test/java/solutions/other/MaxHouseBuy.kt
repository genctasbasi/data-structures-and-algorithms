package solutions.other

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * Problem
 * There are N houses for sale. The i-th house costs Ai dollars to buy. You have a budget of B dollars to spend.
 * What is the maximum number of houses you can buy?
 */
class MaxHouseBuy {

    @Test
    fun test() {
        assertEquals(2, maxBuy(100, intArrayOf(20, 90, 40, 90)))
        assertEquals(3, maxBuy(50, intArrayOf(30, 30, 10, 10)))
        assertEquals(0, maxBuy(300, intArrayOf(999, 999, 999)))
        assertEquals(5, maxBuy(100, intArrayOf(90, 10, 10, 20, 60, 30, 30)))
        assertEquals(
            25,
            maxBuy(
                100,
                intArrayOf(
                    99,
                    99,
                    99,
                    99,
                    99,
                    99,
                    99,
                    99,
                    99,
                    99,
                    99,
                    99,
                    99,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    1,
                    76
                )
            )
        )
    }

    fun maxBuy(budget: Int, prices: IntArray): Int {
        if (budget == 0 || prices.isEmpty()) return 0
        val result = maxBuyR(budget, prices, 0, hashMapOf())
        return result
    }

    // O(n)
    fun maxBuyR(budget: Int, prices: IntArray, startIndex: Int, mem: HashMap<String, Int>): Int {

        if (budget == 0 || startIndex > prices.lastIndex) return 0

        val key = "$budget-$startIndex"
        if (mem[key] != null) return mem[key]!!

        val canBuyThis = if (budget >= prices[startIndex]) 1 else 0
        val option1 = canBuyThis + maxBuyR(budget - prices[startIndex], prices, startIndex + 1, mem)
        val option2 = maxBuyR(budget, prices, startIndex + 1, mem)

        val max = Math.max(option1, option2)
        mem[key] = max
        return max
    }

    /**
     * O(nlogn)
     */
    fun `maxBuy O(nlog(n))`(budget: Int, prices: IntArray): Int {
        if (budget == 0 || prices.isEmpty()) return 0

        val sorted = prices.sorted()

        var left = budget
        var bought = 0
        sorted.forEach {
            if (it <= left) {
                left -= it
                bought++
            }
        }

        return bought
    }

}