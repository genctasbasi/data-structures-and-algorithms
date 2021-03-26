package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

/**
 * https://leetcode.com/problems/clone-graph/
 */
class CloneGraph {

    @Test
    fun test() {

        val node1 = Node(1)
        val node2 = Node(2)
        val node3 = Node(3)
        val node4 = Node(4)

        node1.neighbors = arrayListOf(node2, node4)
        node2.neighbors = arrayListOf(node1, node3)
        node3.neighbors = arrayListOf(node2, node4)
        node4.neighbors = arrayListOf(node1, node3)

        val cloned = cloneGraph(node1)

        assertEquals(1, cloned?.`val`)
        assertEquals(2, cloned?.neighbors?.size)
        assertEquals(2, cloned?.neighbors?.get(0)?.`val`)
        assertEquals(4, cloned?.neighbors?.get(1)?.`val`)
    }

    class Node(var `val`: Int) {
        var neighbors: ArrayList<Node?> = ArrayList()
    }

    fun cloneGraph(node: Node?): Node? {

        if (node == null) return null
        var head: Node? = null

        val q = LinkedList<Node>()
        q.add(node)

        val map = hashMapOf<Int, Node>()
        val visited = hashSetOf<Int>()
        while (q.isNotEmpty()) {
            val nodeOld = q.pop()
            visited.add(nodeOld.`val`)

            val nodeNew = map[nodeOld.`val`] ?: Node(nodeOld.`val`)
            if (head == null) head = nodeNew

            map[nodeNew.`val`] = nodeNew
            nodeOld.neighbors.forEach { oldNeighbour ->
                oldNeighbour?.let {
                    if (visited.contains(oldNeighbour.`val`).not() && q.contains(oldNeighbour)
                            .not()
                    ) q.add(oldNeighbour)
                    val neighbourNew = map[oldNeighbour.`val`] ?: Node(oldNeighbour.`val`)

                    map[neighbourNew.`val`] = neighbourNew
                    nodeNew.neighbors.add(neighbourNew)
                }
            }
        }

        return head
    }

}