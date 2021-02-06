package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/third-maximum-number/
 *
 * Can easily be done with sorting - O(nlog(n))
 * O(n) solution can be looping 3 times? O(3n), yielding to O(n)
 *
 * Another option could be using a max heap and polling when size becomes > 3
 */
class ThirdMaximumNumber {

    @Test
    fun test() {
        val result = thirdMax(intArrayOf(2, 2, 3, 1))
        Assert.assertEquals(1, result)
    }

    @Test
    fun test2() {
        val result = thirdMax(intArrayOf(1, 2))
        Assert.assertEquals(2, result)
    }

    @Test
    fun test3() {
        val result = thirdMax(intArrayOf(3, 2, 1))
        Assert.assertEquals(1, result)
    }

    @Test
    fun test4() {
        val result = thirdMax(intArrayOf(1, 2, -2147483648))
        Assert.assertEquals(-2147483648, result)
    }

    @Test
    fun test5() {
        val result = thirdMax(intArrayOf(1, 1, 1))
        Assert.assertEquals(-2147483648, result)
    }

    fun thirdMax(nums: IntArray): Int {

        var top1: Int? = null
        var top2: Int? = null
        var top3: Int? = null

        nums.forEach {
            if (top1 == null || it > top1!!) top1 = it
        }

        nums.forEach {
            if (it < top1!! && (top2 == null || it > top2!!)) top2 = it
        }

        if (top2 == null) return top1!!

        nums.forEach {
            if (it < top2!! && (top3 == null || it > top3!!)) top3 = it
        }

        if (top3 == null) return top1!!
        if (top2 == null) return top1!!

        return top3!!
    }
}