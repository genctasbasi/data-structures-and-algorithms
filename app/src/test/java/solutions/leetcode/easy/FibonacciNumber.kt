package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/fibonacci-number/
 */
class FibonacciNumber {

    @Test
    fun test() {
        val result = fib(7)
        Assert.assertEquals(3, result)
    }

    fun fib(n: Int): Int {

        if (n == 0) return 0
        if (n == 1 || n == 2) return 1

        var prev = 1
        var prevPrev = 1
        var index = 3
        var fibo = prev + prevPrev

        while (index < n) {
            fibo = prev + prevPrev
            prevPrev = prev
            prev = fibo
            index++
        }

        return prev + prevPrev
    }

}