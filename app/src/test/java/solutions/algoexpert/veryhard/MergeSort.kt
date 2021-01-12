package solutions.algoexpert.veryhard

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Merge%20Sort
 */
class MergeSort {

    @Test
    fun test() {
        val sort = mergeSort(mutableListOf(8, 5, 2, 9, 5, 6, 3))
        assertEquals(listOf(2, 3, 5, 5, 6, 8, 9), sort)
    }

    fun mergeSort(array: MutableList<Int>): List<Int> {
        return split(array, 0, array.lastIndex)
    }

    fun split(array: MutableList<Int>, left: Int, right: Int): List<Int> {

        if (left >= right) return listOf(array[left])

        val mid = left + ((right - left) / 2)

        val leftList = split(array, left, mid)
        val rightList = split(array, mid + 1, right)

        val merged = merge(leftList, rightList)

        return merged
    }

    private fun merge(leftList: List<Int>, rightList: List<Int>): List<Int> {

        if (leftList.isEmpty()) return rightList
        if (rightList.isEmpty()) return leftList

        val merged = mutableListOf<Int>()

        var p1 = 0
        var p2 = 0

        while (p1 <= leftList.lastIndex || p2 <= rightList.lastIndex) {

            when {
                p1 > leftList.lastIndex -> {
                    merged.add(rightList[p2])
                    p2++
                }
                p2 > rightList.lastIndex -> {
                    merged.add(leftList[p1])
                    p1++
                }
                leftList[p1] < rightList[p2] -> {
                    merged.add(leftList[p1])
                    p1++
                }
                else -> {
                    merged.add(rightList[p2])
                    p2++
                }
            }
        }

        return merged
    }

}