package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/arithmetic-slices/
 */
class ArithmeticSlices {

    @Test
    fun test() {
        val result = numberOfArithmeticSlices(intArrayOf(1, 2, 3, 4, 5, 6, 7))
        assertEquals(15, result)
    }

    fun numberOfArithmeticSlices(A: IntArray): Int {

        var count = 0

        for (k in 2..A.lastIndex) {

            var p0 = 0
            var p1 = p0 + k

            while (p1 <= A.lastIndex) {
                if (isArithmetic(A, p0, p1)) count++
                p0++
                p1 = p0 + k
            }
        }

        return count
    }

    fun isArithmetic(arr: IntArray, start: Int, end: Int): Boolean {

        var prev: Int? = null
        var diff: Int? = null

        for (i in start..end) {
            if (prev == null) {
                prev = arr[i]
            } else {
                if (diff == null) {
                    diff = arr[i] - prev
                } else {
                    if (diff != (arr[i] - prev)) return false
                }

                prev = arr[i]

            }
        }

        return true
    }
}