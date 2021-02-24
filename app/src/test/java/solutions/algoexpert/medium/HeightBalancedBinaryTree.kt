package solutions.algoexpert.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Height%20Balanced%20Binary%20Tree
 */

class HeightBalancedBinaryTree {

    @Test
    fun test() {
        val node1 = BinaryTree(1)
        val node2 = BinaryTree(2)
        val node3 = BinaryTree(3)
        val node4 = BinaryTree(4)
        val node5 = BinaryTree(5)
        val node6 = BinaryTree(6)
        val node7 = BinaryTree(7)
        val node8 = BinaryTree(8)

        node1.left = node2
        node1.right = node3

        node2.left = node4
        node2.right = node5

        node5.left = node7
        node5.right = node8

        node3.right = node6

        assertEquals(true, heightBalancedBinaryTree(node1))
    }

    @Test
    fun test1() {
        val node1 = BinaryTree(1)
        val node2 = BinaryTree(2)
        val node3 = BinaryTree(3)

        node1.left = node2
        node2.right = node3

        assertEquals(false, heightBalancedBinaryTree(node1))
    }

    @Test
    fun test2() {
        val node1 = BinaryTree(1)
        val node2 = BinaryTree(2)
        val node3 = BinaryTree(3)
        val node4 = BinaryTree(4)
        val node5 = BinaryTree(5)
        val node6 = BinaryTree(6)
        val node7 = BinaryTree(7)
        val node8 = BinaryTree(8)
        val node9 = BinaryTree(9)
        val node10 = BinaryTree(10)
        val node11 = BinaryTree(11)

        node1.left = node2
        node1.right = node3

        node2.left = node4
        node2.right = node5

        node3.left = node11
        node3.right = node6

        node5.left = node7
        node5.right = node8

        node6.left = node9
        node6.right = node10

        assertEquals(true, heightBalancedBinaryTree(node1))
    }

    @Test
    fun test3() {
        val node1 = BinaryTree(1)
        val node2 = BinaryTree(2)
        val node3 = BinaryTree(3)
        val node4 = BinaryTree(4)
        val node5 = BinaryTree(5)
        val node6 = BinaryTree(6)
        val node7 = BinaryTree(7)
        val node8 = BinaryTree(8)

        node1.left = node2
        node1.right = node3

        node2.left = node4
        node2.right = node5

        node3.right = node6

        node5.left = node7
        node5.right = node8

        assertEquals(true, heightBalancedBinaryTree(node1))
    }

    open class BinaryTree(value: Int) {
        var value = value
        var left: BinaryTree? = null
        var right: BinaryTree? = null
    }

    var isBalanced = true
    fun heightBalancedBinaryTree(tree: BinaryTree): Boolean {

        val leftHeight = getHeight(tree.left)
        val rightHeight = getHeight(tree.right)

        if (!isBalanced) return false
        return Math.abs(leftHeight - rightHeight) <= 1
    }

    fun getHeight(node: BinaryTree?): Int {

        if (node == null) return 0

        val leftHeight = getHeight(node.left)
        val rightHeight = getHeight(node.right)

        if (Math.abs(leftHeight - rightHeight) > 1) {
            isBalanced = false
        }

        return Math.max(leftHeight, rightHeight) + 1
    }
}