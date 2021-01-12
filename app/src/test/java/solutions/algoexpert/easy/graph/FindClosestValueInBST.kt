package solutions.algoexpert.easy.graph

import solutions.algoexpert._utils.BST
import solutions.algoexpert._utils.Helper
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Find%20Closest%20Value%20In%20BST
 */
class FindClosestValueInBST {

    lateinit var root: BST

    @Before
    fun setup() {
        root = Helper.buildBST()
    }

    @Test
    fun test() {
        assertEquals(13, findClosestValueInBst(root, 12))
    }

    private fun findClosestValueInBst(tree: BST, target: Int): Int {

        if (target == tree.value) return target

        if (target > tree.value) {
            if (tree.right == null) return tree.value
            val closest = findClosestValueInBst(tree.right!!, target)
            return if (kotlin.math.abs(closest - target) < kotlin.math.abs(tree.value - target)) closest else tree.value
        }

        if (target < tree.value) {
            if (tree.left == null) return tree.value
            val closest = findClosestValueInBst(tree.left!!, target)
            return if (kotlin.math.abs(closest - target) < kotlin.math.abs(tree.value - target)) closest else tree.value
        }

        return -1
    }
}