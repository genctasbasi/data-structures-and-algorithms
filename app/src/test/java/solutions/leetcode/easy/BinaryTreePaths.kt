package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/binary-tree-paths/
 */
class BinaryTreePaths {

    @Test
    fun test() {
        val node1 = TreeNode(1)
        val node2 = TreeNode(2)
        val node3 = TreeNode(3)
        val node5 = TreeNode(5)
        val node7 = TreeNode(7)

        node1.left = node2
        node1.right = node3

        node2.left = node7
        node2.right = node5

        Assert.assertEquals(listOf("1->2->5", "1->3"), binaryTreePaths(node1))
    }

    @Test
    fun test1() {
        val node1 = TreeNode(1)
        Assert.assertEquals(listOf("1"), binaryTreePaths(node1))
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun binaryTreePaths(root: TreeNode?): List<String> {
        if (root == null) return listOf()
        val lists = getPaths(root)
        return lists ?: emptyList()
    }

    private fun getPaths(node: TreeNode?): List<String>? {
        if (node == null) return null

        if (node.left == null && node.right == null) {
            return listOf(node.`val`.toString())
        }

        val pathsLeft = getPaths(node.left)
        val pathsRight = getPaths(node.right)

        val listLeft = pathsLeft?.map { "${node.`val`}->$it" }
        val listRight = pathsRight?.map { "${node.`val`}->$it" }

        return (listLeft ?: listOf()) + (listRight ?: listOf())
    }

}