package solutions.other

import junit.framework.TestCase.assertEquals
import org.junit.Test

class QuickSort {

    @Test
    fun test() {
        val sort =
            quickSort(mutableListOf(8, 5, 2, 9, 7, 6, 3))
        assertEquals(listOf(2, 3, 5, 6, 7, 8, 9), sort)
    }

    fun quickSort(array: MutableList<Int>): List<Int> {
        if (array.size < 2) return array
        qsHelper(array, 0, array.lastIndex)
        return array
    }

    fun qsHelper(array: MutableList<Int>, left: Int, right: Int) {

        if (left < right) {
            val pivot = partition(array, left, right)

            qsHelper(array, left, pivot - 1)
            qsHelper(array, pivot, right)
        }
    }

    /**
     * returns the pivot index
     */
    private fun partition(array: MutableList<Int>, left: Int, right: Int): Int {

        val pivot = array[right]
        var i = left

        (left..right).forEachIndexed { j, _ ->
            val item = array[left + j]
            if (item < pivot) {
                swap(array, i, left + j)
                i++
            }
        }

        // put the pivot to its place
        swap(array, i, right)
        return i
    }

    fun swap(array: MutableList<Int>, index1: Int, index2: Int) {
        val temp = array[index1]
        array[index1] = array[index2]
        array[index2] = temp
    }
}