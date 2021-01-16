package solutions.hackerRank.algorithms

import org.junit.Assert
import org.junit.Test

/**
 * https://www.hackerrank.com/challenges/minimum-distances/problem
 */
class MinimumDistances {

    @Test
    fun test() {
        val result = minimumDistances(arrayOf(3, 2, 1, 2, 3))
        Assert.assertEquals(2, result)
    }

    @Test
    fun test2() {
        val result = minimumDistances(arrayOf(1, 2, 3, 4, 10))
        Assert.assertEquals(-1, result)
    }

    fun minimumDistances(a: Array<Int>): Int {
        val map = mutableMapOf<Int, MutableList<Int>>()

        a.forEachIndexed { index, it ->
            if (map[it] == null) {
                map[it] = mutableListOf(index)
            } else map[it]?.add(index)
        }

        var minDistance = Int.MAX_VALUE

        map.values.forEach {
            if (it.size == 2) {   // pair
                if (it[1] - it[0] < minDistance)
                    minDistance = it[1] - it[0]

            }
        }

        return if(minDistance == Int.MAX_VALUE) -1 else minDistance
    }

}