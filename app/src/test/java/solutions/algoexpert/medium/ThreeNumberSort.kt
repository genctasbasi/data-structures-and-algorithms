package solutions.algoexpert.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Three%20Number%20Sort
 */

class ThreeNumberSort {

    @Test
    fun test() {
        val threeNumberSort =
            threeNumberSort(mutableListOf(1, 0, 0, -1, -1, 0, 1, 1), listOf(0, 1, -1))
        assertEquals(listOf(0, 0, 0, 1, 1, 1, -1, -1), threeNumberSort)
    }

    fun threeNumberSort(array: MutableList<Int>, order: List<Int>): List<Int> {

        var changeCount = 0
        var index1 = 0
        var index2 = 1
        order.forEach { order ->

            index1 = changeCount

            while (index1 < array.size) {

                if (array[index1] == order) {
                    changeCount++
                    index1++
                    continue
                }

                for (j in index1 + 1 until array.size) {

                    if (array[j] == order) { // this is the guy we're looking for
                        val temp = array[index1]
                        array[index1] = array[j]
                        array[j] = temp
                        changeCount++
                        break
                    }
                }

                index1++
            }
        }

        return array
    }
}