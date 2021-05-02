package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/add-two-numbers
 */
class AddTwoNumbers {

    @Test
    fun test() {
        val listNode2 = ListNode(2)
        val listNode4 = ListNode(4)
        val listNode3 = ListNode(3)

        listNode2.next = listNode4
        listNode4.next = listNode3

        val listNode5 = ListNode(5)
        val listNode6 = ListNode(6)
        val listNode4_2 = ListNode(4)

        listNode5.next = listNode6
        listNode6.next = listNode4_2

        val result = addTwoNumbers(listNode2, listNode5)
        assertEquals(result?.`val`, 7)
        assertEquals(result?.next?.`val`, 0)
        assertEquals(result?.next?.next?.`val`, 8)
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {

        var sumList: ListNode? = null
        var head: ListNode? = null
        var list1 = l1
        var list2 = l2

        var carriedOver = false
        while (!(list1 == null && list2 == null)) {

            var sum: Int

            if (sumList == null)
                sumList = ListNode(0)
            else {
                sumList.next = ListNode(0)
                sumList = sumList.next
            }

            if (head == null)
                head = sumList

            if (list1 == null) {
                sum = list2!!.`val`
                if (carriedOver) {
                    sum++
                }
            } else if (list2 == null) {
                sum = list1!!.`val`
                if (carriedOver) {
                    sum++
                }
            } else {
                sum = list1.`val` + list2.`val`
                if (carriedOver) {
                    sum++
                }
            }

            val digit = sum.rem(10)
            carriedOver = sum >= 10
            sumList?.`val` = digit

            list1 = list1?.next
            list2 = list2?.next
        }

        if (carriedOver)
            sumList?.next = ListNode(1)

        return head
    }
}