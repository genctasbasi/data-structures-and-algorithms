package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Move%20Element%20To%20End
 */
class MoveElementToEnd {

    @Test
    fun test() {

        val list1 = mutableListOf(2, 1, 2, 2, 2, 3, 4, 2)
        val sorted = moveElementToEnd(list1, 2)

        Assert.assertEquals(
            listOf(4, 1, 3, 2, 2, 2, 2, 2),
            sorted
        )
    }

    fun moveElementToEnd(array: MutableList<Int>, toMove: Int): List<Int> {

        var indexLeft = 0
        var indexRight = array.size - 1

        while (indexLeft < indexRight) {

            val numberLeft = array[indexLeft]
            val numberRight = array[indexRight]

            if (numberLeft == toMove) {  // find the first number from the end that isn't toMove

                if (numberRight == toMove) {  // we don't want this
                    indexRight--
                } else {  // that's the one to swap
                    array[indexLeft] = numberRight
                    array[indexRight] = toMove

                    indexLeft++
                    indexRight--
                }
            } else
                indexLeft++
        }

        return array
    }
}