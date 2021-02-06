package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Test

/**
 * WIP
 * https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
 */
class BinarySearchTreeToGreaterSumTree {

    @Test
    fun test() {

        val node4 = TreeNode(4)
        val node1 = TreeNode(1)
        val node6 = TreeNode(6)
        val node0 = TreeNode(0)
        val node2 = TreeNode(2)
        val node5 = TreeNode(5)
        val node7 = TreeNode(7)
        val node3 = TreeNode(3)
        val node8 = TreeNode(8)

        node4.left = node1
        node4.right = node6

        node1.left = node0
        node1.right = node2

        node6.left = node5
        node6.right = node7

        node2.right = node3
        node7.right = node8

        val result = bstToGst(node4)
        assertEquals(30, result?.`val`)
        assertEquals(36, result?.left?.`val`)
        assertEquals(36, result?.left?.left?.`val`)
        assertEquals(35, result?.left?.right?.`val`)
        assertEquals(33, result?.left?.right?.right?.`val`)

        assertEquals(21, result?.right?.`val`)
        assertEquals(26, result?.right?.left?.`val`)
        assertEquals(15, result?.right?.right?.`val`)
        assertEquals(8, result?.right?.right?.right?.`val`)
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun bstToGst(node: TreeNode?): TreeNode? {
        if (node == null) return node
        val parentSum = bstToGst2Helper(node.right, 0)
        node.`val` += parentSum

        bstToGst2Helper(node.left, node.`val`)
        return node
    }

    private fun bstToGst2Helper(node: TreeNode?, parentSum: Int): Int {

        if (node == null) return 0

        val sum = bstToGst2Helper(node.right, parentSum)
        val leftSum = getSum(node.left)
        node.`val` += (sum + parentSum)

        bstToGst2Helper(node.left, node.`val`)

        return node.`val` - parentSum + leftSum
    }

    private fun getSum(node: TreeNode?): Int {
        if (node == null) return 0

        val sumRight = getSum(node.right)
        val sumLeft = getSum(node.left)

        return sumRight + sumLeft + node.`val`
    }

    /**
     * This below is someone else's solution
     */

    var sum = 0

    fun bstToGstCodility(node: TreeNode?): TreeNode? {
        if (node == null) return node
        bstToGst(node.right)
        sum += node.`val`
        node.`val` = sum
        bstToGst(node.left)
        return node
    }
}