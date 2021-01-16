package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/find-the-town-judge/
 */
class FindTheTownJudge {

    @Test
    fun test() {
        val result = findJudge(2, arrayOf(intArrayOf(1, 3), intArrayOf(2, 3)))
        Assert.assertEquals(-1, result)
    }

    @Test
    fun test1() {
        val result = findJudge(
            4,
            arrayOf(
                intArrayOf(1, 3),
                intArrayOf(1, 4),
                intArrayOf(2, 3),
                intArrayOf(2, 4),
                intArrayOf(4, 3)
            )
        )
        Assert.assertEquals(3, result)
    }

    fun findJudge(N: Int, trust: Array<IntArray>): Int {

        val trustMap = mutableMapOf<Int, Int>()

        trust.forEach {
            trustMap[it[1]] = (trustMap[it[1]] ?: 0).plus(1)
        }

        if (trustMap.values.max() != N - 1) return -1

        var max = Int.MIN_VALUE
        var key: Int? = null
        trustMap.forEach {
            if (it.value > max) {
                max = it.value
                key = it.key
            }
        }

        // check key doesn't trust anyone
        return if (trust.any { it[0] == key }) -1 else key ?: -1
    }

}