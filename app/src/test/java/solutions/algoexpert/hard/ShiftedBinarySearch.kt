package solutions.algoexpert.hard

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Shifted%20Binary%20Search
 */
class ShiftedBinarySearch {

    @Test
    fun test() {
        val search =
            shiftedBinarySearch(listOf(45, 61, 71, 72, 73, 0, 1, 21, 33, 37), 33)
        assertEquals(8, search)
    }

    @Test
    fun test2() {
        val search =
            shiftedBinarySearch(listOf(72, 73, 0, 1, 21, 33, 37, 45, 61, 71), 0)
        assertEquals(2, search)
    }

    @Test
    fun test3() {
        val search =
            shiftedBinarySearch(listOf(5, 23, 111, 1), 111)
        assertEquals(2, search)
    }

    fun shiftedBinarySearch(array: List<Int>, target: Int): Int {

        if (array.isEmpty()) return -1
        if (array.size == 1 && array[0] != target) return -1

        val pointerL = 0
        val pointerM = array.lastIndex / 2
        val pointerR = array.lastIndex

        val numberL = array[pointerL]
        val numberM = array[pointerM]
        val numberR = array[pointerR]

        if (numberL == target) return pointerL
        if (numberM == target) return pointerM
        if (numberR == target) return pointerR

        // now decide which way to take - left or right
        if (numberL < numberM) {
            return if (target in (numberL..numberM)) {
                // left
                shiftedBinarySearch(array.subList(0, pointerM), target)
            } else {
                // right
                val rightIndex =
                    shiftedBinarySearch(array.subList(pointerM + 1, array.size), target)
                if (rightIndex == -1) rightIndex else rightIndex + pointerM + 1
            }
        } else {
            return if (target > numberL || target in (0..numberM)) {
                // left
                shiftedBinarySearch(array.subList(0, pointerM), target)
            } else {
                // right
                val rightIndex =
                    shiftedBinarySearch(array.subList(pointerM + 1, array.size), target)
                if (rightIndex == -1) rightIndex else rightIndex + pointerM + 1
            }
        }
    }

}