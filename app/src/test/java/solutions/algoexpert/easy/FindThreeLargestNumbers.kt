package solutions.algoexpert.easy

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import solutions.algoexpert._utils.BinaryTree
import solutions.algoexpert._utils.Helper

/**
 * https://www.algoexpert.io/questions/Find%20Three%20Largest%20Numbers
 */
class FindThreeLargestNumbers {

    lateinit var binaryTree: BinaryTree

    @Before
    fun setup() {
        binaryTree = Helper.buildBinaryTree()
    }

    @Test
    fun test() {
        assertEquals(
            listOf(18, 141, 541),
            findThreeLargestNumbers(listOf(141, 1, 17, -7, -17, -27, 18, 541, 8, 7, 7))
        )
    }

    fun findThreeLargestNumbers(array: List<Int>): List<Int> {

        var m1 = Int.MIN_VALUE
        var m2 = Int.MIN_VALUE
        var m3 = Int.MIN_VALUE

        array.forEach {

            when {
                it > m3 -> {
                    m1 = m2
                    m2 = m3
                    m3 = it
                }
                it > m2 -> {
                    m1 = m2
                    m2 = it
                }
                it > m1 -> {
                    m1 = it
                }
            }
        }

        return listOf(m1, m2, m3)
    }

}