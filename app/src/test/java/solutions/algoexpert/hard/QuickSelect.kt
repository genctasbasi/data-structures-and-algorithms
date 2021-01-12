package solutions.algoexpert.hard

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Quickselect
 */
class QuickSelect {

    @Test
    fun test() {
        val search =
            `quick select O(n)`(mutableListOf(8, 5, 2, 9, 7, 6, 3), 3)
        assertEquals(5, search)
    }


    fun `quick select O(n)`(array: MutableList<Int>, k: Int): Int {
        return `quick select O(n) helper`(array, k, 0, array.lastIndex)
    }

    fun `quick select O(n) helper`(array: MutableList<Int>, k: Int, left: Int, right: Int): Int {

        if (left < right) {
            val pivot = partition(array, left, right)

            return if (pivot >= k - 1) {
                `quick select O(n) helper`(array, k, left, pivot - 1)
                array[k - 1]
            } else {
                `quick select O(n) helper`(array, k, pivot + 1, right)
                array[k - 1]
            }
        }

        return array[k - 1]
    }

    private fun partition(array: MutableList<Int>, left: Int, right: Int): Int {

        val pivot = array[right]
        var i = left

        (left..right).forEachIndexed { indexJ, _ ->
            if (array[left + indexJ] < pivot) {
                swap(array, i, left + indexJ)
                i++
            }
        }

        swap(array, i, right)

        return i
    }

    private fun swap(array: MutableList<Int>, indexI: Int, indexJ: Int) {
        val temp = array[indexI]
        array[indexI] = array[indexJ]
        array[indexJ] = temp
    }

    fun `quick select O(kn)`(array: MutableList<Int>, k: Int): Int {

        var smallest = Int.MIN_VALUE
        var smallestCurrent = Int.MAX_VALUE
        (1..k).forEach { pass ->
            array.forEach { number ->
                if (number > smallest && number < smallestCurrent) {
                    smallestCurrent = number
                }
            }

            smallest = smallestCurrent
            smallestCurrent = Int.MAX_VALUE
        }

        return smallest
    }

}