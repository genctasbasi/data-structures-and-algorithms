package solutions.algoexpert.hard

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Reverse%20Linked%20List
 */
class ReverseLinkedList {

    @Test
    fun test() {

        val node0 = LinkedList(0)
        val node1 = LinkedList(1)
        val node2 = LinkedList(2)
        val node3 = LinkedList(3)
        val node4 = LinkedList(4)
        val node5 = LinkedList(5)

        node0.next = node1
        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node5

        val head = reverseLinkedListIterative(node0)
        reverseLinkedList(node0)

        assertEquals(node5, newHead)
    }

    open class LinkedList(value: Int) {
        var value = value
        var next: LinkedList? = null
    }

    var newHead: LinkedList? = null
    fun reverseLinkedList(node: LinkedList): LinkedList {

        if (node.next == null) {
            newHead = node
            return node
        }

        val nextNode = reverseLinkedList(node.next!!)
        nextNode.next = node
        node.next = null
        return node
    }

    fun reverseLinkedListIterative(node: LinkedList): LinkedList {

        if (node.next == null) return node

        var prev: LinkedList? = null
        var current: LinkedList? = node

        while (current != null) {
            val next = current.next
            current.next = prev
            prev = current
            current = next
        }

        return prev!!
    }

}