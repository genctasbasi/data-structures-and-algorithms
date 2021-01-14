package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 */
class MergeTwoSortedLists {

    @Test
    fun test() {

        val listNode1 = ListNode(1)
        val listNode3 = ListNode(3)
        val listNode7 = ListNode(7)

        listNode1.next = listNode3
        listNode3.next = listNode7

        val listNode2 = ListNode(2)
        val listNode5 = ListNode(5)
        val listNode9 = ListNode(9)

        listNode2.next = listNode5
        listNode5.next = listNode9

        val result = mergeTwoLists(listNode1, listNode2)
        Assert.assertEquals(listNode1, result)
        Assert.assertEquals(listNode2, result?.next)
        Assert.assertEquals(listNode3, result?.next?.next)
        Assert.assertEquals(listNode5, result?.next?.next?.next)
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {

        if (l1 == null) return l2
        if (l2 == null) return l1

        var l1p = l1
        var l2p = l2

        var p: ListNode
        if (l1p.`val` < l2p.`val`) {
            p = l1p
            l1p = l1p.next

        } else {
            p = l2p
            l2p = l2p.next
        }

        val root = p

        while (!(l1p == null && l2p == null)) {

            if (l1p == null) {
                p.next = l2p
                l2p = l2p?.next
            } else if (l2p == null) {
                p.next = l1p
                l1p = l1p.next
            } else {

                if (l1p.`val` < l2p.`val`) {
                    p.next = l1p
                    l1p = l1p.next
                } else {
                    p.next = l2p
                    l2p = l2p.next
                }
            }

            p = p.next!!
        }

        return root
    }
}