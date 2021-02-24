package solutions.algoexpert.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Find%20Kth%20Largest%20Value%20In%20BST
 */

class FindKthLargestValueInBst {

    @Test
    fun test() {
        val node15 = BST(15)
        val node5 = BST(5)
        val node20 = BST(20)
        val node2 = BST(2)
        val node5_1 = BST(5)
        val node17 = BST(17)
        val node22 = BST(22)
        val node1 = BST(1)
        val node3 = BST(3)

        node15.left = node5
        node15.right = node20

        node5.left = node2
        node5.right = node5_1

        node2.left = node1
        node2.right = node3

        node20.left = node17
        node20.right = node22

        val result = findKthLargestValueInBst(node15, 6)
        assertEquals(5, result)
    }

    open class BST(value: Int) {
        var value = value
        var left: BST? = null
        var right: BST? = null
    }

    fun findKthLargestValueInBst(tree: BST, k: Int): Int {
        helper(tree, k)
        return kThMax ?: 0
    }

    var count = 0
    var kThMax: Int? = null
    fun helper(node: BST?, k: Int) {

        if (node == null) return

        helper(node.right, k)
        count++
        if (count == k && kThMax == null) {
            kThMax = node.value
            return
        }

        helper(node.left, k)
    }
}