package solutions.algoexpert.easy.sorting

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Insertion%20Sort
 * Complexity:
 *  Time: O(n^2) Best: O(n): if sorted already
 *  Space: O(1): Swaps in place, no other array is used
 */
class InsertionSort {

    @Test
    fun test() {
        val sorted = insertionSort(mutableListOf(8, 5, 2, 9, 5, 6, 3))
        assertEquals(listOf(2, 3, 5, 5, 6, 8, 9), sorted)
    }

    fun insertionSort(array: MutableList<Int>): List<Int> {

        if (array.size < 2) return array

        var loopIndex = 1

        while (loopIndex < array.size) {

            // need swap? move it towards the start of the list
            while (loopIndex > 0 && array[loopIndex] < array[loopIndex - 1]) {
                val temp = array[loopIndex - 1]
                array[loopIndex - 1] = array[loopIndex]
                array[loopIndex] = temp
                loopIndex--
            }

            loopIndex++
        }

        return array
    }
}