package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/permutations/
 */
class Permutations {

    @Test
    fun test() {
        val result = permute(intArrayOf(1, 2, 3))
        assertEquals(
            listOf(
                listOf(1, 2, 3),
                listOf(1, 3, 2),
                listOf(2, 1, 3),
                listOf(2, 3, 1),
                listOf(3, 1, 2),
                listOf(3, 2, 1)

            ), result
        )
    }

    fun permute(nums: IntArray): List<List<Int>> {

        if (nums.isEmpty()) return listOf()
        if (nums.size == 1) return listOf(listOf(nums[0]))

        val permutations = mutableListOf<List<Int>>()

        val firstChar = nums.first()
        val restOfTheList = nums.toList().subList(1, nums.lastIndex + 1)
        val permutationsForTheRest = permute(restOfTheList.toIntArray())

        permutationsForTheRest.forEach {

            it.forEachIndexed { index, i ->
                val preList = it.subList(0, index + 1)
                val postList = it.subList(index + 1, it.lastIndex + 1)
                val newList = preList + firstChar + postList
                permutations.add(newList)
            }

            permutations.add(listOf(firstChar) + it)
        }

        return permutations
    }
}