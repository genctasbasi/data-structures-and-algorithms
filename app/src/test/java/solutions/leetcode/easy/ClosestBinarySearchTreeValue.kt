package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/closest-binary-search-tree-value/
 */
class ClosestBinarySearchTreeValue {

    @Test
    fun test1() {
        val node4 = TreeNode(4)
        val node2 = TreeNode(2)
        val node5 = TreeNode(5)
        val node1 = TreeNode(1)
        val node3 = TreeNode(3)

        node4.left = node2
        node4.right = node5
        node2.left = node1
        node2.right = node3

        Assert.assertEquals(4, closestValue(node4, 3.714286))
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    var closest = Double.MAX_VALUE
    var closestNode: Int? = null

    fun closestValue(root: TreeNode?, target: Double): Int {
        helper(root, target)
        return closestNode ?: 0
    }

    fun helper(root: TreeNode?, target: Double) {

        if (root == null) return

        val diff: Double = Math.abs(root.`val` - target)

        helper(root.left, target)
        helper(root.right, target)

        if (diff < closest) {
            closest = diff
            closestNode = root.`val`
        }
    }

}