package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/
 */
class InsertIntoASortedCircularLinkedList {

    @Test
    fun test() {

        val node1 = Node(1)
        val node3 = Node(3)
        val node4 = Node(4)

        node3.next = node4
        node4.next = node1
        node1.next = node3

        val head = insert(node3, -10)
        assertEquals(2, head?.next?.next?.`val`)
    }

    @Test
    fun test2() {

        val node1 = Node(1)
        val node3 = Node(3)
        val node3_2 = Node(3)

        node1.next = node3
        node3.next = node3_2
        node3_2.next = node1

        val head = insert(node1, 4)
        assertEquals(4, head?.next?.next?.`val`)
    }

    @Test
    fun test3() {

        val node3 = Node(3)
        val node3_2 = Node(3)
        val node5 = Node(5)

        node3.next = node3_2
        node3_2.next = node5
        node5.next = node3

        val head = insert(node3, 0)
        assertEquals(0, head?.next?.next?.next?.`val`)
    }

    class Node(var `val`: Int) {
        var next: Node? = null
    }

    fun insert(node: Node?, insertVal: Int): Node? {

        if (node == null) {
            val newNode = Node(insertVal)
            newNode.next = newNode
            return newNode
        }

        val startNode: Node = node
        var smallestNode: Node = node
        var biggestNode: Node = node
        var currentNode: Node = node.next!!
        var madeLoop = false
        while (true) {

            if (currentNode.`val` >= biggestNode.`val`)
                biggestNode = currentNode
            else if (currentNode.`val` < smallestNode.`val`)
                smallestNode = currentNode

            if (insertVal >= currentNode.`val` &&
                insertVal <= currentNode.next!!.`val`
            ) {
                val newNode = Node(insertVal)
                newNode.next = currentNode.next
                currentNode.next = newNode
                return startNode
            }

            if (madeLoop && currentNode == biggestNode && currentNode.next!! == smallestNode) {
                val newNode = Node(insertVal)
                newNode.next = currentNode.next
                currentNode.next = newNode
                return startNode
            }

            if (currentNode == startNode) {   // made one loop
                madeLoop = true
            }

            currentNode = currentNode.next!!
        }
    }
}