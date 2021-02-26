package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/leftmost-column-with-at-least-a-one/
 */
class LeftmostColumnWithAtLeastAOne {

    @Test
    fun test() {
        assertEquals(0, BinaryMatrix())
    }

    class BinaryMatrix {
        fun get(row: Int, col: Int): Int {
            return if (row == 0) {
                if (col == 0) 0 else 1
            } else if (row == 1) {
                1
            } else 0
        }

        fun dimensions(): List<Int> {
            return listOf(2, 2)
        }
    }

    // just to save some calls
    val map = mutableMapOf<Pair<Int, Int>, Int>()

    fun leftMostColumnWithOne(binaryMatrix: BinaryMatrix): Int {

        val dim = binaryMatrix.dimensions()
        val rows = dim[0]
        val cols = dim[1]
        val lastColIndex = cols - 1

        var leftMostSoFar = Int.MAX_VALUE
        (0 until rows).forEach {
            // this row have at least one 1 in the columns, find the leftmost one
            if (binaryMatrix.get(it, lastColIndex) == 1) {
                val leftMostOneTheRow = getLeftMostOne(binaryMatrix, it, cols)
                if (leftMostOneTheRow == 0) return 0    // cannot get better than that
                leftMostSoFar = Math.min(leftMostSoFar, leftMostOneTheRow)
            }
        }

        return if (leftMostSoFar == Int.MAX_VALUE) -1 else leftMostSoFar
    }

    private fun getLeftMostOne(binaryMatrix: BinaryMatrix, rowIndex: Int, colCount: Int): Int {
        return getLeftMostOne(binaryMatrix, rowIndex, 0, colCount - 1)
    }

    private fun getLeftMostOne(
        binaryMatrix: BinaryMatrix,
        rowIndex: Int,
        start: Int,
        end: Int
    ): Int {

        if (start > end) return -1
        if (start == end) {
            val r = if (map[Pair(rowIndex, start)] != null) map[Pair(rowIndex, start)]!!
            else binaryMatrix.get(rowIndex, start)

            map[Pair(rowIndex, start)] = r
            return if (r == 1) start else -1
        }

        val mid = (start + end) / 2

        val result =
            if (map[Pair(rowIndex, mid)] != null) map[Pair(rowIndex, mid)]!! else binaryMatrix.get(
                rowIndex,
                mid
            )
        map[Pair(rowIndex, mid)] = result

        return if (result == 1) {    // try more left
            if (mid == 0) 0
            else {
                getLeftMostOne(binaryMatrix, rowIndex, start, mid)
            }
        } else {
            if (mid == end) return -1
            val oneRight = if (map[Pair(rowIndex, mid + 1)] != null) map[Pair(
                rowIndex,
                mid + 1
            )]!! else binaryMatrix.get(rowIndex, mid + 1)

            map[Pair(rowIndex, mid + 1)] = oneRight
            if (oneRight == 1) return mid + 1

            getLeftMostOne(binaryMatrix, rowIndex, mid + 1, end)
        }
    }

}