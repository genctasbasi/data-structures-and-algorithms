package solutions.algoexpert.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Find%20Successor
 *
 */
class FindSuccessor {

    @Test
    fun test() {

        val node1 = BinaryTree(1)
        val node2 = BinaryTree(2)
        val node3 = BinaryTree(3)
        val node4 = BinaryTree(4)
        val node5 = BinaryTree(5)
        val node6 = BinaryTree(6)

        node1.left = node2
        node1.right = node3

        node2.left = node4
        node2.right = node5

        node4.left = node6

        val result = findSuccessor(node1, node5)
        assertEquals(node1, result)
    }

    // This is an input class. Do not edit.
    open class BinaryTree(value: Int) {
        var value = value
        var left: BinaryTree? = null
        var right: BinaryTree? = null
        var parent: BinaryTree? = null
    }

    fun findSuccessor(tree: BinaryTree?, node: BinaryTree): BinaryTree? {
        val list = mutableListOf<BinaryTree>()
        findSuccessorHelper(tree, list)

        list.forEachIndexed { index, it ->
            if (it.value == node.value)
                return if (index + 1 > list.lastIndex) null else list[index + 1]
        }

        return null
    }

    fun findSuccessorHelper(
        tree: BinaryTree?,
        list: MutableList<BinaryTree>
    ): BinaryTree? {

        if (tree == null) return null
        val left = findSuccessorHelper(tree.left, list)
        if (left != null) list.add(left)

        list.add(tree)

        val right = findSuccessorHelper(tree.right, list)
        if (right != null) list.add(right)

        return null
    }
}