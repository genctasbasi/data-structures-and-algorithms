package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Invert%20Binary%20Tree
 */
class InvertBinaryTree {

    @Test
    fun testCase1() {

        val root = BinaryTree(1)
        root.left = BinaryTree(2)
        root.left?.left = BinaryTree(4)
        root.left?.right = BinaryTree(5)

        root.left?.left?.left = BinaryTree(8)
        root.left?.left?.right = BinaryTree(9)

        root.right = BinaryTree(3)
        root.right?.left = BinaryTree(6)
        root.right?.right = BinaryTree(7)

        invertBinaryTree(root)

        Assert.assertEquals(3, root.left?.value)
        Assert.assertEquals(2, root.right?.value)
    }

    open class BinaryTree(value: Int) {
        var value = value
        var left: BinaryTree? = null
        var right: BinaryTree? = null
    }

    fun invertBinaryTree(tree: BinaryTree) {
        swap(tree)
    }

    fun swap(
        tree: BinaryTree?
    ) {
        if (tree == null) return
        swapContent(tree)
        swap(tree.left)
        swap(tree.right)
    }

    private fun swapContent(
        tree: BinaryTree?
    ) {
        if (tree == null) return

        val temp = tree.left
        tree.left = tree.right
        tree.right = temp
    }
}