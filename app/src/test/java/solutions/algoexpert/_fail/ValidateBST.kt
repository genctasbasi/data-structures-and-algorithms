package solutions.algoexpert._fail

import solutions.algoexpert._utils.BST
import solutions.algoexpert._utils.Helper
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Validate%20BST
 */
class ValidateBST {

    lateinit var validBST: BST
    lateinit var invalidBST: BST

    @Before
    fun setup() {
        validBST = Helper.buildBST()
        invalidBST = Helper.buildInvalidBST()
    }

    @Test
    fun test() {
        assertEquals(true, validateBst(validBST))
        assertEquals(false, validateBst(invalidBST))
    }

    fun validateBst(tree: BST): Boolean {
        return validateBstHelper(tree)
    }

    fun validateBstHelper(tree: BST?): Boolean {

        if (tree == null) return true

        val largestLeft = getLargestChildValue(tree.left) ?: Int.MIN_VALUE
        val smallestRight = getSmallestChildValue(tree.right) ?: Int.MAX_VALUE

        return tree.value in (largestLeft + 1)..smallestRight
    }

    fun getLargestChildValue(tree: BST?): Int? {
        return getLargestChildValueHelper(tree, Int.MIN_VALUE)
    }

    fun getLargestChildValueHelper(tree: BST?, currentLargest: Int): Int? {

        if (tree == null) return currentLargest
        var localLargest = kotlin.math.max(currentLargest, tree.value)

        val leftLargest = getLargestChildValueHelper(tree.left, localLargest)
        val rightLargest = getLargestChildValueHelper(tree.right, localLargest)

        return kotlin.math.max(leftLargest ?: Int.MIN_VALUE, rightLargest ?: Int.MIN_VALUE)
    }

    fun getSmallestChildValue(tree: BST?): Int? {
        return getSmallestChildValueHelper(tree, Int.MAX_VALUE)
    }

    fun getSmallestChildValueHelper(tree: BST?, currentSmallest: Int): Int? {

        if (tree == null) return currentSmallest
        var localSmallest = kotlin.math.min(currentSmallest, tree.value)

        val leftLargest = getSmallestChildValueHelper(tree.left, localSmallest)
        val rightLargest = getSmallestChildValueHelper(tree.right, localSmallest)

        return kotlin.math.min(leftLargest ?: Int.MAX_VALUE, rightLargest ?: Int.MAX_VALUE)
    }
}