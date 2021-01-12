package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Max%20Subset%20Sum%20No%20Adjacent
 */
class NumberOfWayToMakeChange {

    @Test
    fun testCase1() {

        val n = 6
        val denoms = listOf(1, 5)

        val numberOfWays = numberOfWaysToMakeChange(n, denoms)
        Assert.assertEquals(2, numberOfWays)
    }

    @Test
    fun testCase2() {

        val n = 25
        val denoms = listOf(1, 5, 10, 25)

        val numberOfWays = numberOfWaysToMakeChange(n, denoms)
        Assert.assertEquals(13, numberOfWays)
    }

    @Test
    fun testCase3() {

        val n = 0
        val denoms = listOf(2, 3, 4, 7)

        val numberOfWays = numberOfWaysToMakeChange(n, denoms)
        Assert.assertEquals(1, numberOfWays)
    }

    @Test
    fun testCase4() {

        val n = 12
        val denoms = listOf(2, 3, 7)

        val numberOfWays = numberOfWaysToMakeChange(n, denoms)
        Assert.assertEquals(4, numberOfWays)
    }


    fun numberOfWaysToMakeChange(n: Int, denoms: List<Int>): Int {

        val arr = Array(n + 1) { 0 }
        arr[0] = 1  // base case

        denoms.forEach { denom ->
            arr.forEachIndexed { index, i ->
                if (denom <= index) {
                    arr[index] += arr[index - denom]
                }
            }
        }

        return arr[arr.size - 1]
    }

    /**
     * That's the way I did it before I learnt about dynamic programming
     */
    fun numberOfWaysToMakeChange2(n: Int, denoms: List<Int>): Int {

        var count = 0
        for (i in denoms.size - 1 downTo 0) {

            val denom = denoms[i]

            when {
                n == 0 -> return 1
                denom == 1 -> count++
                denom > n || denom == 0 -> {
                }
                denom == n -> count++
                else -> {  // denom is smaller
                    var left = n - denom

                    do {
                        val countInRestOfTheArray =
                            when {
                                i > 0 -> numberOfWaysToMakeChange(
                                    left,
                                    denoms.subList(0, i)
                                )
                                n.rem(denom) == 0 -> return ++count
                                else -> 0
                            }

                        count += countInRestOfTheArray
                        left -= denom
                        // count++
                    } while (left >= 0)
                }
            }
        }

        return count
    }
}