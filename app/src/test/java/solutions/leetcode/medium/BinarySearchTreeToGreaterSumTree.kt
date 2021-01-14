package solutions.leetcode.medium

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
        Assert.assertEquals(30, result?.`val`)
        Assert.assertEquals(36, result?.left?.`val`)
        Assert.assertEquals(36, result?.left?.left?.`val`)
        Assert.assertEquals(35, result?.left?.right?.`val`)
        Assert.assertEquals(33, result?.left?.right?.right?.`val`)

        Assert.assertEquals(21, result?.right?.`val`)
        Assert.assertEquals(26, result?.right?.left?.`val`)
        Assert.assertEquals(15, result?.right?.right?.`val`)
        Assert.assertEquals(8, result?.right?.right?.right?.`val`)
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun bstToGst(root: TreeNode?): TreeNode? {


        return null
    }
}