package solutions.algoexpert.easy.sorting

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Selection%20Sort
 * Complexity:
 *  Time: O(n^2) Best: O(n): if sorted already
 *  Space: O(1): Swaps in place, no other array is used
 */
class SelectionSort {

    @Test
    fun test() {
        val sorted = selectionSort(mutableListOf(8, 5, 2, 9, 5, 6, 3))
        assertEquals(listOf(2, 3, 5, 5, 6, 8, 9), sorted)
    }

    fun selectionSort(array: MutableList<Int>): List<Int> {

        if (array.size < 2) return array

        var indexLoop = 0
        var minIndex = 0

        while (minIndex < array.size) {

            while (indexLoop < array.size) {

                if (array[indexLoop] < array[minIndex]) {
                    swap(indexLoop, minIndex, array)
                }

                indexLoop++
            }

            minIndex++
            indexLoop = minIndex + 1
        }

        return array
    }

    private fun swap(index1: Int, index2: Int, array: MutableList<Int>) {
        val temp = array[index1]
        array[index1] = array[index2]
        array[index2] = temp
    }
}