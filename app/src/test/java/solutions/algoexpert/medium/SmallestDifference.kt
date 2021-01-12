package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test
import kotlin.math.abs

/**
 * https://www.algoexpert.io/questions/Smallest%20Difference
 */
class SmallestDifference {

    @Test
    fun test() {

        val list1 = mutableListOf(-1, 5, 10, 20, 28, 3)
        val list2 = mutableListOf(26, 134, 135, 15, 17)

        val list3 = mutableListOf(-1, 5, 10, 20, 3)
        val list4 = mutableListOf(26, 134, 135, 15, 17)

        Assert.assertEquals(
            listOf(28, 26),
            smallestDifference2(list1, list2)
        )

        Assert.assertEquals(
            listOf(20, 17),
            smallestDifference2(list3, list4)
        )
    }

    /**
     * Time: O(n^2)
     * Space: O(1)
     */
    fun smallestDifference(arrayOne: MutableList<Int>, arrayTwo: MutableList<Int>): List<Int> {

        var currentMin = Int.MAX_VALUE
        val list = mutableListOf<Int>()
        arrayOne.forEach { item1 ->
            arrayTwo.forEach { item2 ->

                val abs = abs(item1.minus(item2))
                if (abs < currentMin) {
                    currentMin = abs
                    list.clear()
                    list.add(item1)
                    list.add(item2)
                }
            }
        }

        return list
    }

    /**
     * Time: O(nlog(n)) - Quick sort
     */
    fun smallestDifference2(arrayOne: MutableList<Int>, arrayTwo: MutableList<Int>): List<Int> {

        arrayOne.sort()
        arrayTwo.sort()

        var pointer1 = 0
        var pointer2 = 0
        val list = mutableListOf<Int>()
        var minSum = Int.MAX_VALUE

        while (pointer1 < arrayOne.size && pointer2 < arrayTwo.size) {

            val currentSum = Math.abs(arrayOne[pointer1].minus(arrayTwo[pointer2]))

            if (currentSum < minSum) {
                minSum = currentSum
                list.clear()
                list.add(arrayOne[pointer1])
                list.add(arrayTwo[pointer2])
            }

            when {
                arrayOne[pointer1] < arrayTwo[pointer2] -> pointer1++
                arrayOne[pointer1] > arrayTwo[pointer2] -> pointer2++

                else -> {
                    return listOf(arrayOne[pointer1], arrayTwo[pointer2])
                }
            }
        }

        return list
    }
}