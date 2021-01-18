package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 */
class SwapNodesInPairs {

    @Test
    fun test() {

        val node1 = ListNode(1)
        val node2 = ListNode(2)
        val node3 = ListNode(3)
        val node4 = ListNode(4)
        val node5 = ListNode(5)

        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node5

        val result = swapPairs(node1)
        assertEquals(2, result?.`val`)
        assertEquals(1, result?.next?.`val`)
        assertEquals(4, result?.next?.next?.`val`)
        assertEquals(3, result?.next?.next?.next?.`val`)
    }

    fun swapPairs(head: ListNode?): ListNode? {
        if (head?.next == null) return head

        var pointerPrev = head
        var pointer = head.next

        while (pointer != null && pointerPrev != null) {

            val temp = pointer.`val`
            pointer.`val` = pointerPrev.`val`
            pointerPrev.`val` = temp

            pointer = pointer.next?.next
            pointerPrev = pointerPrev.next?.next
        }

        return head
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

}