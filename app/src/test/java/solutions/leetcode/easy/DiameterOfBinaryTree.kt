package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/
 */
class DiameterOfBinaryTree {

    @Test
    fun test() {

        val node1 = TreeNode(1)
        val node2 = TreeNode(2)
        val node3 = TreeNode(3)
        val node5 = TreeNode(5)

        node3.left = node1
        node3.right = node2
        node1.right = node5

        assertEquals(3, diameterOfBinaryTree(node3))
    }

    @Test
    fun test2() {

        val node4 = TreeNode(4)
        val nodeM7 = TreeNode(-7)
        val nodeM9 = TreeNode(-9)
        val nodeM3 = TreeNode(-3)
        val nodeM1 = TreeNode(-1)

        node4.left = nodeM7
        nodeM7.right = nodeM9
        nodeM9.left = nodeM3
        nodeM3.right = nodeM1

        assertEquals(4, diameterOfBinaryTree(node4))
    }

    @Test
    fun test3() {

        val node4 = TreeNode(4)
        val node7 = TreeNode(7)
        val node3 = TreeNode(3)
        val node9 = TreeNode(9)
        val node32 = TreeNode(32)
        val node92 = TreeNode(92)
        val node72 = TreeNode(72)
        val node44 = TreeNode(44)
        val node61 = TreeNode(61)
        val node62 = TreeNode(62)
        val node63 = TreeNode(63)
        val node0 = TreeNode(0)
        val node64 = TreeNode(64)
        val node5 = TreeNode(5)
        val node93 = TreeNode(93)
        val node2 = TreeNode(2)
        val node1 = TreeNode(1)

        node4.left = node7
        node4.right = node3

        node3.right = node32
        node32.left = node44

        node3.left = node9

        node9.left = node92
        node9.right = node72

        node72.left = node61
        node72.right = node62

        node62.left = node93
        node93.left = node2

        node61.left = node5

        node92.left = node63
        node63.left = node0
        node63.right = node64
        node0.right = node1

        assertEquals(8, diameterOfBinaryTree(node4))
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun diameterOfBinaryTree(root: TreeNode?): Int {

        if (root == null) return 0
        if (root.left == null && root.right == null) return 0

        val left = diameterOfBinaryTreeHelper(root.left)
        val right = diameterOfBinaryTreeHelper(root.right)

        val leftPath = if (left == null) 0 else left.first + 1
        val rightPath = if (right == null) 0 else right.first + 1

        val rootTotal = leftPath + rightPath
        return Math.max(rootTotal, Math.max(left?.second ?: 0, right?.second ?: 0))
    }

    fun diameterOfBinaryTreeHelper(root: TreeNode?): Pair<Int, Int>? {   // longest path, best of node

        if (root == null) return null

        val left = diameterOfBinaryTreeHelper(root.left)
        val right = diameterOfBinaryTreeHelper(root.right)

        val leftPath = if (left == null) 0 else left.first + 1
        val rightPath = if (right == null) 0 else right.first + 1

        val maxTotal =
            Math.max(Math.max(leftPath + rightPath, left?.second ?: 0), right?.second ?: 0)
        return Pair(Math.max(leftPath, rightPath), maxTotal)
    }
}