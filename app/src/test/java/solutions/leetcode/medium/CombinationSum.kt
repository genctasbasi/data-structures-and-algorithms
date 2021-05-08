package solutions.leetcode.medium

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/combination-sum/
 */
class CombinationSum {

    @Test
    fun test() {
        val result = combinationSum(intArrayOf(2, 3, 6, 7), 7).toTypedArray()
        assertArrayEquals(arrayOf(intArrayOf(2, 2, 3), intArrayOf(7)), result)
    }

    val output = mutableListOf<MutableList<Int>>()
    fun combinationSum(candidates: IntArray, target: Int): List<List<Int>> {

        backtracking(candidates, target, 0, mutableListOf())
        return output
    }

    fun backtracking(
        candidates: IntArray,
        target: Int,
        sum: Int,
        path: MutableList<Int>
    ) {
        if (sum == target) {
            val newPath = mutableListOf<Int>()
            newPath.addAll(path)
            output.add(newPath)
            return
        }

        if (sum > target) return

        candidates.forEach {
            path.add(it)
            backtracking(candidates, target, sum + it, path)
            path.removeAt(path.lastIndex)
        }
    }
}