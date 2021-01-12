package solutions.algoexpert.medium

import solutions.algoexpert._utils.BST
import solutions.algoexpert._utils.Helper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Validate%20BST
 * O(n) time complexity
 * O(d) space complexity, where d is the dept of the tree
 *
 * Worst case: O(n) time and O(n) space, in case of single branch.
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
        return validateBstHelper(tree, Int.MIN_VALUE, Int.MAX_VALUE)
    }

    private fun validateBstHelper(tree: BST?, min: Int, max: Int): Boolean {

        if (tree == null) return true

        if (tree.value < min || tree.value > max) return false

        val isLeftValid = validateBstHelper(tree.left, min, tree.value - 1)
        val isRightValid = validateBstHelper(tree.right, tree.value, max)

        return isLeftValid && isRightValid
    }

}