package solutions.algoexpert.hard

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Shift%20Linked%20List
 */
class ShiftLinkedList {

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

        val shift = shiftLinkedList(node0, -1)
        assertEquals(node4, shift)
    }

    open class LinkedList(value: Int) {
        var value = value
        var next: LinkedList? = null
    }

    fun shiftLinkedList(head: LinkedList, k: Int): LinkedList {

        var pCount: LinkedList? = head
        var pTail: LinkedList? = head
        var pTailMinusK: LinkedList? = head

        var count = 0
        // get the size
        while (pCount != null) {
            pCount = pCount.next
            count++
        }

        var shiftRem = k.rem(count)

        if (shiftRem < 0) {
            shiftRem += count
        }

        (1..shiftRem).forEach {
            pTail = pTail?.next
        }

        while (pTail?.next != null) {
            pTail = pTail?.next
            pTailMinusK = pTailMinusK?.next
        }

        pTail?.next = head
        val newHead = pTailMinusK?.next
        pTailMinusK?.next = null

        return newHead!!
    }

}