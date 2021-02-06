package solutions.algoexpert.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Binary%20Search
 */
class RemoveDuplicatesFromLinkedList {

    @Test
    fun test() {

        val list1 = LinkedList(1)
        val list1_2 = LinkedList(1)
        val list3 = LinkedList(3)
        val list4 = LinkedList(4)
        val list4_2 = LinkedList(4)
        val list4_3 = LinkedList(4)
        val list5 = LinkedList(5)
        val list6 = LinkedList(6)
        val list6_2 = LinkedList(6)

        list1.next = list1_2
        list1_2.next = list3
        list3.next = list4
        list4.next = list4_2
        list4_2.next = list4_3
        list4_3.next = list5
        list5.next = list6
        list6.next = list6_2

        removeDuplicatesFromLinkedList(list1)

        assertEquals(list3, list1.next)
        assertEquals(list5, list4.next)
    }

    open class LinkedList(value: Int) {
        var value = value
        var next: LinkedList? = null
    }

    fun removeDuplicatesFromLinkedList(linkedList: LinkedList): LinkedList {

        var currentNode: LinkedList? = linkedList

        while (currentNode?.next != null) {
            while (currentNode.next?.value == currentNode.value) {
                currentNode.next = currentNode.next?.next
            }

            currentNode = currentNode.next
        }

        return linkedList
    }
}