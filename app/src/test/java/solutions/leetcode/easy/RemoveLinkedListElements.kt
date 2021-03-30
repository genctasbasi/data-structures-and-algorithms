package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/remove-linked-list-elements/
 */
class RemoveLinkedListElements {

    @Test
    fun test1() {

        val node0 = ListNode(6)
        val node1 = ListNode(1)
        val node2 = ListNode(2)
        val node6 = ListNode(6)
        val node3 = ListNode(3)
        val node4 = ListNode(4)
        val node5 = ListNode(5)
        val node6_2 = ListNode(6)

        node0.next = node1
        node1.next = node2
        node2.next = node6
        node6.next = node3
        node3.next = node4
        node4.next = node5
        node5.next = node6_2

        val head = removeElements(node0, 6)
        assertEquals(3, head?.next?.next?.`val`)
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun removeElements(head: ListNode?, value: Int): ListNode? {

        val startNode = ListNode(0)
        startNode.next = head
        var curr: ListNode? = startNode

        while (curr != null) {

            if (curr.next?.`val` == value) {
                curr.next = curr.next?.next
            } else {
                curr = curr.next
            }
        }

        return startNode.next
    }

}