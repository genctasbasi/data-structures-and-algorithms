package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Four%20Number%20Sum
 */
class FourNumberSum {

    @Test
    fun test() {
        val lists = fourNumberSum(mutableListOf(7, 6, 4, -1, 1, 2), 16)
        assertEquals(
            listOf(
                listOf(7, 6, 4, -1),
                listOf(7, 6, 1, 2)
            ), lists
        )
    }

    @Test
    fun test2() {
        val lists = fourNumberSum(mutableListOf(5, -5, -2, 2, 3, -3), 0)
        assertEquals(
            listOf(
                listOf(-5, 5, 3, -3),
                listOf(-5, 5, 2, -2),
                listOf(-2, 2, 3, -3)
            ), lists
        )
    }

    @Test
    fun test3() {
        val lists = fourNumberSum(mutableListOf(-1, 22, 18, 4, 7, 11, 2, -5, -3), 30)
        assertEquals(
            listOf(
                listOf(-5, 2, 11, 22),
                listOf(-3, 4, 11, 18),
                listOf(-1, 2, 7, 22),
                listOf(-1, 2, 11, 18),
                listOf(-3, 4, 7, 22)
            ), lists
        )
    }

    @Suppress("ConvertTwoComparisonsToRangeCheck")
    fun fourNumberSum(array: MutableList<Int>, targetSum: Int): List<List<Int>> {

        if (array.size < 3) return listOf()

        val sums = mutableListOf<List<Int>>()

        var index1: Int
        var index2: Int
        var index3: Int

        val arraySorted = array.sorted()
        arraySorted.forEachIndexed { index0, index0Item ->

            // reset indexes
            index1 = index0 + 1
            index2 = index1 + 1
            index3 = arraySorted.size - 1

            while (index1 < index2 && index2 < index3) {

                val index1Item = arraySorted[index1]
                val index2Item = arraySorted[index2]
                val index3Item = arraySorted[index3]

                val indexSum = index0Item + index1Item + index2Item + index3Item

                if (indexSum == targetSum) {
                    sums.add(listOf(index0Item, index1Item, index2Item, index3Item))

                    if (index2 + 1 == index3) { // index2 already at max, move index1
                        index1++
                        index2 = index1 + 1
                        index3 = array.size - 1
                        continue
                    } else {
                        index2++
                    }

                } else if (indexSum < targetSum) {
                    if (index2 + 1 == index3) { // index2 already at max, move index1
                        index1++
                        index2 = index1 + 1
                        index3 = array.size - 1
                        continue
                    } else {
                        index2++
                    }
                } else if (indexSum > targetSum) {
                    if (index3 - 1 == index2) { // cannot go smaller
                        index1++
                        index2 = index1 + 1
                        index3 = array.size - 1
                        continue
                    } else {
                        index3--
                        continue
                    }
                }
            }
        }

        return sums
    }
}