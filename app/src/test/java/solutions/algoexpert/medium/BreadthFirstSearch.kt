package solutions.algoexpert.medium

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://www.algoexpert.io/questions/Breadth-first%20Search
 */
class BreadthFirstSearch {

    @Test
    fun testCase1() {
        val nodeA = Node("A")
        val nodeB = Node("B")
        val nodeC = Node("C")
        val nodeD = Node("D")
        val nodeE = Node("E")
        val nodeF = Node("F")
        val nodeG = Node("G")
        val nodeH = Node("H")
        val nodeI = Node("I")
        val nodeJ = Node("J")
        val nodeK = Node("K")

        nodeA.children = mutableListOf(nodeB, nodeC, nodeD)
        nodeB.children = mutableListOf(nodeE, nodeF)
        nodeF.children = mutableListOf(nodeI, nodeJ)
        nodeD.children = mutableListOf(nodeG, nodeH)
        nodeG.children = mutableListOf(nodeK)

        val list = nodeA.breadthFirstSearch()
        assertEquals(list, listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"))
    }

    class Node(name: String) {
        val name: String = name
        var children = mutableListOf<Node>()
        val list = mutableListOf<String>()

        fun breadthFirstSearch(): List<String> {

            val queue = java.util.LinkedList<Node>()
            queue.add(this)

            while (queue.isNotEmpty()) {
                val node = queue.remove()
                list.add(node.name)
                node.children.forEach {
                    queue.add(it)
                }
            }

            return list
        }
    }
}