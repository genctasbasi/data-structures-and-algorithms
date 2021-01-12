package solutions.algoexpert.easy.graph

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import solutions.algoexpert._utils.BinaryTree
import solutions.algoexpert._utils.Helper

/**
 * https://www.algoexpert.io/questions/Branch%20Sums
 */
class BranchSums {

    lateinit var binaryTree: BinaryTree

    @Before
    fun setup() {
        binaryTree = Helper.buildBinaryTree()
    }

    @Test
    fun test() {
        assertEquals(listOf(15, 16, 18, 10, 11), branchSums(binaryTree))
    }

    fun branchSums(root: BinaryTree?): List<Int> {
        return sumHelper(root, 0, mutableListOf())
    }

    private fun sumHelper(root: BinaryTree?, sum: Int, list: MutableList<Int>): MutableList<Int> {

        if (root == null) return list

        if (root.left == null && root.right == null) {
            list.add(sum + root.value)
            return list
        }

        val listLeft = sumHelper(root.left, sum + root.value, list)
        return sumHelper(root.right, sum + root.value, listLeft)
    }
}