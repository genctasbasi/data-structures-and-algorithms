package solutions.leetcode.hard

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/
 */
class MergeKSortedLists {

    fun mergeKLists(lists: Array<ListNode?>): ListNode? {

        if (lists.isEmpty()) return null
        if (lists.size == 1) return lists[0]

        var allMerged = false
        var head: ListNode? = null
        var nodeInOrder: ListNode? = null
        val pointers = Array<ListNode?>(lists.size) { null }

        lists.forEachIndexed { index, it ->
            pointers[index] = it
        }

        var listIndex = 0
        var currentMin = Int.MAX_VALUE
        var pointerMin: ListNode? = null
        var currentMinPointerIndex = 0
        while (!allMerged) {

            val currentPointer = pointers[listIndex]

            if (currentPointer != null) {

                if (currentPointer.`val` < currentMin) {
                    currentMin = currentPointer.`val`
                    pointerMin = currentPointer
                    currentMinPointerIndex = listIndex
                }
            }

            if (listIndex == lists.lastIndex) {  // a round is finished, update the order
                if (head == null) {
                    head = pointerMin
                    nodeInOrder = head

                } else {
                    nodeInOrder?.next = pointerMin
                    nodeInOrder = nodeInOrder?.next
                }

                pointers[currentMinPointerIndex] = pointers[currentMinPointerIndex]?.next
                listIndex = -1
                currentMin = Int.MAX_VALUE
            }

            allMerged = isAllMerged(pointers)
            listIndex++
        }

        return head
    }

    private fun isAllMerged(pointers: Array<ListNode?>): Boolean {

        pointers.forEach {
            if (it != null) return false
        }

        return true
    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
}