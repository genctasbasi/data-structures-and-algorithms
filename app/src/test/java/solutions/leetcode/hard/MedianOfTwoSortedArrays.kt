package solutions.leetcode.hard

import junit.framework.TestCase.assertEquals
import org.junit.Test

class MedianOfTwoSortedArrays {

    @Test
    fun test1() {
        val result = findMedianSortedArrays(intArrayOf(1, 3), intArrayOf(2))
        assertEquals(2.0, result)
    }

    @Test
    fun test2() {
        val result = findMedianSortedArrays(intArrayOf(1, 2), intArrayOf(3, 4))
        assertEquals(2.5, result)
    }

    @Test
    fun test3() {
        val result = findMedianSortedArrays(intArrayOf(0, 0), intArrayOf(0, 0))
        assertEquals(0.0, result)
    }

    @Test
    fun test4() {
        val result = findMedianSortedArrays(intArrayOf(), intArrayOf(1))
        assertEquals(1.0, result)
    }

    @Test
    fun test5() {
        val result = findMedianSortedArrays(intArrayOf(2), intArrayOf())
        assertEquals(2.0, result)
    }

    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {

        val size1 = nums1.size
        val size2 = nums2.size

        if (size1 == 1 && size2 == 1) return (nums1[0].toDouble() + nums2[0]) / 2
        if (size1 == 0 && size2 == 1) return nums2[0].toDouble()
        if (size1 == 1 && size2 == 0) return nums1[0].toDouble()

        val isOdd = (size1 + size2).rem(2) == 1

        val medianIndex = (size1 + size2) / 2
        var index = 0
        var num1Index = 0
        var num2Index = 0

        var previousNumberPicked: Int = -1
        var numberPicked: Int = 0

        while (index <= medianIndex) {

            previousNumberPicked = numberPicked

            if (size1 == 0) {
                numberPicked = nums2[num2Index]
                num2Index++
            } else if (size2 == 0) {
                numberPicked = nums1[num1Index]
                num1Index++
            } else if (

                (num1Index < size1 && num2Index < size2 && nums1[num1Index] < nums2[num2Index])
                || (num1Index < size1 && num2Index >= size2)


            ) {
                numberPicked = nums1[num1Index]
                num1Index++
            } else if (num2Index < size2) {
                numberPicked = nums2[num2Index]
                num2Index++
            }

            index++
        }

        return if (isOdd) numberPicked.toDouble() else {
            (numberPicked.toDouble() + previousNumberPicked) / 2
        }
    }
}