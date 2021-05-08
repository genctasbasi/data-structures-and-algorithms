package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/sqrtx/
 */
class SqrtX {

    @Test
    fun test() {
        assertEquals(2, mySqrt(4))
    }

    @Test
    fun test1() {
        assertEquals(2, mySqrt(8))
    }

    @Test
    fun test2() {
        assertEquals(11, mySqrt(121))
    }

    @Test
    fun test3() {
        assertEquals(11, mySqrt(122))
    }

    @Test
    fun test4() {
        assertEquals(2, mySqrt(5))
    }

    fun mySqrt(x: Int): Int {
        if (x < 2) return x
        if (x < 4) return 1
        if (x < 9) return 2
        return rec(x, 0, x / 2)
    }

    private fun rec(x: Int, start: Int, end: Int): Int {

        if (start > end) return start
        if (end - start == 1) {
            val endResult = end * end
            return if (endResult == x) end else start
        }

        val mid = (start + end) / 2
        val num: Long = mid.toLong() * mid
        return if (num > x) {
            rec(x, start, mid)
        } else {
            rec(x, mid, end)
        }
    }

}