package solutions.leetcode.medium

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
class BinaryTreeLevelOrderTraversal {

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

        val list = levelOrder(node3)
        assertEquals(3, list.size)
        assertEquals(1, list[0].size)
        assertEquals(2, list[1].size)
        assertEquals(2, list[2].size)
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun levelOrder(root: TreeNode?): List<List<Int>> {

        if (root == null) return listOf()
        val output = mutableListOf<MutableList<Int>>()

        var queue = LinkedList<TreeNode>()
        queue.add(root)

        while (queue.isNotEmpty()) {

            val list = mutableListOf<Int>()

            val queueB = LinkedList<TreeNode>()
            while (queue.isNotEmpty()) {
                val poll = queue.poll()!!
                list.add(poll.`val`)
                poll.left?.let { queueB.add(it) }
                poll.right?.let { queueB.add(it) }
            }

            output.add(list)
            queue = queueB
        }

        return output
    }
}