package solutions.leetcode.medium

import junit.framework.TestCase
import org.junit.Test

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 */
class ValidateBinarySearchTree {

    @Test
    fun test() {

        val node1 = TreeNode(1)
        val node10 = TreeNode(10)
        val node_m5 = TreeNode(-5)
        val node20 = TreeNode(20)

        node1.right = node10
        node10.left = node_m5
        node10.right = node20

        val result = isValidBST(node1)
        TestCase.assertEquals(false, result)
    }

    @Test
    fun test2() {

        val node1 = TreeNode(-2147483648)
        val node2 = TreeNode(-2147483648)

        node1.left = node2

        val result = isValidBST(node1)
        TestCase.assertEquals(false, result)
    }

    @Test
    fun test3() {

        val node1 = TreeNode(10)
        val node2 = TreeNode(5)
        val node3 = TreeNode(20)
        val node4 = TreeNode(4)
        val node6 = TreeNode(6)
        val node15 = TreeNode(15)
        val node25 = TreeNode(25)

        node1.left = node2
        node1.right = node3

        node2.left = node4
        node2.right = node6

        node3.left = node15
        node3.right = node25

        val result = isValidBST(node1)
        TestCase.assertEquals(true, result)
    }

    fun isValidBST(root: TreeNode): Boolean {
        val isValidLeft = isValidBSTHelper(root.left, Long.MIN_VALUE, (root.`val`).toLong() - 1)
        val isValidRight = isValidBSTHelper(root.right, (root.`val`).toLong() + 1, Long.MAX_VALUE)

        return isValidLeft && isValidRight
    }

    fun isValidBSTHelper(root: TreeNode?, min: Long, max: Long): Boolean {

        if (root == null) return true

        val isValidLeft = isValidBSTHelper(root.left, min, (root.`val`).toLong() - 1)
        val isValidRight = isValidBSTHelper(root.right, (root.`val`).toLong() + 1, max)

        return isValidLeft && isValidRight && root.`val` >= min && root.`val` <= max
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}