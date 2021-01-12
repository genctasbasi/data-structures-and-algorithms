package solutions.algoexpert.hard

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Search%20For%20Range
 */
class SearchForRange {

    @Test
    fun test() {
        val search =
            searchForRange(listOf(0, 1, 21, 33, 45, 45, 45, 45, 45, 45, 61, 71, 73), 45)
        assertEquals(listOf(4, 9), search)
    }

    @Test
    fun test9() {
        val search =
            searchForRange(listOf(0, 1, 21, 33, 45, 45, 45, 45, 45, 45, 45, 45, 45), 45)
        assertEquals(listOf(4, 12), search)
    }

    @Test
    fun test2() {
        val search =
            searchForRange(listOf(5, 7, 7, 8, 8, 10), 5)
        assertEquals(listOf(0), search)
    }

    @Test
    fun test3() {
        val search =
            searchForRange(listOf(5, 7, 7, 8, 8, 10), 7)
        assertEquals(listOf(1, 2), search)
    }

    @Test
    fun test5() {
        val search =
            searchForRange(listOf(5, 7, 7, 8, 8, 10), 10)
        assertEquals(listOf(5, 5), search)
    }

    fun searchForRange(array: List<Int>, target: Int): List<Int> {
        val search = searchForRange(array, target, 0, array.lastIndex)

        return when {
            search[0] != -1 && search[1] == -1 -> listOf(search[0], search[0])
            search[0] == -1 && search[1] != -1 -> listOf(search[1], search[1])
            else -> search
        }
    }

    fun searchForRange(array: List<Int>, target: Int, startIndex: Int, endIndex: Int): List<Int> {

        if (startIndex == endIndex) {
            return if (array[startIndex] == target) listOf(startIndex, endIndex) else listOf(-1, -1)
        }

        val pointerM = startIndex + (endIndex - startIndex) / 2
        val numberM = array[pointerM]

        if (numberM != target) {

            return if (pointerM < endIndex && array[pointerM + 1] == target) {
                // found left edge
                val rightRange = searchForRange(array, target, pointerM + 1, endIndex)
                listOf(pointerM + 1, rightRange[1])
            } else if (pointerM > 0 && array[pointerM - 1] == target) {
                // found right edge
                val leftRange = searchForRange(array, target, startIndex, pointerM)
                listOf(leftRange[0], pointerM - 1)
            } else {

                val leftRange = searchForRange(array, target, startIndex, pointerM)
                val rightRange = searchForRange(array, target, pointerM + 1, endIndex)

                listOf(leftRange[0], rightRange[1])
            }

        } else {
            // so the mid point we chose _is_ the target
            // check if it's an edge
            return if (pointerM > 1 && array[pointerM - 1] != target) {    // left edge

                val rightRange = searchForRange(array, target, pointerM + 1, endIndex)
                listOf(pointerM, rightRange[1])

            } else if (pointerM < array.lastIndex && array[pointerM + 1] != target) {   // right edge
                val leftRange = searchForRange(array, target, startIndex, pointerM)
                listOf(leftRange[0], pointerM)
            } else {
                val leftRange = searchForRange(array, target, startIndex, pointerM)
                val rightRange = searchForRange(array, target, pointerM + 1, endIndex)

                listOf(leftRange[0], rightRange[1])
            }
        }
    }
}