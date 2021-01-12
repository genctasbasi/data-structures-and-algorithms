package solutions.algoexpert.medium

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Remove%20Kth%20Node%20From%20End
 */
class RemoveKthNodeFromEnd {

    private lateinit var node1: LinkedList

    @Before
    fun setup() {
        node1 = LinkedList(1)
        node1.next = LinkedList(2)
        node1.next?.next = LinkedList(3)
        node1.next?.next?.next = LinkedList(4)
        node1.next?.next?.next?.next = LinkedList(5)
        node1.next?.next?.next?.next?.next = LinkedList(6)
        node1.next?.next?.next?.next?.next?.next = LinkedList(7)
        node1.next?.next?.next?.next?.next?.next?.next = LinkedList(8)
        node1.next?.next?.next?.next?.next?.next?.next?.next = LinkedList(9)
        node1.next?.next?.next?.next?.next?.next?.next?.next?.next = LinkedList(10)
    }

    @Test
    fun testCase1() {
        removeKthNodeFromEnd(node1, 4)
        assertEquals(node1.next?.next?.next?.next?.next?.value, 7)  // 6 is removed
    }

    @Test
    fun testCase10() {
        removeKthNodeFromEnd(node1, 10)
        assertEquals(node1.next?.next?.next?.next?.next?.next?.value, 8)  // 6 is removed
    }

    open class LinkedList(value: Int) {
        var value = value
        var next: LinkedList? = null
    }

    fun removeKthNodeFromEnd(head: LinkedList, k: Int) {
        // set a runner to get the size
        var size = 1
        var node: LinkedList? = head
        while (node?.next != null) {
            size++
            node = node.next
        }

        val nodeIndexToFind = size - k

        if (nodeIndexToFind == 0) {
            head.value = head.next?.value ?: 0
            head.next = head.next?.next
            return
        }

        // if(nodeIndexToFind == 0) nodeIndexToFind = 1
        var index = 0
        node = head
        while (index <= nodeIndexToFind) {

            if (index == nodeIndexToFind - 1 && node != null) {
                node.next = node.next?.next
                return
            }

            index++
            node = node?.next
        }
    }

}