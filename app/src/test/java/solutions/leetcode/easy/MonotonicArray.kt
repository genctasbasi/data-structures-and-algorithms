package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/monotonic-array/
 */
class MonotonicArray {

    @Test
    fun test() {
        assertEquals(true, isMonotonic(intArrayOf(1, 2, 2, 3)))
    }

    @Test
    fun test1() {
        assertEquals(true, isMonotonic(intArrayOf(6, 5, 4, 4)))
    }

    @Test
    fun test2() {
        assertEquals(false, isMonotonic(intArrayOf(6, 5, 4, 4, 5)))
    }

    @Test
    fun test3() {
        assertEquals(true, isMonotonic(intArrayOf(1, 1, 1, 1, 1, 1, 2)))
    }

    @Test
    fun test4() {
        assertEquals(true, isMonotonic(intArrayOf(1, 1, 1, 1, 1, 1, 0)))
    }

    @Test
    fun test5() {
        assertEquals(false, isMonotonic(intArrayOf(1, 1, 1, 1, 1, 1, 2, 1)))
    }

    fun isMonotonic(A: IntArray): Boolean {
        if (A.size < 2) return true

        var increasing: Boolean? = null
        var p = 1

        while (p <= A.lastIndex) {

            val num = A[p]
            val prev = A[p - 1]

            if (increasing == null && num != prev) increasing = num > prev

            increasing?.let {
                if (it && A[p] < A[p - 1]) return false
                if (!it && A[p] > A[p - 1]) return false
            }

            p++
        }

        return true
    }
}