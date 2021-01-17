package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Min%20Number%20Of%20Coins%20For%20Change
 */
class MinNumberOfCoinsForChange {

    @Test
    fun testCase1() {
        val n = 37
        val denoms = listOf(1, 5, 10)
        Assert.assertEquals(6, minNumberOfCoinsForChange(n, denoms))
    }

    @Test
    fun testCase2() {
        val n = 9
        val denoms = listOf(3, 5)
        Assert.assertEquals(3, minNumberOfCoinsForChange(n, denoms))
    }

    fun minNumberOfCoinsForChange(n: Int, denoms: List<Int>): Int {

        val arr = Array(n + 1) { Int.MAX_VALUE }
        arr[0] = 0

        denoms.forEach { denom ->
            arr.forEachIndexed { index, coin ->

                if (index >= denom) {

                    if (arr[index - denom] != Int.MAX_VALUE) {
                        val newPotentialMin = arr[index - denom] + 1
                        arr[index] = Math.min(newPotentialMin, arr[index])
                    }

                }

            }
        }

        return if (arr[n] == Int.MAX_VALUE) -1 else arr[n]
    }

    /**
     * Greedy algorithm - Fails for the case testCase2, obviously.
     */
    fun minNumberOfCoinsForChange2(n: Int, denoms: List<Int>): Int {

        var count = 0
        var remaining = n
        denoms.sortedDescending().forEach {
            count += remaining.div(it)
            remaining = remaining.rem(it)

        }

        return if (remaining == 0) count else -1
    }

}