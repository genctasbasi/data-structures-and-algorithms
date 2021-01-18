package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 */
class RemoveDuplicatesFromSortedArray {

    @Test
    fun test() {
        val result = removeDuplicates(intArrayOf(1, 1, 2))
        Assert.assertEquals(2, result)
    }

    @Test
    fun test2() {
        val result = removeDuplicates(intArrayOf(0, 0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4))
        Assert.assertEquals(5, result)
    }

    @Test
    fun test3() {
        val result = removeDuplicates(intArrayOf(0, 1, 2, 3, 3, 4, 4, 5))
        Assert.assertEquals(5, result)
    }

    @Test
    fun test4() {
        val result = removeDuplicates(intArrayOf(-3, -1, -1, 0, 0, 0, 0, 0, 2))
        Assert.assertEquals(4, result)
    }

    fun removeDuplicates(nums: IntArray): Int {

        if (nums.isEmpty()) return 0
        if (nums.size == 1) return 1

        var lastSeen = Int.MIN_VALUE
        var removeIndex = Int.MIN_VALUE
        var pointer = 0
        var cleanIndex = Int.MIN_VALUE

        while (pointer <= nums.lastIndex) {

            val item = nums[pointer]

            if (lastSeen == Int.MIN_VALUE) {
                lastSeen = item
                removeIndex = pointer + 1
                cleanIndex = pointer

            } else {
                // fw to find another one
                if (item != lastSeen) {

                    if (removeIndex == pointer) {
                        removeIndex++
                        cleanIndex++
                        lastSeen = item
                    } else {
                        val temp = nums[removeIndex]
                        nums[removeIndex] = nums[pointer]
                        lastSeen = nums[pointer]
                        nums[pointer] = temp
                        cleanIndex++
                        removeIndex = cleanIndex + 1
                    }
                }
            }

            pointer++
        }

        return cleanIndex + 1
    }
}