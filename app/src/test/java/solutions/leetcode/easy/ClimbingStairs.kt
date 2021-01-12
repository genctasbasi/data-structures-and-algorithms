package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/climbing-stairs/
 */
class ClimbingStairs {

    @Test
    fun test() {
        val result = climbStairs(3)
        Assert.assertEquals(3, result)
    }

    @Test
    fun test2() {
        val result = climbStairs(4)
        Assert.assertEquals(5, result)
    }

    @Test
    fun test3() {
        val result = climbStairs(5)
        Assert.assertEquals(8, result)
    }

    fun climbStairs(n: Int): Int {
        return climbStairs(n, mutableMapOf())
    }

    fun climbStairs(n: Int, map: MutableMap<Int, Int>): Int {

        if (n == 0) return 0
        if (n == 1) return 1
        if (n == 2) return 2

        val nMinus1 = if (map[n - 1] != null) map[n - 1] else {
            map[n - 1] = climbStairs(n - 1, map)
            map[n - 1]
        }

        val nMinus2 = if (map[n - 2] != null) map[n - 2] else {
            map[n - 2] = climbStairs(n - 2, map)
            map[n - 2]
        }

        return nMinus1!! + nMinus2!!
    }
}