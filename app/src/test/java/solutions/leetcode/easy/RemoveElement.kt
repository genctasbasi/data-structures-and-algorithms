package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/remove-element/
 */
class RemoveElement {

    @Test
    fun test() {
        val result = removeElement(intArrayOf(3, 2, 2, 3), 3)
        Assert.assertEquals(2, result)
    }

    @Test
    fun test2() {
        val result = removeElement(intArrayOf(0, 1, 2, 2, 3, 0, 4, 2), 2)
        Assert.assertEquals(5, result)
    }

    @Test
    fun test3() {
        val result = removeElement(intArrayOf(0, 1, 2, 2, 3, 0, 4, 2), 99)
        Assert.assertEquals(8, result)
    }

    @Test
    fun test4() {
        val result = removeElement(intArrayOf(0, 1, 2, 2, 3, 0, 4, 2), 3)
        Assert.assertEquals(7, result)
    }

    @Test
    fun test5() {
        val result = removeElement(intArrayOf(0), 0)
        Assert.assertEquals(0, result)
    }

    @Test
    fun test6() {
        val result = removeElement(intArrayOf(0, 1), 0)
        Assert.assertEquals(1, result)
    }

    @Test
    fun test7() {
        val result = removeElement(intArrayOf(3, 3), 3)
        Assert.assertEquals(0, result)
    }

    @Test
    fun test8() {
        val result = removeElement(intArrayOf(3, 3, 1), 3)
        Assert.assertEquals(1, result)
    }

    @Test
    fun test9() {
        val result = removeElement(intArrayOf(2), 3)
        Assert.assertEquals(1, result)
    }

    fun removeElement(nums: IntArray, `val`: Int): Int {

        if (nums.size == 1 && nums[0] == `val`) return 0

        var removeIndex = -1
        var pointer = 0

        while (pointer <= nums.lastIndex) {

            val item = nums[pointer]

            if (item == `val`) {
                if (removeIndex == -1) {    // we already have one to replace
                    removeIndex = pointer
                }

            } else {
                if (removeIndex != -1) {
                    // swap
                    nums[removeIndex] = item
                    nums[pointer] = `val`

                    // reset pointer
                    pointer = removeIndex + 1
                    removeIndex = -1
                    continue
                }
            }

            pointer++
        }

        val firstIndex = nums.indexOfFirst { it == `val` }
        return if (firstIndex == -1) nums.size else firstIndex
    }
}