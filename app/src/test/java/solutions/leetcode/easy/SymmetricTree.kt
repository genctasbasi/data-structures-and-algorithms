package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/symmetric-tree/
 */
class SymmetricTree {

    @Test
    fun test1() {
        val node1 = TreeNode(1)
        val node2_1 = TreeNode(2)
        val node2_2 = TreeNode(2)
        val node3_1 = TreeNode(3)
        val node3_2 = TreeNode(3)
        val node4_1 = TreeNode(4)
        val node4_2 = TreeNode(4)

        node1.left = node2_1
        node1.right = node2_2

        node2_1.left = node3_1
        node2_1.right = node4_1

        node2_2.left = node4_2
        node2_2.right = node3_2

        assertEquals(true, isSymmetricIterative(node1))
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun isSymmetric(root: TreeNode?): Boolean {
        return isMirror(root, root)
    }

    fun isMirror(node1: TreeNode?, node2: TreeNode?): Boolean {

        if (node1 == null && node2 == null) return true
        if (node1 == null || node2 == null) return false

        return node1.`val` == node2.`val`
                && isMirror(node1.left, node2.right)
                && isMirror(node1.right, node2.left)
    }

    fun isSymmetricIterative(root: TreeNode?): Boolean {

        if (root == null) return true

        val queue: Queue<TreeNode> = LinkedList<TreeNode>()
        queue.add(root)

        while (queue.isNotEmpty()) {

            if (queue.size == 1) {
                val node = queue.poll()!!
                queue.add(node.left)
                queue.add(node.right)
                continue
            }

            val poll1 = queue.poll()
            val poll2 = queue.poll()

            if (poll1 == null && poll2 == null) continue
            if (poll1 == null || poll2 == null) return false

            if (poll1.`val` != poll2.`val`) return false

            queue.add(poll1.left)
            queue.add(poll2.right)
            queue.add(poll1.right)
            queue.add(poll2.left)
        }

        return true
    }

    fun `isSymmetric failed attempt`(root: TreeNode?): Boolean {

        if (root == null) return true
        if (root.left == null && root.right == null) return true

        var queue = LinkedList<TreeNode>()
        queue.add(root)

        while (queue.isNotEmpty()) {

            if (queue.size == 1) {
                val node = queue.remove()
                if (node.left == null) queue.add(TreeNode(-1)) else queue.add(node.left!!)
                if (node.right == null) queue.add(TreeNode(-1)) else queue.add(node.right!!)

                continue
            }

            if (queue.size.rem(2) != 0) return false

            val queueBackup = LinkedList<TreeNode>()

            while (queue.isNotEmpty()) {
                val endNode = queue.removeAt(queue.lastIndex)
                val startNode = queue.removeAt(0)

                if (startNode.`val` != endNode.`val`) return false

                if (!(startNode.left == null && startNode.right == null)) {
                    if (startNode.left == null) queueBackup.add(TreeNode(-1)) else queueBackup.add(
                        startNode.left!!
                    )
                    if (startNode.right == null) queueBackup.add(TreeNode(-1)) else queueBackup.add(
                        startNode.right!!
                    )
                }

                if (!(endNode.left == null && endNode.right == null)) {
                    if (endNode.left == null) queueBackup.add(TreeNode(-1)) else queueBackup.add(
                        endNode.left!!
                    )
                    if (endNode.right == null) queueBackup.add(TreeNode(-1)) else queueBackup.add(
                        endNode.right!!
                    )
                }
            }

            queue = queueBackup
        }

        return true
    }

}