package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/2-keys-keyboard/
 */
class TwoKeysKeyboard {

    @Test
    fun test() {
        val result = minSteps(18)
        assertEquals(3, result)
    }

    fun minSteps(n: Int): Int {
        if (n == 1) return 0

        var operationCount = 0
        val divisors = mutableListOf<Int>()

        // find multipliers
        var divisor = 2
        var remainder = n
        while (remainder > 1) {
            if (remainder.rem(divisor) != 0) {
                divisor++
            } else {
                remainder = remainder.div(divisor)
                divisors.add(divisor)
                divisor = 2
            }
        }

        return if (remainder == n) { // must be a prime
            n    // that's what's needed for prime numbers
        } else {
            divisors.forEach {
                operationCount += it - 1
            }

            operationCount += 1 // for initial copying
            operationCount += divisors.size - 1 // copying for each multiplier

            operationCount
        }
    }
}