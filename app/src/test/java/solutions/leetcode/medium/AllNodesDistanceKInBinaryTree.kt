package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 */
class AllNodesDistanceKInBinaryTree {

    @Test
    fun test() {

        val node3 = TreeNode(3)
        val node5 = TreeNode(5)
        val node1 = TreeNode(1)
        val node6 = TreeNode(6)
        val node2 = TreeNode(2)
        val node0 = TreeNode(0)
        val node8 = TreeNode(8)
        val node7 = TreeNode(7)
        val node4 = TreeNode(4)

        node3.left = node5
        node3.right = node1

        node5.left = node6
        node5.right = node2

        node2.left = node7
        node2.right = node4

        node1.left = node0
        node1.right = node8

        assertEquals(listOf(7, 4, 1), distanceK(node3, node5, 2))
    }

    class TreeNode(var `val`: Int = 0) {
        var left: TreeNode? = null
        var right: TreeNode? = null
        // var parent: TreeNode? = null // do this, if allowed
    }

    fun distanceK(root: TreeNode?, target: TreeNode?, K: Int): List<Int> {

        val parents = hashMapOf<TreeNode, TreeNode?>()  // key: node, value: parent
        setParents(parents, root, null)

        val queue: Queue<TreeNode> = LinkedList<TreeNode>()
        val visited = hashSetOf<TreeNode>()

        queue.offer(target)
        visited.add(target!!)

        var distance = 0
        while (queue.isNotEmpty()) {

            // this is what we were looking for.
            // what we already have in queue are the ones we're looking for
            if (distance == K) {
                return queue.map { it.`val` }
            }

            val queueSize = queue.size
            (0 until queueSize).forEach {

                val currentNode = queue.poll()!!
                visited.add(currentNode)

                currentNode.left?.let {
                    if (visited.contains(it).not()) {
                        queue.offer(it)
                    }
                }

                currentNode.right?.let {
                    if (visited.contains(it).not()) {
                        queue.offer(it)
                    }
                }

                parents[currentNode]?.let {
                    if (visited.contains(it).not()) {
                        queue.offer(it)
                    }
                }
            }

            distance++
        }

        return listOf()
    }

    private fun setParents(
        parents: HashMap<TreeNode, TreeNode?>,
        node: TreeNode?,
        parent: TreeNode?
    ) {

        if (node == null) return

        parents[node] = parent

        setParents(parents, node.left, node)
        setParents(parents, node.right, node)
    }

}