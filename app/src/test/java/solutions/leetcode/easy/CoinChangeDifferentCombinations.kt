package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/min-cost-climbing-stairs/
 */
class CoinChangeDifferentCombinations {

    @Test
    fun test() {
        val result = minCostClimbingStairs(intArrayOf(10, 15, 20))
        Assert.assertEquals(15, result)
    }

    @Test
    fun test2() {
        val result = minCostClimbingStairs(intArrayOf(1, 100, 1, 1, 1, 100, 1, 1, 100, 1))
        Assert.assertEquals(6, result)
    }

    @Test
    fun test3() {
        val result = minCostClimbingStairs(intArrayOf(0, 2, 2, 1))
        Assert.assertEquals(2, result)
    }

    @Test
    fun test4() {
        val result = minCostClimbingStairs(intArrayOf(2, 2, 1, 0))
        Assert.assertEquals(2, result)
    }

    /**
     * DP solution
     */
    fun minCostClimbingStairs(cost: IntArray): Int {
        if (cost.size < 2) return 0

        var costPrevPrev = cost[0]
        var costPrev = cost[1]
        for (i in 2..cost.lastIndex) {
            val option1 = costPrevPrev
            val option2 = costPrev

            costPrevPrev = costPrev
            costPrev = Math.min(option1, option2) + cost[i]
        }

        return Math.min(costPrev, costPrevPrev)
    }

    /**
     * recursion
     */
    fun minCostClimbingStairsR(cost: IntArray): Int {

        val option1Cost = getCost(cost, 0, mutableMapOf())
        val option2Cost = getCost(cost, 1, mutableMapOf())

        return Math.min(option1Cost, option2Cost)
    }

    fun getCost(cost: IntArray, startIndex: Int, mem: MutableMap<Int, Int>): Int {

        if (startIndex > cost.lastIndex) return 0
        if (startIndex == cost.lastIndex) return cost[startIndex]

        if (mem[startIndex] != null) return mem[startIndex]!!

        val option1Cost = getCost(cost, startIndex + 1, mem)
        mem[startIndex + 1] = option1Cost

        val option2Cost = getCost(cost, startIndex + 2, mem)
        mem[startIndex + 2] = option2Cost

        return cost[startIndex] + Math.min(option1Cost, option2Cost)
    }
}