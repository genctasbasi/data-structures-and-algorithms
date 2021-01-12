package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Spiral%20Traverse
 */
class SpiralTraverse {

    @Test
    fun test() {
        val list = listOf(
            listOf(1, 2, 3, 4),
            listOf(12, 13, 14, 5),
            listOf(11, 16, 15, 6),
            listOf(10, 9, 8, 7)
        )

        Assert.assertEquals(
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16),
            spiralTraverse(list)
        )

        val list2 = listOf(
            listOf(4, 2, 3, 6, 7, 8, 1, 9, 5, 10),
            listOf(12, 19, 15, 16, 20, 18, 13, 17, 11, 14)
        )

        Assert.assertEquals(
            listOf(4, 2, 3, 6, 7, 8, 1, 9, 5, 10, 14, 11, 17, 13, 18, 20, 16, 15, 19, 12),
            spiralTraverse(list2)
        )

        val list3 = listOf(
            listOf(1, 2, 3),
            listOf(8, 9, 4),
            listOf(7, 6, 5)
        )

        Assert.assertEquals(
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
            spiralTraverse(list3)
        )

        val list4 = listOf(
            listOf(27, 12, 35, 26),
            listOf(25, 21, 94, 11),
            listOf(19, 96, 43, 56),
            listOf(55, 36, 10, 18),
            listOf(96, 83, 31, 94),
            listOf(93, 11, 90, 16)
        )

        Assert.assertEquals(
            listOf(
                27, 12, 35, 26, 11, 56, 18, 94, 16, 90, 11, 93, 96,
                55, 19, 25, 21, 94, 43, 10, 31, 83, 36, 96
            ),
            spiralTraverse(list4)
        )

        val list5 = listOf(
            listOf(1)
        )

        Assert.assertEquals(
            listOf(1),
            spiralTraverse(list5)
        )

        val list6 = listOf(
            listOf(1, 2, 3, 4),
            listOf(10, 11, 12, 5),
            listOf(9, 8, 7, 6)
        )

        Assert.assertEquals(
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12),
            spiralTraverse(list6)
        )
    }

    fun spiralTraverse(array: List<List<Int>>): List<Int> {

        val list = mutableListOf<Int>()

        val columns = array[0].size
        val rows = array.size

        var startColumn = 0
        var endColumn = columns - 1
        var startRow = 0
        var endRow = rows - 1

        while (startColumn <= endColumn && startRow <= endRow) {

            // right to left
            for (i in startColumn..endColumn) {
                list.add(array[startRow][i])
            }

            // top to bottom
            for (i in startRow + 1 until endRow) {
                list.add(array[i][endColumn])
            }

            // left to right
            if (startRow != endRow)
                for (i in endColumn downTo startColumn) {
                    list.add(array[endRow][i])
                }

            // bottom to top
            if (startColumn != endColumn)
                for (i in endRow - 1 downTo startRow + 1) {
                    list.add(array[i][startColumn])
                }

            startColumn++
            endColumn--
            startRow++
            endRow--
        }

        return list
    }
}