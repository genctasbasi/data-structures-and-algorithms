package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Max%20Sum%20Increasing%20Subsequence
 */
class MaxSumIncreasingSubsequence {

    @Test
    fun test1() {
        val sum = maxSumIncreasingSubsequence(listOf(10, 70, 20, 30, 50, 11, 30))
        assertEquals(Pair(110, listOf(10, 20, 30, 50)), sum)
    }

    @Test
    fun test2() {
        val sum = maxSumIncreasingSubsequence(listOf(8, 12, 2, 3, 15, 5, 7))
        assertEquals(Pair(35, listOf(10, 20, 30, 50)), sum)
    }

    @Test
    fun test3() {
        val sum = maxSumIncreasingSubsequence(listOf(-1, 1))
        assertEquals(Pair(35, listOf(10, 20, 30, 50)), sum)
    }

    fun maxSumIncreasingSubsequence(array: List<Int>): Pair<Int, List<Int>> {

        if (array.size == 1) return Pair(array.first(), array)

        val sums = Array(array.size) { 0 }

        array.forEachIndexed { index, item ->

            if (index == 0) {
                sums[0] = item
            } else {

                var localSum = item
                for (i in 0 until index) {
                    if (item > array[i]) {
                        if ((sums[i] + item) > localSum)
                            localSum = sums[i] + item
                    }
                }

                sums[index] = localSum
            }
        }

        val list = mutableListOf<Int>()
        val max: Int = sums.max()!!
        val maxIndex: Int = sums.indexOf(max)
        list.add(array[maxIndex])
        var left = max
        var leftIndex = maxIndex

        while (leftIndex > 0) {
            left -= array[leftIndex]
            if(left > sums.size) break
            leftIndex = sums.lastIndexOf(left)
            list.add(0, array[leftIndex])
        }

        return Pair(sums.max()!!, list)
    }
}