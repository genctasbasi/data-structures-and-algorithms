package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 */
class MaximumDepthOfBinaryTree {

    @Test
    fun test1() {
        val node3 = TreeNode(3)
        val node9 = TreeNode(9)
        val node20 = TreeNode(20)
        val node15 = TreeNode(15)
        val node7 = TreeNode(7)

        node3.left = node9
        node3.right = node20
        node20.left = node15
        node20.right = node7

        assertEquals(3, maxDepth(node3))
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    var maxLevel = 1
    fun maxDepth(root: TreeNode?): Int {

        if (root == null) return 0
        getHeight(root, 1)
        return maxLevel
    }

    private fun getHeight(root: TreeNode?, level: Int) {

        if (root == null) return

        getHeight(root.left, level + 1)
        getHeight(root.right, level + 1)

        maxLevel = Math.max(maxLevel, level)
    }

}