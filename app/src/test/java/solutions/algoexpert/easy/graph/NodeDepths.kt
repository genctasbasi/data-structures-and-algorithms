package solutions.algoexpert.easy.graph

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import solutions.algoexpert._utils.BinaryTree
import solutions.algoexpert._utils.Helper

/**
 * https://www.algoexpert.io/questions/Node%20Depths
 */
class NodeDepths {

    lateinit var binaryTree: BinaryTree

    @Before
    fun setup() {
        binaryTree = Helper.buildBinaryTree()
        binaryTree.left?.right?.left = null // remove node 10
    }

    @Test
    fun test() {
        assertEquals(16, nodeDepths(binaryTree))
    }

    fun nodeDepths(root: BinaryTree): Int {
        // Write your code here.
        return nodeDepthsWithSums(root, -1)
    }

    private fun nodeDepthsWithSums(root: BinaryTree?, sum: Int): Int {

        if (root == null) return 0

        val newSum = sum + 1
        val leftSum = nodeDepthsWithSums(root.left, newSum)
        val rightSum = nodeDepthsWithSums(root.right, newSum)

        return leftSum + rightSum + newSum
    }
}