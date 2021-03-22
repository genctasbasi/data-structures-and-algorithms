package solutions.leetcode.medium

import org.junit.Test
import kotlin.random.Random

/**
 * https://leetcode.com/problems/random-pick-index/
 */
class RandomPickIndex {

    private val nums = intArrayOf(1, 2, 3, 3, 3)

    @Test
    fun test() {
        pick(3)
        // since it's random, no assertion here
    }

    fun pick(target: Int): Int {
        val indexes = mutableListOf<Int>()
        nums.forEachIndexed { index, it ->
            if (it == target) indexes.add(index)
        }

        val random = Random.nextInt(0, indexes.size)
        return indexes[random]
    }
}