package solutions.algoexpert.medium

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Binary%20Tree%20Diameter
 */

class BinaryTreeDiameter {

    @Test
    fun test() {
        val binaryTree1 = BinaryTree(1)
        val binaryTree3 = BinaryTree(3)
        val binaryTree2 = BinaryTree(2)
        val binaryTree7 = BinaryTree(7)
        val binaryTree4 = BinaryTree(4)
        val binaryTree8 = BinaryTree(8)
        val binaryTree5 = BinaryTree(5)
        val binaryTree9 = BinaryTree(9)
        val binaryTree6 = BinaryTree(6)

        binaryTree1.left = binaryTree3
        binaryTree1.right = binaryTree2
        binaryTree3.left = binaryTree7
        binaryTree3.right = binaryTree4

        binaryTree7.left = binaryTree8
        binaryTree8.left = binaryTree9
        binaryTree4.right = binaryTree5
        binaryTree5.right = binaryTree6

        assertEquals(6, binaryTreeDiameter(binaryTree1))
    }

    open class BinaryTree(value: Int) {
        var value = value
        var left: BinaryTree? = null
        var right: BinaryTree? = null
    }

    fun binaryTreeDiameter(tree: BinaryTree?): Int {
        val nodes = binaryTreeDiameterHelper(tree).first
        return nodes -1 // edges
    }

    fun binaryTreeDiameterHelper(tree: BinaryTree?): Pair<Int, Int> {   // current max length, max of left or right

        if (tree == null) return Pair(0, 0)

        val depthLeft = binaryTreeDiameterHelper(tree.left)
        val depthRight = binaryTreeDiameterHelper(tree.right)

        val thisNodesMax = depthLeft.second + depthRight.second + 1

        val newMax = Math.max(Math.max(thisNodesMax, depthLeft.first), depthRight.first)

        return Pair(
            newMax,
            Math.max(depthLeft.first, depthRight.first) + 1 // max of routes to choose

        )
    }
}