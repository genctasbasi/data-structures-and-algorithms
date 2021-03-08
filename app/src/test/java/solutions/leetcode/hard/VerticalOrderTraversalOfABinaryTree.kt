package solutions.leetcode.hard

import org.junit.Assert
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 *
 * TODO: Question on LC requires us to take the row order into consideration but here I'm skipping
 * that as I don't think it really adds much to the question. Therefore this solution will fail for some test cases on LC.
 */
class VerticalOrderTraversalOfABinaryTree {

    @Test
    fun test() {

        val node3 = TreeNode(3)
        val node9 = TreeNode(9)
        val node20 = TreeNode(20)
        val node15 = TreeNode(15)
        val node7 = TreeNode(7)

        node3.left = node9
        node3.right = node20

        node20.left = node15
        node20.right = node7

        Assert.assertEquals(
            listOf(listOf(9), listOf(3, 15), listOf(20), listOf(7)),
            verticalTraversal(node3)
        )
    }

    @Test
    fun test1() {

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

        val result = verticalTraversal(node1)
        Assert.assertEquals(
            listOf(listOf(4), listOf(2), listOf(1, 5, 6), listOf(3), listOf(7)),
            result
        )
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun verticalTraversal(root: TreeNode?): List<List<Int>> {

        val columnMap = hashMapOf<Int, MutableList<Int>>()
        addColumn(root, 0, 0, columnMap)

        return columnMap.toSortedMap(compareBy { it }).values.toList()
    }

    private fun addColumn(
        root: TreeNode?,
        row: Int,
        column: Int,
        columnMap: HashMap<Int, MutableList<Int>>
    ) {

        if (root == null) return

        if (columnMap[column] == null) columnMap[column] = mutableListOf()
        columnMap[column]!!.add(root.`val`)
        // TODO: Honour the row number as well, maybe use a map like Key: column, Value: Map of (row, value)

        addColumn(root.left, row + 1, column - 1, columnMap)
        addColumn(root.right, row + 1, column + 1, columnMap)
    }
}