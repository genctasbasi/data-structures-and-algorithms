package solutions.algoexpert.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Reconstruct%20BST
 */

class ReconstructBST {

    @Test
    fun test() {
        val root = reconstructBst(listOf(10, 4, 2, 1, 5, 17, 19, 18))
        assertEquals(4, root?.left?.value)
        assertEquals(17, root?.right?.value)
    }

    open class BST(value: Int, left: BST? = null, right: BST? = null) {
        var value = value
        var left = left
        var right = right
    }

    fun reconstructBst(preOrderTraversalValues: List<Int>): BST? {

        val root = BST(preOrderTraversalValues[0])

        (1 until preOrderTraversalValues.size).forEach {
            val number = preOrderTraversalValues[it]
            insert(root, number)
        }

        return root
    }

    fun insert(root: BST, value: Int) {

        if (value < root.value) {

            if (root.left == null) {
                root.left = BST(value)
            } else
                insert(root.left!!, value)
        } else {

            if (root.right == null) {
                root.right = BST(value)
            } else insert(root.right!!, value)
        }
    }
}