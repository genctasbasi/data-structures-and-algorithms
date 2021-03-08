package solutions.leetcode.medium

import org.junit.Assert
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/binary-tree-vertical-order-traversal/submissions/
 */
class BinaryTreeVerticalOrderTraversal {

    @Test
    fun test1() {

        val node3 = TreeNode(3)
        val node9 = TreeNode(9)
        val node8 = TreeNode(8)
        val node4 = TreeNode(4)
        val node0 = TreeNode(0)
        val node1 = TreeNode(1)
        val node7 = TreeNode(7)
        val node5 = TreeNode(5)
        val node2 = TreeNode(2)

        node3.left = node9
        node3.right = node8

        node9.left = node4
        node9.right = node0

        node8.left = node1
        node8.right = node7

        node1.left = node5

        node0.right = node2

        val result = verticalOrder(node3)
        Assert.assertEquals(
            listOf(listOf(4), listOf(9, 5), listOf(3, 0, 1), listOf(8, 2), listOf(7)),
            result
        )
    }

    @Test
    fun test2() {

        val node1 = TreeNode(1)
        val node2 = TreeNode(2)
        val node3 = TreeNode(3)
        val node4 = TreeNode(4)
        val node5 = TreeNode(5)
        val node6 = TreeNode(6)
        val node7 = TreeNode(7)

        node1.left = node2
        node1.right = node3

        node2.left = node4
        node2.right = node6

        node3.left = node5
        node3.right = node7

        val result = verticalOrder(node1)
        Assert.assertEquals(
            listOf(listOf(4), listOf(2), listOf(1, 5, 6), listOf(3), listOf(7)),
            result
        )
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    class TreeNodeC(val node: TreeNode, val column: Int)

    fun verticalOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return listOf()
        val columnMap = bfs(TreeNodeC(root, 0))
        return columnMap.toSortedMap(compareBy { it }).values.toList()
    }

    private fun bfs(root: TreeNodeC): HashMap<Int, MutableList<Int>> {

        val columnMap = hashMapOf<Int, MutableList<Int>>()
        val q = LinkedList<TreeNodeC?>()
        q.add(root)

        while (q.isNotEmpty()) {

            val node = q.pop() ?: continue
            node.node.left?.let { q.add(TreeNodeC(it, node.column - 1)) }
            node.node.right?.let { q.add(TreeNodeC(it, node.column + 1)) }

            if (columnMap[node.column] == null) columnMap[node.column] = mutableListOf()
            columnMap[node.column]!!.add(node.node.`val`)
        }

        return columnMap
    }
}