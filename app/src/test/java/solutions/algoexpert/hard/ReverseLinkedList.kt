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

        val reverse = reverseLinkedList(node0)
        assertEquals(node5, reverse)
    }

    open class LinkedList(value: Int) {
        var value = value
        var next: LinkedList? = null
    }

    fun reverseLinkedList(head: LinkedList): LinkedList {
        return head
    }


}