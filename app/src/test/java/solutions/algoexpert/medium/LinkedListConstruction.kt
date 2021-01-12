package solutions.algoexpert.medium

import junit.framework.TestCase.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Linked%20List%20Construction
 *
 */
class LinkedListConstruction {

    private val linkedList = DoublyLinkedList()

    private val node1 = Node(1)
    private val node2 = Node(2)
    private val node3 = Node(3)
    private val node3_2 = Node(3)
    private val node3_3 = Node(3)
    private val node4 = Node(4)
    private val node5 = Node(5)
    private val node6 = Node(6)
    private val node7 = Node(7)

    @Test
    fun testCase1() {

        linkedList.setHead(node5)
        linkedList.setHead(node4)
        linkedList.setHead(node3)
        linkedList.setHead(node2)
        linkedList.setHead(node1)
        linkedList.setHead(node4)
        linkedList.setTail(node6)

        linkedList.insertBefore(node6, node3)
        linkedList.insertAfter(node6, node3_2)
        linkedList.insertAtPosition(1, node3_3)
        linkedList.removeNodesWithValue(3)
        linkedList.remove(node2)

        assertTrue(linkedList.containsNodeWithValue(5))
    }

    @Test
    fun testCase3() {
        linkedList.setTail(node1)
        assertTrue(linkedList.containsNodeWithValue(5))
    }

    @Test
    fun testCase4() {
        linkedList.insertAtPosition(1, node1)
        assertTrue(linkedList.containsNodeWithValue(5))
    }

    @Test
    fun testCase11() {
        linkedList.setHead(node1)
        linkedList.insertAfter(node1, node2)
        linkedList.insertAfter(node2, node3)
        linkedList.insertAfter(node3, node4)
        linkedList.setTail(node1)

        assertTrue(linkedList.containsNodeWithValue(5))
    }

    @Test
    fun testCase12() {
        linkedList.setTail(node1)
        linkedList.insertBefore(node1, node2)
        linkedList.insertBefore(node2, node3)
        linkedList.insertBefore(node3, node4)
        linkedList.setHead(node1)

        assertTrue(linkedList.containsNodeWithValue(5))
    }

    @Test
    fun testCase13() {
        linkedList.setHead(node1)
        linkedList.insertAfter(node1, node2)
        linkedList.insertAfter(node2, node3)
        linkedList.insertAfter(node3, node4)
        linkedList.insertAfter(node2, node1)
        linkedList.insertBefore(node3, node4)
        linkedList.setHead(node1)

        assertTrue(linkedList.containsNodeWithValue(5))
    }

    @Test
    fun testCase14() {
        linkedList.setHead(node1)
        linkedList.insertAfter(node1, node2)
        linkedList.insertAfter(node2, node3)
        linkedList.insertAfter(node3, node4)
        linkedList.insertAfter(node4, node5)
        linkedList.insertAfter(node5, node6)
        linkedList.insertAfter(node6, node7)
        linkedList.insertAtPosition(7, node1)
        linkedList.insertAtPosition(1, node1)
        linkedList.insertAtPosition(2, node1)
        linkedList.insertAtPosition(3, node1)
        linkedList.insertAtPosition(4, node1)
        linkedList.insertAtPosition(5, node1)
        linkedList.insertAtPosition(6, node1)
        assertTrue(linkedList.containsNodeWithValue(5))
    }

    @Test
    fun testCase15() {
        linkedList.setHead(node1)
        linkedList.remove(node1)
        assertTrue(linkedList.containsNodeWithValue(5))
    }

    @Test
    fun testCase20() {
        val node1 = Node(1)
        val node1_2 = Node(1)
        val node1_3 = Node(1)
        val node1_4 = Node(1)

        linkedList.setHead(node1)
        linkedList.insertAfter(node1, node1_2)
        linkedList.insertAfter(node1_2, node1_3)
        linkedList.insertAfter(node1_3, node1_4)
        linkedList.removeNodesWithValue(1)

        assertFalse(linkedList.containsNodeWithValue(5))
    }

    class Node(val value: Int) {
        var prev: Node? = null
        var next: Node? = null
    }

    class DoublyLinkedList {
        private var head: Node? = null
        private var tail: Node? = null

        fun setHead(node: Node) {
            if (head == node)
                return

            if (head == null) { // no head or tail
                head = node
                tail = node
                return
            }

            if (node == tail)
                tail = node.prev

            // remove the node from where it is first
            node.prev?.next = node.next
            node.next?.prev = node.prev

            node.prev = null
            node.next = head
            head?.prev = node

            head = node
        }

        fun setTail(node: Node) {
            if (tail == node)
                return

            if (head == null) {
                head = node
                tail = node
                return
            }

            insertAfter(this.tail!!, node)
            tail = node
        }

        fun insertBefore(node: Node, nodeToInsert: Node) {

            if(nodeToInsert == head)
                head = nodeToInsert.next

            // remove from existing place first
            nodeToInsert.prev?.next = nodeToInsert.next
            nodeToInsert.next?.prev = nodeToInsert.prev

            // move to new place
            node.prev?.next = nodeToInsert
            nodeToInsert.prev = node.prev
            nodeToInsert.next = node
            node.prev = nodeToInsert

            if (node == head)
                head = nodeToInsert

            if (nodeToInsert == tail)
                tail = node
        }

        fun insertAfter(node: Node, nodeToInsert: Node) {

            // remove from existing place first
            nodeToInsert.prev?.next = nodeToInsert.next
            nodeToInsert.next?.prev = nodeToInsert.prev

            if (head == nodeToInsert)
                head = head?.next

            // move to new place
            node.next?.prev = nodeToInsert
            nodeToInsert.next = node.next
            nodeToInsert.prev = node
            node.next = nodeToInsert

            if (node == tail)
                tail = nodeToInsert

            if (nodeToInsert == head)
                head = node
        }

        fun insertAtPosition(position: Int, nodeToInsert: Node) {

            if (head == null) {
                head = nodeToInsert
                tail = nodeToInsert
                return
            }

            var index = 1
            var node = head
            while (node != null) {

                if (index == position) {
                    insertBefore(node, nodeToInsert)

                    return
                }
                index++
                node = node.next
            }
        }

        fun removeNodesWithValue(value: Int) {
            var node = head
            while (node != null) {

                if (node.value == value) {
                    remove(node)
                }

                node = node.next
            }

            if (head?.value == value)
                head = null
        }

        fun remove(node: Node) {
            when (node) {

                tail -> {
                    tail = node.prev
                    tail?.next = null

                    if(node == head)
                        head = null
                }
                head -> {
                    head = head?.next
                    head?.prev = null
                }
                else -> {
                    node.prev?.next = node.next
                    node.next?.prev = node.prev
                    // node.next = null
                    // node.prev = null
                }
            }
        }

        fun containsNodeWithValue(value: Int): Boolean {
            var node = head
            while (node != null) {
                if (node.value == value) return true
                node = node.next
            }

            return false
        }

        fun getHead(): Node? {
            return this.head
        }

        fun getTail(): Node? {
            return this.tail
        }
    }
}