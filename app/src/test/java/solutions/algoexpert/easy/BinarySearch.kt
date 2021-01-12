package solutions.algoexpert.easy

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import solutions.algoexpert._utils.BinaryTree
import solutions.algoexpert._utils.Helper

/**
 * https://www.algoexpert.io/questions/Binary%20Search
 */
class BinarySearch {

    lateinit var binaryTree: BinaryTree

    @Before
    fun setup() {
        binaryTree = Helper.buildBinaryTree()
    }

    @Test
    fun test() {
        assertEquals(3, binarySearch(listOf(0, 1, 21, 33, 45, 45, 61, 71, 72, 73), 33))
        assertEquals(9, binarySearch(listOf(0, 1, 21, 33, 45, 45, 61, 71, 72, 73), 73))
        assertEquals(0, binarySearch(listOf(0, 1, 21, 33, 45, 45, 61, 71, 72, 73), 0))
        assertEquals(-1, binarySearch(listOf(0, 1, 21, 33, 45, 45, 61, 71, 72, 73), 11))
        assertEquals(-1, binarySearch(listOf(0, 1, 21, 33, 45, 45, 61, 71, 72, 73), 99))
        assertEquals(-1, binarySearch(listOf(0, 1, 21, 33, 45, 45, 61, 71, 72, 73), -9))
    }

    fun binarySearch(array: List<Int>, target: Int): Int {

        var left = 0
        var right = array.size - 1
        var mid: Int
        while (hasValidIndex(left, right, array.size)) {

            mid = (left + right).div(2)

            if (array[mid] == target) return mid

            if (array[mid] > target) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }

        // TODO: could be better...
        return if (left == right && array[left] == target) left else -1
    }

    private fun hasValidIndex(
        left: Int,
        right: Int,
        size: Int
    ) = left != right && left < right && right > 0 && left < size

}