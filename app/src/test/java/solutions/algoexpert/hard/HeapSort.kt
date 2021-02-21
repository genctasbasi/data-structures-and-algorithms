package solutions.algoexpert.hard

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Heap%20Sort
 */
class HeapSort {

    @Test
    fun test() {
        val sort =
            heapSort(mutableListOf(8, 5, 2, 9, 5, 6, 3))
        assertEquals(listOf(2, 3, 5, 5, 6, 8, 9), sort)
    }

    @Test
    fun test2() {
        val sort =
            heapSort(mutableListOf(10, 6, 7, 5, 15, 17, 12))
        assertEquals(listOf(5, 6, 7, 10, 12, 15, 17), sort)
    }

    @Test
    fun test3() {
        val sort =
            heapSort(mutableListOf(10, 6, 7, 5, 15, 17, 12, 3, 2, 90))
        assertEquals(listOf(2, 3, 5, 6, 7, 10, 12, 15, 17, 90), sort)
    }

    fun heapSort(array: MutableList<Int>): List<Int> {

        if (array.size < 2) return array

        val itemIndex = array.lastIndex / 2

        // phase 1: build the heap (turn the array into a heap)
        for (index in itemIndex downTo 0) {
            bubbleDown(array, index, array.lastIndex)
        }

        var endIndex = array.lastIndex

        // phase 2: extraction
        while (endIndex >= 0) {
            swap(array, 0, endIndex)
            bubbleDown(array, 0, endIndex - 1)
            endIndex--
        }

        return array
    }

    private fun bubbleDown(array: MutableList<Int>, index: Int, endIndex: Int) {

        var child1Index = 2 * index + 1
        var child2Index = 2 * index + 2

        if (child1Index > endIndex && child2Index > endIndex) return

        var currentItemIndex = index

        var child1: Int? = if (child1Index <= endIndex) array[child1Index] else null
        var child2: Int? = if (child2Index <= endIndex) array[child2Index] else null

        while (child1 != null || child2 != null) {

            val currentItem = array[currentItemIndex]

            if (child1 == null) {
                if (child2!! > currentItem) {
                    swap(array, child2Index, currentItemIndex)
                    currentItemIndex = child2Index
                } else return

            } else if (child2 == null) {
                if (child1 > currentItem) {
                    swap(array, child1Index, currentItemIndex)
                    currentItemIndex = child1Index
                } else return
            } else if (child1 > currentItem || child2 > currentItem) {
                if (child1 > child2) {
                    swap(array, child1Index, currentItemIndex)
                    currentItemIndex = child1Index
                } else {
                    swap(array, child2Index, currentItemIndex)
                    currentItemIndex = child2Index
                }
            } else {
                return
            }

            child1Index = 2 * currentItemIndex + 1
            child2Index = 2 * currentItemIndex + 2

            child1 = if (child1Index <= endIndex) array[child1Index] else null
            child2 = if (child2Index <= endIndex) array[child2Index] else null
        }
    }

    private fun swap(array: MutableList<Int>, index1: Int, index2: Int) {
        val temp = array[index1]
        array[index1] = array[index2]
        array[index2] = temp
    }

}