package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Max%20Subset%20Sum%20No%20Adjacent
 */
class MaxSubsetSumNoAdjacent {

    @Test
    fun testCase1() {

        val list1 = listOf(75, 105, 120, 75, 90, 135)
        val sum = maxSubsetSumNoAdjacent(list1)

        Assert.assertEquals(330, sum)
    }

    fun maxSubsetSumNoAdjacent(array: List<Int>): Int {

        var currentMax = 0
        var sumMinus2 = 0
        var sumMinus1 = 0
        array.forEachIndexed { index, value ->

            val max = Math.max(sumMinus2 + value, sumMinus1)

            if (index - 2 >= 0)
                sumMinus2 = array[index - 2]

            sumMinus2 = sumMinus1
            sumMinus1 = max

            if (max > currentMax)
                currentMax = max

        }

        return currentMax
    }
    
}