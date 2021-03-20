package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 */
class ConvertBinarySearchTreeToSortedDoublyLinkedList {

    @Test
    fun test() {
        val node4 = Node(4)
        val node2 = Node(2)
        val node5 = Node(5)
        val node1 = Node(1)
        val node3 = Node(3)

        node4.left = node2
        node4.right = node5

        node2.left = node1
        node2.right = node3

        val result = treeToDoublyList(node4)
        assertEquals(result?.`val`, 1)
    }

    class Node(var `val`: Int) {
        var left: Node? = null
        var right: Node? = null
    }

    var currentLinkedListNode: Node? = null
    var headOfLinkedListNode: Node? = null

    private fun treeToDoublyList(root: Node?): Node? {
        helper(root)

        currentLinkedListNode = headOfLinkedListNode
        while (currentLinkedListNode?.right != null) {
            currentLinkedListNode = currentLinkedListNode?.right
        }

        currentLinkedListNode?.right = headOfLinkedListNode
        headOfLinkedListNode?.left = currentLinkedListNode

        return headOfLinkedListNode
    }

    private fun helper(root: Node?) {

        if (root == null) return

        helper(root.left)
        if (currentLinkedListNode == null) {
            headOfLinkedListNode = Node(root.`val`)
            currentLinkedListNode = headOfLinkedListNode
        } else {
            val newNode = Node(root.`val`)
            currentLinkedListNode!!.right = newNode
            newNode.left = currentLinkedListNode
            currentLinkedListNode = newNode
        }

        helper(root.right)
    }

}