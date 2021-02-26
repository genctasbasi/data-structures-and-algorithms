package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/k-closest-points-to-origin/
 */
class KClosestPointsToOrigin {

    @Test
    fun test() {
        val result = kClosest(
            arrayOf(
                intArrayOf(1, 3),
                intArrayOf(0, 0),
                intArrayOf(12, -22),
                intArrayOf(-2, 2)
            ), 1
        )
        assertEquals(-2, result[0][0])
        assertEquals(2, result[0][1])
    }

    fun kClosest(points: Array<IntArray>, K: Int): Array<IntArray> {

        val output = mutableListOf<IntArray>()

        val map = hashMapOf<Int, Double>()

        points.forEachIndexed { index, it ->
            val distance =
                Math.sqrt(Math.pow(it[0].toDouble(), 2.0) + Math.pow(it[1].toDouble(), 2.0))
            map[index] = distance
        }

        val sorted = map.entries.sortedWith(compareBy { it.value })

        (0 until K).forEach {
            val index = sorted.elementAt(it)
            output.add(points[index.key])
        }

        return output.toTypedArray()
    }
}