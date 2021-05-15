package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/flip-equivalent-binary-trees/
 */
class FlipEquivalentBinaryTrees {

    @Test
    fun test() {

        val root1 = getRoot1()
        val root2 = getRoot2()

        val result = flipEquiv(root1, root2)
        assertEquals(true, result)
    }

    private fun getRoot1(): TreeNode {
        val node1 = TreeNode(1)
        val node2 = TreeNode(2)
        val node3 = TreeNode(3)
        val node4 = TreeNode(4)
        val node5 = TreeNode(5)
        val node6 = TreeNode(6)
        val node7 = TreeNode(7)
        val node8 = TreeNode(8)

        node1.left = node2
        node1.right = node3

        node2.left = node4
        node2.right = node5

        node5.left = node7
        node5.right = node8

        node3.left = node6
        return node1
    }

    private fun getRoot2(): TreeNode {
        val node1 = TreeNode(1)
        val node2 = TreeNode(2)
        val node3 = TreeNode(3)
        val node4 = TreeNode(4)
        val node5 = TreeNode(5)
        val node6 = TreeNode(6)
        val node7 = TreeNode(7)
        val node8 = TreeNode(8)

        node1.left = node3
        node1.right = node2

        node2.left = node4
        node2.right = node5

        node5.left = node8
        node5.right = node7

        node3.right = node6
        return node1
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun flipEquiv(root1: TreeNode?, root2: TreeNode?): Boolean {

        if (root1 == null && root2 == null) return true
        if (root1 == null || root2 == null) return false

        if (root1.`val` != root2.`val`) return false

        val option1 = flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)
        val option2 = flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left)

        return option1 || option2
    }

}