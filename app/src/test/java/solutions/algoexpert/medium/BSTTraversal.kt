package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import solutions.algoexpert._utils.BST
import solutions.algoexpert._utils.Helper

/**
 * https://www.algoexpert.io/questions/BST%20Traversal
 */
class BSTTraversal {

    lateinit var validBST: BST

    @Before
    fun setup() {
        validBST = Helper.buildBST()
    }

    @Test
    fun test() {

        val inOrderExpected = listOf(1, 2, 5, 5, 10, 13, 14, 15, 22)
        val inOrder = inOrderTraverse(validBST, emptyArray<Int>().toMutableList())
        Assert.assertEquals(inOrderExpected, inOrder)

        val preOrderExpected = listOf(10, 5, 2, 1, 5, 15, 13, 14, 22)
        val preOrder = preOrderTraverse(validBST, emptyArray<Int>().toMutableList())
        Assert.assertEquals(preOrderExpected, preOrder)

        val postOrderExpected = listOf(1, 2, 5, 5, 14, 13, 22, 15, 10)
        val postOrder = postOrderTraverse(validBST, emptyArray<Int>().toMutableList())
        Assert.assertEquals(postOrderExpected, postOrder)
    }

    /**
     * left, root, right
     */
    fun inOrderTraverse(tree: BST?, array: MutableList<Int>): List<Int> {

        if (tree == null) return array
        inOrderTraverse(tree.left, array)
        array.add(tree.value)
        inOrderTraverse(tree.right, array)
        return array
    }

    /**
     * root, left, right
     */
    fun preOrderTraverse(tree: BST?, array: MutableList<Int>): List<Int> {

        if (tree == null) return array
        array.add(tree.value)
        preOrderTraverse(tree.left, array)
        preOrderTraverse(tree.right, array)
        return array
    }

    /**
     * left, right, root
     */
    fun postOrderTraverse(tree: BST?, array: MutableList<Int>): List<Int> {
        if (tree == null) return array
        postOrderTraverse(tree.left, array)
        postOrderTraverse(tree.right, array)
        array.add(tree.value)

        return array
    }
}