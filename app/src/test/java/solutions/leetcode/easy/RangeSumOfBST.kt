package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/range-sum-of-bst/
 */
class RangeSumOfBST {

    @Test
    fun test() {
        val node10 = TreeNode(10)
        val node5 = TreeNode(5)
        val node15 = TreeNode(15)
        val node3 = TreeNode(3)
        val node7 = TreeNode(7)
        val node18 = TreeNode(18)

        node10.left = node5
        node10.right = node15

        node5.left = node3
        node5.right = node7

        node15.right = node18

        assertEquals(32, rangeSumOfBST(node10, 7, 15))
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    private fun rangeSumOfBST(node: TreeNode?, low: Int, high: Int): Int {

        if (node == null)
            return 0

        val leftSum = rangeSumOfBST(node.left, low, high)
        val rightSum = rangeSumOfBST(node.right, low, high)

        val nodeVal = if (node.`val` in (low..high)) node.`val` else 0
        return nodeVal + leftSum + rightSum
    }

}