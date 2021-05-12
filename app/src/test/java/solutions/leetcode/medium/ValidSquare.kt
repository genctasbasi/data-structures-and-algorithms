package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/valid-square/
 */
class ValidSquare {

    @Test
    fun test() {
        val result = validSquare(
            intArrayOf(0, 0),
            intArrayOf(0, 1),
            intArrayOf(1, 1),
            intArrayOf(1, 0)
        )

        assertEquals(true, result)
    }

    fun validSquare(p1: IntArray, p2: IntArray, p3: IntArray, p4: IntArray): Boolean {
        val distances: MutableSet<Double> = HashSet()
        distances.add(distance(p1, p2))
        distances.add(distance(p1, p3))
        distances.add(distance(p1, p4))
        distances.add(distance(p2, p3))
        distances.add(distance(p2, p4))
        distances.add(distance(p3, p4))
        return if (distances.contains(0.0)) {
            false
        } else distances.size == 2
    }

    fun distance(p1: IntArray, p2: IntArray): Double {
        val x = (p2[0] - p1[0]) * (p2[0] - p1[0])
        val y = (p2[1] - p1[1]) * (p2[1] - p1[1])
        return Math.sqrt(x + y.toDouble())
    }
}