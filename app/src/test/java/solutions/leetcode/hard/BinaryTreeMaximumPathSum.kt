package solutions.leetcode.hard

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * hhttps://leetcode.com/problems/binary-tree-maximum-path-sum/
 *
 */
class BinaryTreeMaximumPathSum {

    @Test
    fun test1() {

        val nodeM10 = TreeNode(-40)
        val node9 = TreeNode(9)
        val node20 = TreeNode(20)
        val node15 = TreeNode(15)
        val node7 = TreeNode(-7)

        nodeM10.left = node9
        nodeM10.right = node20
        node20.left = node15
        node20.right = node7

        assertEquals(35, maxPathSum(nodeM10))
    }

    @Test
    fun test2() {
        val node = TreeNode(-3)
        assertEquals(-3, maxPathSum(node))
    }

    @Test
    fun test3() {
        val node2 = TreeNode(2)
        val nodeM1 = TreeNode(-1)
        val nodeM2 = TreeNode(-2)

        node2.left = nodeM1
        node2.right = nodeM2

        assertEquals(2, maxPathSum(node2))
    }

    @Test
    fun test4() {
        val node1 = TreeNode(1)
        val nodeM2 = TreeNode(-2)
        val nodeM3 = TreeNode(-3)
        val node12 = TreeNode(1)
        val node3 = TreeNode(3)
        val nodeM22 = TreeNode(-2)
        val nodeM1 = TreeNode(-1)

        node1.left = nodeM2
        node1.right = nodeM3

        nodeM2.left = node12
        nodeM2.right = node3

        node12.left = nodeM1

        nodeM3.left = nodeM22

        assertEquals(3, maxPathSum(node1))
    }

    @Test
    fun test6() {
        val node5 = TreeNode(5)
        val node4 = TreeNode(4)
        val node8 = TreeNode(8)
        val node11 = TreeNode(11)
        val node13 = TreeNode(13)
        val node42 = TreeNode(4)
        val node7 = TreeNode(7)
        val node2 = TreeNode(2)
        val node1 = TreeNode(1)

        node5.left = node4
        node5.right = node8

        node4.left = node11

        node8.left = node13
        node8.right = node42

        node11.left = node7
        node11.right = node2

        node42.right = node1

        assertEquals(48, maxPathSum(node5))
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    var max = Int.MIN_VALUE // TODO try to optimize, no global vars

    fun maxPathSum(root: TreeNode?): Int {
        maxPathSumHelper(root)
        return max
    }

    fun maxPathSumHelper(root: TreeNode?): Int? {

        if (root == null) return null

        val leftSum = maxPathSumHelper(root.left)
        val rightSum = maxPathSumHelper(root.right)

        val rootTotal = (leftSum ?: 0) + (rightSum ?: 0) + root.`val`

        max =
            listOf(
                max,
                (leftSum ?: Int.MIN_VALUE),
                (rightSum ?: Int.MIN_VALUE),
                (leftSum ?: 0) + root.`val`,
                (rightSum ?: 0) + root.`val`,
                rootTotal,
                root.`val`
            ).max()
                ?: Int.MIN_VALUE

        return listOf(
            root.`val`,
            (leftSum ?: 0) + root.`val`,
            (rightSum ?: 0) + root.`val`
        ).max()
    }

}