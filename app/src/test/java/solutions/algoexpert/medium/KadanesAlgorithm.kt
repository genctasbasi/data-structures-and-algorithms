package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Kadane's%20Algorithm
 */
class KadanesAlgorithm {

    @Test
    fun testCase1() {
        val list1 = listOf(3, 5, -9, 1, 3, -2, 3, 4, 7, 2, -9, 6, 3, 1, -5, 4)
        Assert.assertEquals(19, kadanesAlgorithm(list1))
    }

    @Test
    fun testCase2() {
        val list1 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        Assert.assertEquals(55, kadanesAlgorithm(list1))
    }

    @Test
    fun testCase3() {
        val list1 = listOf(-1, -2, -3, -4, -5, -6, -7, -8, -9, -10)
        Assert.assertEquals(-1, kadanesAlgorithm(list1))
    }

    @Test
    fun testCase4() {
        val list1 = listOf(1, 2, 3, 4, 5, 6, -20, 7, 8, 9, 10)
        Assert.assertEquals(35, kadanesAlgorithm(list1))
    }

    fun kadanesAlgorithm(array: List<Int>): Int {
        var max = Int.MIN_VALUE
        var totalSum = Int.MIN_VALUE

        array.forEach {

            if (totalSum == Int.MIN_VALUE || it > it + totalSum) {
                totalSum = it   // re-start
            } else {
                totalSum += it
            }

            if (totalSum > max)
                max = totalSum

        }

        return max
    }

    fun kadanesAlgorithm2(array: List<Int>): Int {

        val arrayGrouped = mutableListOf<Int>()

        var positiveSum = 0
        var negativeSum = 0
        var sumDirection = if (array[0] > 0) 1 else -1
        array.forEach {

            if (it < 0) {
                negativeSum += it
                if (sumDirection == 1) {  // switch from positive to negative
                    arrayGrouped.add(positiveSum)
                    positiveSum = 0
                }

                sumDirection = -1
            } else if (it > 0) {

                positiveSum += it

                if (sumDirection == -1) {  // switch from positive to negative
                    arrayGrouped.add(negativeSum)
                    negativeSum = 0
                }

                sumDirection = 1
            }
        }

        if (positiveSum != 0) arrayGrouped.add(positiveSum)
        if (negativeSum != 0) arrayGrouped.add(negativeSum)

        if (arrayGrouped[0] < 0) arrayGrouped.drop(0)
        if (arrayGrouped[arrayGrouped.size - 1] < 0) arrayGrouped.dropLast(1)

        var index = arrayGrouped.size - 1

        var totalSum = 0
        var potentialAdd = 0

        while (index >= 0) {

            if (index == 0) {
                totalSum += arrayGrouped[0]
                break
            }

            val current = arrayGrouped[index]
            val beforeCurrent = arrayGrouped[index - 1]

            if ((current + beforeCurrent) > 0) {    // add to sum
                totalSum += (current + beforeCurrent)
                potentialAdd = if (index >= 2) arrayGrouped[index - 2] else potentialAdd
            } else {
                totalSum += potentialAdd
            }

            index -= 2
        }

        return if (totalSum == 0) -1 else totalSum
    }

}