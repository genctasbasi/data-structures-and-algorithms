package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/path-sum/
 */
class PathSum {

    @Test
    fun test() {
        val node5 = TreeNode(5)
        val node4 = TreeNode(4)
        val node8 = TreeNode(8)
        val node11 = TreeNode(11)
        val node13 = TreeNode(13)
        val node4_2 = TreeNode(4)
        val node7 = TreeNode(7)
        val node2 = TreeNode(2)
        val node1 = TreeNode(1)

        node5.left = node4
        node5.right = node8

        node4.left = node11

        node11.left = node7
        node11.right = node2

        node5.right = node8

        node8.left = node13
        node8.right = node4_2

        node4_2.right = node1

        assertEquals(true, hasPathSum(node5, 22))
    }

    @Test
    fun test1() {
        val node1 = TreeNode(1)
        val node2 = TreeNode(2)
        val node3 = TreeNode(3)

        node1.left = node2
        node1.right = node3

        assertEquals(false, hasPathSum(node1, 5))
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
        return find(root, targetSum, 0)
    }

    fun find(node: TreeNode?, targetSum: Int, sum: Int): Boolean {
        if (node == null) return false
        val newSum = sum + node.`val`

        if (newSum == targetSum) return true

        if (find(node.left, targetSum, newSum)) return true
        if (find(node.right, targetSum, newSum)) return true

        return false
    }
}