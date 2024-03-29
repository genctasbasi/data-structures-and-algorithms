package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/powx-n/
 *
 * Well that's obviously a O(n) for the worst case. However it can be O(log(n)) if
 * once we find how many halves we need to have.
 */
class PowXN {

    @Test
    fun test() {
        val result = myPow(2.0, 11)
        assertEquals(2048.0, result)
    }

    @Test
    fun test2() {
        val result = myPow(8.95371, -1)
        assertEquals(0.11168554710840535, result)
    }

    fun myPow(x: Double, n: Int): Double {

        if (n == 0) return 1.0
        if (x == 1.0) return 1.0

        val nCalc = if (n < 0) n * -1 else n
        val sum = myPowHelper(x, nCalc)

        return if (n < 0) 1.div(sum) else sum
    }

    private fun myPowHelper(x: Double, n: Int): Double {

        if (n == 0) return 1.0  // base case

        val half = myPowHelper(x, n / 2)

        return if (n.rem(2) == 0) {
            half * half
        } else {
            half * half * x
        }
    }

    fun `myPow O(n)`(x: Double, n: Int): Double {

        if (n == 0 || x == 1.0) return 1.0
        if (x == -1.0) return if (n.rem(2) == 0) 1.0 else -1.0
        if (n == Int.MIN_VALUE) return 0.0

        var sum = 1.0
        val limit = if (n < 0) n * -1 else n
        val nHalf = n / 2

        (1..limit).forEachIndexed { index, _ ->

            if (n > 0 && index == nHalf) {

                var result = sum * sum
                if (n.rem(2) == 1) result *= x
                return if (n < 0) 1.div(result) else result
            }

            sum *= x
        }

        return if (n < 0) 1.div(sum) else sum
    }
}