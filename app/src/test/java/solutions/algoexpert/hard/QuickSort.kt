package solutions.algoexpert.hard

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Quick%20Sort
 */
class QuickSort {

    @Test
    fun test() {
        val sort =
            quickSort(mutableListOf(8, 5, 2, 9, 7, 6, 3))
        assertEquals(listOf(2, 3, 5, 6, 7, 8, 9), sort)
    }

    @Test
    fun testX() {
        val sort =
            quickSort(mutableListOf(8, 5, 2, 9, 6, -1, 1, 3))
        assertEquals(listOf(-1, 1, 2, 3, 5, 6, 8, 9), sort)
    }

    fun quickSort(array: MutableList<Int>): List<Int> {
        return quickSortHelper(array, 0, array.lastIndex)
    }

    fun quickSortHelper(array: MutableList<Int>, left: Int, right: Int): List<Int> {

        if (left < right) {
            val pivot = partition(array, left, right)
            quickSortHelper(array, left, pivot - 1)
            quickSortHelper(array, pivot + 1, right)
        }

        return array
    }

    /**
     * The job of the partition function is to return the position of the pivot value.
     * Based on this position, we'll sort the left and the right side of the array in the calling function.
     *
     * @return index of the pivot
     */
    fun partition(array: MutableList<Int>, left: Int, right: Int): Int {

        val pivot = array[right]
        var i = left    // this i is where the selected pivot will be placed

        (left..right).forEachIndexed { j, _ ->
            if (array[left + j] < pivot) {
                swap(array, i, left + j)
                i++ // that makes sure the swapped value is 'behind' the gate keeper i
            }
        }

        swap(array, i, right)

        return i
    }

    private fun swap(array: MutableList<Int>, i: Int, j: Int) {
        val temp = array[j]
        array[j] = array[i]
        array[i] = temp
    }
}