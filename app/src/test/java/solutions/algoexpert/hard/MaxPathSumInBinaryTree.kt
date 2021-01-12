package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Max%20Path%20Sum%20In%20Binary%20Tree
 */
class MaxPathSumInBinaryTree {

    @Test
    fun test1() {
        val sum = maxPathSum(getTree1())
        assertEquals(18, sum)
    }

    @Test
    fun test3() {
        val sum = maxPathSum(getTree3())
        assertEquals(3, sum)
    }

    @Test
    fun test4() {
        val sum = maxPathSum(getTree4())
        assertEquals(6, sum)
    }

    @Test
    fun test15() {
        val sum = maxPathSum(getTree15())
        assertEquals(-1, sum)
    }

    @Test
    fun test16() {
        val sum = maxPathSum(getTree16())
        assertEquals(4, sum)
    }

    fun maxPathSum(tree: BinaryTree): Int {

        val left = maxPathSumHelper(tree.left, Int.MIN_VALUE)
        val right = maxPathSumHelper(tree.right, Int.MIN_VALUE)

        val maxNode = Math.max(left.second, right.second)
        val totalPath =
            Math.max(
                left.first + right.first + tree.value,
                Math.max(left.first + tree.value, right.first + tree.value)
            )

        return Math.max(maxNode, totalPath)
    }

    fun maxPathSumHelper(tree: BinaryTree?, max: Int): Pair<Int, Int> { // path total, max so far

        if (tree == null) return Pair(0, max)

        val sumLeftMax = maxPathSumHelper(tree.left, max)
        val sumRightMax = maxPathSumHelper(tree.right, max)

        val pathSum = Math.max(sumLeftMax.first, sumRightMax.first) + tree.value
        val maxSoFar = Math.max(
            Math.max(pathSum, Math.max(sumLeftMax.second, sumRightMax.second)),
            sumLeftMax.first + sumRightMax.first + tree.value
        )

        return Pair(pathSum, maxSoFar)
    }

    private fun getTree1(): BinaryTree {
        val tree1 = BinaryTree(1)
        val tree2 = BinaryTree(2)
        val tree3 = BinaryTree(3)
        val tree4 = BinaryTree(4)
        val tree5 = BinaryTree(5)
        val tree6 = BinaryTree(6)
        val tree7 = BinaryTree(7)

        tree1.left = tree2
        tree1.right = tree3

        tree2.left = tree4
        tree2.right = tree5

        tree3.left = tree6
        tree3.right = tree7

        return tree1
    }

    private fun getTree3(): BinaryTree {
        val tree1 = BinaryTree(1)
        val treeMinus1 = BinaryTree(-1)
        val tree2 = BinaryTree(2)

        tree1.left = tree2
        tree1.right = treeMinus1

        return tree1
    }

    private fun getTree4(): BinaryTree {
        val tree1 = BinaryTree(1)
        val tree3 = BinaryTree(3)
        val treeMinus5 = BinaryTree(-5)
        val tree6 = BinaryTree(6)

        tree1.left = treeMinus5
        tree1.right = tree3

        treeMinus5.left = tree6

        return tree1
    }

    private fun getTree15(): BinaryTree {
        val treeM2 = BinaryTree(-2)
        val treeM1 = BinaryTree(-1)

        treeM2.left = treeM1

        return treeM2
    }

    private fun getTree16(): BinaryTree {
        val treeM2 = BinaryTree(-2)
        val treeM1 = BinaryTree(-1)
        val tree2 = BinaryTree(2)
        val tree3 = BinaryTree(3)

        treeM2.left = treeM1
        treeM1.left = tree2
        treeM1.right = tree3

        return treeM2
    }

    open class BinaryTree(value: Int) {
        var value = value
        var left: BinaryTree? = null
        var right: BinaryTree? = null
    }

}