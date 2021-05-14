package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/find-duplicate-subtrees/
 */
class FindDuplicateSubtrees {

    @Test
    fun test() {
        val node1 = TreeNode(1)
        val node2 = TreeNode(2)
        val node3 = TreeNode(3)
        val node4 = TreeNode(4)
        val node2_2 = TreeNode(2)
        val node4_2 = TreeNode(4)
        val node4_3 = TreeNode(4)

        node1.left = node2
        node1.right = node3

        node2.left = node4

        node3.left = node2_2
        node3.right = node4_3

        node2_2.left = node4_2

        val result = findDuplicateSubtrees(node1)

        assertEquals(2, result.size)
        assertEquals(2, result[0]?.`val`)
        assertEquals(4, result[0]?.left?.`val`)
        assertEquals(4, result[1]?.`val`)
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    val map = hashMapOf<String, Int>()  // k: key representation of a tree v: count
    val output = mutableListOf<TreeNode>()

    fun findDuplicateSubtrees(root: TreeNode?): List<TreeNode?> {
        paths(root)
        return output
    }

    fun paths(root: TreeNode?): String {

        if (root == null) return "*"

        val pathLeft = paths(root.left)
        val pathRight = paths(root.right)

        val key = "${root.`val`}-$pathLeft-$pathRight"
        map[key] = (map[key] ?: 0) + 1
        if (map[key] ?: 0 == 2) output.add(root)

        return key
    }
}