package solutions.algoexpert.hard

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Merge%20Linked%20Lists
 */
class MergedLinkedList {

    @Test
    fun test() {

        val node1 = LinkedList(1)
        val node2 = LinkedList(2)
        val node3 = LinkedList(3)
        val node4 = LinkedList(4)
        val node5 = LinkedList(5)
        val node6 = LinkedList(6)
        val node7 = LinkedList(7)
        val node8 = LinkedList(8)
        val node9 = LinkedList(9)
        val node10 = LinkedList(10)

        node1.next = node3
        node3.next = node4
        node4.next = node5
        node5.next = node9
        node9.next = node10

        node2.next = node6
        node6.next = node7
        node7.next = node8

        val merge = mergeLinkedLists(node2, node1)
        assertEquals(node1, merge)
    }

    @Test
    fun test2() {

        val node1 = LinkedList(1)
        val node2 = LinkedList(2)
        val node3 = LinkedList(3)
        val node4 = LinkedList(4)
        val node5 = LinkedList(5)
        val node6 = LinkedList(6)
        val node7 = LinkedList(7)
        val node8 = LinkedList(8)
        val node9 = LinkedList(9)
        val node10 = LinkedList(10)

        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node5

        node6.next = node7
        node7.next = node8
        node8.next = node9
        node9.next = node10

        val merge = mergeLinkedLists(node6, node1)
        assertEquals(node1, merge)
    }

    @Test
    fun test3() {

        val node1 = LinkedList(1, "1")
        val node1_2 = LinkedList(1, "1_2")
        val node1_3 = LinkedList(1, "1_3")
        val node3 = LinkedList(3, "3")
        val node4 = LinkedList(4, "4")
        val node5 = LinkedList(5, "5")
        val node5_2 = LinkedList(5, "5_2")
        val node5_3 = LinkedList(5, "5_3")
        val node5_4 = LinkedList(5, "5_4")
        val node10 = LinkedList(10, "10")

        val node1_4 = LinkedList(1, "1_4")
        val node1_5 = LinkedList(1, "1_5")
        val node2 = LinkedList(2, "2")
        val node2_2 = LinkedList(2, "2_2")
        val node5_5 = LinkedList(5, "5_5")
        val node6 = LinkedList(6, "6")
        val node10_2 = LinkedList(10, "10_2")
        val node10_3 = LinkedList(10, "10_3")

        node1.next = node1_2
        node1_2.next = node1_3
        node1_3.next = node3
        node3.next = node4
        node4.next = node5
        node5.next = node5_2
        node5_2.next = node5_3
        node5_3.next = node5_4
        node5_4.next = node10

        node1_4.next = node1_5
        node1_5.next = node2
        node2.next = node2_2
        node2_2.next = node5_5
        node5_5.next = node6
        node6.next = node10_2
        node10_2.next = node10_3

        val merge = mergeLinkedLists(node1, node1_4)
        assertEquals(node1, merge)
    }

    open class LinkedList(value: Int, val name: String? = "") {
        var value = value
        var next: LinkedList? = null
    }

    fun mergeLinkedLists(headOne: LinkedList, headTwo: LinkedList): LinkedList {

        var pointer1: LinkedList? = headOne
        var pointer2: LinkedList? = headTwo

        while (pointer1 != null || pointer2 != null) {

            // fast forward pointer2
            while (pointer2 != null && pointer2.next?.value == pointer1?.value)
                pointer2 = pointer2.next

            if (pointer1 == null) {
                pointer2 = pointer2?.next
                continue

            } else if (pointer2 == null) {
                pointer1 = pointer1.next
                continue
            }

            if (pointer1.value >= pointer2.value) {

                if (pointer2.next?.value ?: Int.MAX_VALUE < pointer1.value) {
                    pointer2 = pointer2.next
                } else {
                    val temp = pointer2.next
                    pointer2.next = pointer1
                    pointer2 = temp

                }

            } else if (pointer1.value < pointer2.value) {

                if (pointer1.next?.value ?: Int.MAX_VALUE < pointer2.value) {
                    pointer1 = pointer1.next
                } else {
                    val temp = pointer1.next
                    pointer1.next = pointer2
                    pointer1 = temp
                }

            }
        }

        return if (headOne.value < headTwo.value) headOne else headTwo
    }
}