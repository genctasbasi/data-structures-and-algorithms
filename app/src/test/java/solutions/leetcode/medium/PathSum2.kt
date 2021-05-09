package solutions.leetcode.medium

import junit.framework.Assert.assertTrue
import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/path-sum-ii/
 */
class PathSum2 {

    @Test
    fun test() {
        val node5 = TreeNode(5)
        val node4 = TreeNode(4)
        val node8 = TreeNode(8)
        val node11 = TreeNode(11)
        val node13 = TreeNode(13)
        val node4_2 = TreeNode(4)
        val node7 = TreeNode(7)
        val node2 = TreeNode(2)
        val node1 = TreeNode(1)
        val node5_2 = TreeNode(5)

        node5.left = node4
        node5.right = node8

        node4.left = node11

        node11.left = node7
        node11.right = node2

        node5.right = node8

        node8.left = node13
        node8.right = node4_2

        node4_2.left = node5_2
        node4_2.right = node1

        val expected = listOf(
            listOf(5, 4, 11, 2),
            listOf(5, 8, 4, 5)
        ).toTypedArray()

        val pathSum = pathSum(node5, 22).toTypedArray()
        assertArrayEquals(expected, pathSum)
    }

    @Test
    fun test1() {
        val node1 = TreeNode(1)
        val node2 = TreeNode(2)
        val node3 = TreeNode(3)

        node1.left = node2
        // node1.right = node3

        val pathSum = pathSum(node1, 1).toTypedArray()
        assertTrue(pathSum.isEmpty())
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    val output = mutableListOf<MutableList<Int>>()
    fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
        if (root == null) return listOf()

        if (root.left == null && root.right == null && root.`val` == targetSum)
            return listOf(listOf(root.`val`))

        findPaths(root, targetSum, 0, mutableListOf())
        return output
    }

    fun findPaths(
        node: TreeNode?,
        targetSum: Int,
        sum: Int,
        list: MutableList<Int>
    ) {

        if (node == null) return

        val newSum = sum + node.`val`
        list.add(node.`val`)

        val isLeaf = node.left == null && node.right == null
        if (newSum == targetSum && isLeaf) {
            output.add(list)
            return
        }

        findPaths(node.left, targetSum, newSum, list.toMutableList())
        findPaths(node.right, targetSum, newSum, list.toMutableList())
        list.removeAt(list.lastIndex)
    }
}