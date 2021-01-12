package solutions.algoexpert.hard

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Find%20Loop
 */
class FindLoop {

    @Test
    fun test() {

        val node0 = LinkedList(0)
        val node1 = LinkedList(1)
        val node2 = LinkedList(2)
        val node3 = LinkedList(3)
        val node4 = LinkedList(4)
        val node5 = LinkedList(5)
        val node6 = LinkedList(6)
        val node7 = LinkedList(7)
        val node8 = LinkedList(8)
        val node9 = LinkedList(9)

        node0.next = node1
        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node5
        node5.next = node6
        node6.next = node7
        node7.next = node8
        node8.next = node9
        node9.next = node0

        val loopNode = findLoop(node0)
        assertEquals(node4, loopNode)
    }

    open class LinkedList(value: Int) {
        var value = value
        var isVisited = false
        var next: LinkedList? = null
    }

    fun findLoop(head: LinkedList?): LinkedList? {

        if (head == null) return null

        var pointer1 = head
        var pointer2 = head
        var started = false
        while (pointer1 != null && pointer2 != null) {

            if (started && pointer1 == pointer2) {   // assuming values are unique
                break
            }

            started = true
            pointer1 = pointer1.next
            pointer2 = pointer2.next?.next
        }

        // so pointer1 & pointer2 are pointing to same node now.
        // start another pointer from head and move forward until it meets with pointer1 - that new meeting point will be
        // the beginning of the loop

        pointer1 = head

        while (pointer1 != pointer2) {
            pointer1 = pointer1?.next
            pointer2 = pointer2?.next
        }

        return pointer1
    }

    fun findLoop2(head: LinkedList?): LinkedList? {

        var node = head
        while (node?.next != null) {
            if (node.isVisited) return node
            node.isVisited = true
            node = node.next
        }

        return null
    }

}