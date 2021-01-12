package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Subarray%20Sort
 */
class SubarraySort {

    @Test
    fun test() {
        val list = subarraySort(mutableListOf(1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19))
        assertEquals(listOf(3, 9), list)
    }

    @Test
    fun test2() {
        val list = subarraySort(mutableListOf(1, 2))
        assertEquals(listOf(-1, -1), list)
    }

    @Test
    fun test3() {
        val list = subarraySort(mutableListOf(2, 1))
        assertEquals(listOf(-1, -1), list)
    }

    fun subarraySort(array: List<Int>): List<Int> {

        if (array.size == 2) {
            return if (array[0] < array[1])
                listOf(-1, -1)
            else listOf(0, 1)
        }

        var currentMax = Int.MIN_VALUE

        var sortStart = -1
        var sortEnd = -1

        for (i in array.indices) {
            if (array[i] < currentMax) {
                val sortFit = getIndexPosition(array, array[i])

                if (sortStart == -1 || sortFit < sortStart)
                    sortStart = sortFit

                if (i > sortEnd)
                    sortEnd = i
            }

            if (array[i] > currentMax)
                currentMax = array[i]
        }

        return listOf(sortStart, sortEnd)
    }

    fun getIndexPosition(array: List<Int>, number: Int): Int {

        for (i in array.indices) {
            if (array[i] > number)
                return i
        }

        return -1
    }

    fun subarraySort2(array: List<Int>): List<Int> {

        if (array.size == 2) {
            return if (array[0] < array[1])
                listOf(-1, -1)
            else listOf(0, 1)
        }

        val list = mutableListOf<Int>()

        var prevItem = Int.MIN_VALUE
        var problemNumber = Int.MIN_VALUE

        for (i in array.indices) {
            if (array[i] < prevItem) {
                problemNumber = array[i]
                break
            }

            prevItem = array[i]
        }

        if (problemNumber == Int.MIN_VALUE) {
            return listOf(-1, -1)
        }

        for (i in array.indices) {
            if (array[i] > problemNumber) {
                list.add(i)
                break
            }
        }

        prevItem = Int.MAX_VALUE
        for (i in array.size - 1 downTo 0) {
            if (array[i] > prevItem) {
                problemNumber = array[i]
                break
            }

            prevItem = array[i]
        }

        for (i in array.size - 1 downTo 0) {
            if (array[i] < problemNumber) {
                list.add(i)
                break
            }
        }

        return list
    }
}