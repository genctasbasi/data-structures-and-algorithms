package solutions.algoexpert.hard

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Sort%20K-Sorted%20Array
 */
class SortKSortedArray {

    @Test
    fun test() {
        assertArrayEquals(
            arrayOf(1, 2, 3, 4, 5, 6, 7),
            sortKSortedArray(mutableListOf(3, 2, 1, 5, 4, 7, 6, 5), 3).toTypedArray()
        )
    }

    @Test
    fun test1() {
        assertArrayEquals(
            arrayOf(-100, 2, 3, 4, 5),
            sortKSortedArray(mutableListOf(5, 4, 3, 2, -100), 5).toTypedArray()
        )
    }

    fun sortKSortedArray(array: MutableList<Int>, k: Int): MutableList<Int> {

        if (array.isEmpty()) return mutableListOf()
        if (k == 0) return array

        val minHeap = java.util.PriorityQueue<Int>()
        val result = Array(array.size) { 0 }

        // this is O(k)
        (0..k).forEach {
            if (it <= array.lastIndex)
                minHeap.offer(array[it])
        }

        array.forEachIndexed { index, it ->
            val min = minHeap.remove()
            result[index] = min
            if (index + k + 1 <= array.lastIndex)
                minHeap.offer(array[index + k + 1])
        }

        return result.toMutableList()
    }

}