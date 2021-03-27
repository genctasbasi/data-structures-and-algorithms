package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/largest-bst-subtree/
 */
class LargestBSTSubtree {

    @Test
    fun test() {

        val node10 = TreeNode(10)
        val node5 = TreeNode(5)
        val node15 = TreeNode(15)
        val node1 = TreeNode(1)
        val node8 = TreeNode(8)
        val node7 = TreeNode(7)

        node10.left = node5
        node10.right = node15

        node5.left = node1
        node5.right = node8

        node15.right = node7

        val size = largestBSTSubtree(node10)

        assertEquals(3, size)
    }

    @Test
    fun test1() {
        val node1 = TreeNode(1)
        val size = largestBSTSubtree(node1)
        assertEquals(1, size)
    }

    @Test
    fun test2() {
        val node1 = TreeNode(1)
        val node2 = TreeNode(2)

        node1.right = node2
        val size = largestBSTSubtree(node1)
        assertEquals(2, size)
    }

    @Test
    fun test3() {
        val node1 = TreeNode(1)
        val node2 = TreeNode(2)
        val node3 = TreeNode(3)
        val node4 = TreeNode(4)

        node3.left = node2
        node3.right = node4
        node4.left = node1

        val size = largestBSTSubtree(node3)
        assertEquals(2, size)
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    var max = 0

    fun largestBSTSubtree(root: TreeNode?): Int {
        isBST(root)
        return max
    }

    class BSTSize(val isBST: Boolean, val size: Int, val min: Int, val max: Int)

    fun isBST(node: TreeNode?): BSTSize {
        if (node == null) return BSTSize(true, 0, Int.MAX_VALUE, Int.MIN_VALUE)

        val left = isBST(node.left)
        val right = isBST(node.right)

        val isBST = left.isBST && right.isBST
                && node.`val` > left.max
                && node.`val` < right.min

        val size = left.size + right.size + 1

        if (isBST) {
            max = Math.max(max, size)
        }

        val min = listOf(
            node.`val`,
            left.min,
            right.min
        ).min()!!

        val max = listOf(
            node.`val`,
            left.max,
            right.max
        ).max()!!

        return BSTSize(isBST, size, min, max)
    }
}