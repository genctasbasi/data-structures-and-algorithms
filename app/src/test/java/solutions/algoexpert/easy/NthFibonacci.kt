package solutions.algoexpert.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Nth%20Fibonacci
 *
 * Complexity:
 *  getNthFib: Time: O(2^n), Space: O(n)
 *  getNthFibIterative: Time: O(n), Space: O(1)
 *  getNthFibWithMem: Time: O(n), Space: O(n)
 */
class NthFibonacci {

    @Test
    fun test() {
        assertEquals(1, getNthFib(2))
        assertEquals(701408733, getNthFib(45))
        assertEquals(701408733, getNthFibWithMem(45, mutableMapOf()))
        assertEquals(701408733, getNthFibIterative(45))
    }

    fun getNthFib(n: Int): Int {
        return getNthFibRecursive(n)
    }

    fun getNthFibRecursive(n: Int): Int {

        return when {
            n <= 0 -> -1
            n == 1 -> 0
            n == 2 -> 1
            else -> getNthFib(n - 2) + getNthFib(n - 1)
        }
    }

    fun getNthFibIterative(n: Int): Int {

        return when {
            n <= 0 -> -1
            n == 1 -> 0
            n == 2 -> 1
            else -> {
                var total = 1
                var n2 = 0
                var n1 = 1
                (3..n).forEach { _ ->
                    total = n1 + n2
                    n2 = n1
                    n1 = total
                }

                total
            }
        }
    }

    fun getNthFibWithMem(n: Int, mem: MutableMap<Int, Long>): Long {

        return when {
            n <= 0 -> -1
            n == 1 -> 0
            n == 2 -> 1
            else -> {

                val n1 = mem[n - 1]
                if (n1 == null) mem[n - 1] = getNthFibWithMem(n - 1, mem)

                val n2 = mem[n - 2]
                if (n2 == null) mem[n - 2] = getNthFibWithMem(n - 2, mem)

                return (mem[n - 1] ?: 0) + (mem[n - 2] ?: 0)
            }
        }
    }

}