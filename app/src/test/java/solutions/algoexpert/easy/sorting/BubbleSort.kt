package solutions.algoexpert.easy.sorting

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Bubble%20Sort
 * Complexity:
 *  Time: O(n^2) Best: O(n): if sorted already
 *  Space: O(1): Swaps in place, no other array is used
 */
class BubbleSort {

    @Test
    fun test() {
        val sorted = bubbleSort(mutableListOf(8, 5, 2, 9, 5, 6, 3))
        assertEquals(listOf(2, 3, 5, 5, 6, 8, 9), sorted)
    }

    fun bubbleSort(array: MutableList<Int>): List<Int> {

        if (array.size < 2) return array

        var hadSwapped = true

        var index1: Int
        var index2: Int

        while (hadSwapped) {

            hadSwapped = false
            index1 = 0
            index2 = 1

            while (index2 < array.size) {

                if (array[index2] < array[index1]) {
                    val temp = array[index1]
                    array[index1] = array[index2]
                    array[index2] = temp
                    hadSwapped = true
                }

                index1++
                index2++
            }
        }

        return array
    }

}