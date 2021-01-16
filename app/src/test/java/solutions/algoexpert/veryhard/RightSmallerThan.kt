package solutions.algoexpert.veryhard

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Right%20Smaller%20Than
 */
class RightSmallerThan {

    @Test
    fun test() {
        val result = rightSmallerThan(listOf(8, 5, 11, -1, 3, 4, 2))
        assertEquals(listOf(5, 4, 4, 0, 1, 1, 0), result)
    }

    // O(n2)
    fun rightSmallerThan(array: List<Int>): List<Int> {

        val output = mutableListOf<Int>()

        for (i in 0..array.lastIndex) {

            var count = 0
            for (j in i..array.lastIndex) {
                if (array[j] < array[i]) count++
            }

            output.add(count)
        }

        return output
    }

    // O(nlog(n))
    fun rightSmallerThanNLogN(array: List<Int>): List<Int> {

        val root = BST(array[0])
        // construct a BST
        for (i in (1..array.lastIndex)) {
            insert(array[i], root)
        }

        val output = mutableListOf<Int>()

        // TODO WIP Hm then what?
        return output
    }

    private fun insert(value: Int, node: BST?): BST? {

        if (node == null) return null

        if (value < node.value) {
            val left = insert(value, node.left)
            if (left == null) node.left = BST(value)
        }

        if (value >= node.value) {
            val right = insert(value, node.right)
            if (right == null) node.right = BST(value)
        }

        return node
    }

    open class BST(value: Int) {
        var value = value
        var left: BST? = null
        var right: BST? = null
    }
}