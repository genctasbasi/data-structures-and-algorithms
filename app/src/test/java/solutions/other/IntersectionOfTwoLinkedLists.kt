package solutions.other

/**
 *
 * https://www.geeksforgeeks.org/problems/intersection-of-two-linked-list/1
 *
 * Given two linked lists of length n and m, the task is to complete the function findIntersection(),
 * which returns the intersection of two linked lists.
 * Each of the two linked lists contains distinct node values.
 *
 */
class IntersectionOfTwoLinkedLists {

    class Node(var data: Int) {
        var next: Node? = null
    }

    // LinkedList1: 9->6->4->2->3->8
    // LinkedList2: 1->2->8->6

    fun findIntersection(head1: Node?, head2: Node?): Node? {
        val set = mutableSetOf<Int>()

        var current = head1

        var returnNode: Node? = null

        while (current != null) {
            set.add(current.data)
            current = current.next
        }

        current = head2
        while (current != null) {

            if (!set.add(current.data)) {
                if (returnNode == null) {
                    returnNode = Node(current.data)
                } else {
                    returnNode.next = Node(current.data)
                }
                print(current.data)
            }

            current = current.next
        }

        return returnNode
    }
}