package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 */
class BinaryTreeRightSideView {

    @Test
    fun test() {
        val node1 = TreeNode(1)
        val node2 = TreeNode(2)
        val node3 = TreeNode(3)
        // val node4 = TreeNode(4)
        val node5 = TreeNode(5)

        node1.left = node2
        node1.right = node3

        node2.right = node5
        // node3.right = node4

        assertEquals(listOf(1, 3, 5), rightSideView(node1))
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun rightSideView(root: TreeNode?): List<Int> {
        if (root == null) return listOf()
        val map = hashMapOf<Int, Int?>()
        helper(root, 0, map)

        return map.values.filterNotNull().toList()
    }

    fun helper(node: TreeNode?, level: Int, map: HashMap<Int, Int?>) {

        if (node == null) return
        helper(node.left, level + 1, map)
        helper(node.right, level + 1, map)
        map[level] = node.`val`
    }
}