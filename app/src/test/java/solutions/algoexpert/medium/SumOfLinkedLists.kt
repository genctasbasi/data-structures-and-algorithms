package solutions.algoexpert.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Monotonic%20Array
 */
class SumOfLinkedLists {

    @Test
    fun test() {
        val l2 = LinkedList(2)
        val l4 = LinkedList(4)
        val l7 = LinkedList(7)
        val l1 = LinkedList(1)
        l2.next = l4
        l4.next = l7
        l7.next = l1

        val l9 = LinkedList(9)
        val l4_2 = LinkedList(4)
        val l5 = LinkedList(5)
        l9.next = l4_2
        l4_2.next = l5

        val result = sumOfLinkedLists(l2, l9)
        assertEquals(1, result.value)
        assertEquals(9, result.next?.value)
        assertEquals(2, result.next?.next?.value)
        assertEquals(2, result.next?.next?.next?.value)
    }

    open class LinkedList(value: Int) {
        var value = value
        var next: LinkedList? = null
    }

    fun sumOfLinkedLists(linkedListOne: LinkedList, linkedListTwo: LinkedList): LinkedList {

        var p1: LinkedList? = linkedListOne
        var p2: LinkedList? = linkedListTwo
        var multiplier = 1
        var total = 0

        while (p1 != null || p2 != null) {

            val valP1 = (p1?.value ?: 0) * multiplier
            val valP2 = (p2?.value ?: 0) * multiplier

            total += (valP1 + valP2)

            p1 = p1?.next
            p2 = p2?.next
            multiplier *= 10
        }

        val root = LinkedList(0)
        var current: LinkedList? = root
        total.toString().reversed().forEachIndexed { index, it ->
            current?.value = it.toString().toInt()
            if (index != total.toString().lastIndex) {
                current?.next = LinkedList(0)
                current = current?.next
            }
        }

        return root
    }

}