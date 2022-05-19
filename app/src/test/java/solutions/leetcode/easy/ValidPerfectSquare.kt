package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/valid-perfect-square/
 */
class ValidPerfectSquare {

    @Test
    fun test() {
        assertEquals(true, isPerfectSquare(9))
        assertEquals(true, isPerfectSquare(810000))
        assertEquals(false, isPerfectSquare(2147483647))
        assertEquals(true, isPerfectSquare(16))
        assertEquals(false, isPerfectSquare(21))
    }

    private fun isPerfectSquare(num: Int): Boolean {

        var count = 1
        var sq = count * count

        while (1 <= sq && sq <= num) {
            if (sq == num) return true
            count++
            sq = count * count
        }

        return false
    }
}