package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test
import java.util.*

/**
 * https://www.algoexpert.io/questions/Three%20Number%20Sum
 */
class ThreeNumberSum {

    @Test
    fun test() {

        val list = mutableListOf(12, 3, 1, 2, -6, 5, -8, 6)
        Assert.assertEquals(
            listOf(listOf(-8, 2, 6), listOf(-8, 3, 5), listOf(-6, 1, 5)),
            threeNumberSum(list, 0)
        )
    }

    fun threeNumberSum(array: MutableList<Int>, targetSum: Int): List<List<Int>> {

        val list = mutableListOf<List<Int>>()
        array.sort()

        array.forEachIndexed { index, item ->

            var indexLeft = index + 1
            var indexRight = array.size - 1

            val sumNeeded = targetSum - item

            while (indexLeft < indexRight) {

                val currentSum = array[indexLeft] + array[indexRight]
                when {
                    currentSum == sumNeeded -> {
                        list.add(listOf(item, array[indexLeft], array[indexRight]))
                        indexLeft++
                    }
                    currentSum < sumNeeded -> indexLeft++
                    else -> indexRight--
                }
            }
        }

        return list
    }
}