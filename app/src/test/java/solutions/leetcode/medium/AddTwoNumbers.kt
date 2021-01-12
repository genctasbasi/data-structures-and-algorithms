package solutions.leetcode.medium

/**
 * https://leetcode.com/problems/add-two-numbers/
 */
class AddTwoNumbers {

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