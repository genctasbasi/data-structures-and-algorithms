package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/divisor-game/
 */
class DivisorGame {

    @Test
    fun test() {
        val result = divisorGame(2)
        Assert.assertEquals(true, result)
    }

    fun divisorGame(n: Int): Boolean {
        return divisorGame(n, true, mutableMapOf())
    }

    fun divisorGame(n: Int, isAlice: Boolean, map: MutableMap<Int, Boolean>): Boolean {

        val divisors = getDivisors(n)

        if (divisors.isEmpty()) return !isAlice

        divisors.forEach {
            if (map[n - it] != null) return map[n - it]!!
            val result = divisorGame(n - it, !isAlice, map)
            map[n - it] = result
        }

        return map.any { it.value }
    }

    private fun getDivisors(n: Int): Set<Int> {

        val set = mutableSetOf<Int>()
        val squareRoot = Math.sqrt(n.toDouble()).toInt()

        for (i in 1..squareRoot) {
            if (n.rem(i) == 0) {
                set.add(i)
                set.add(n / i)
            }
        }

        set.remove(n)
        return set
    }
}