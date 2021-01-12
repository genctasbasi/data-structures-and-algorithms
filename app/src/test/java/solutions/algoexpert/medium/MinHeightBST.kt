package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Min%20Height%20BST
 */
class MinHeightBST {

    @Test
    fun testCase1() {

        val list = listOf(1, 2, 5, 7, 10, 13, 14, 15, 22)
        val bst = minHeightBst(list)
        Assert.assertEquals(10, bst.value)
        Assert.assertEquals(22, bst.right?.right?.right?.value)
        Assert.assertEquals(1, bst.left?.left?.value)
    }

    fun minHeightBst(array: List<Int>): BST {

        val stack = java.util.Stack<List<Int>>()
        stack.push(array)
        var bst: BST? = null

        while (stack.isNotEmpty()) {

            val popped = stack.pop()
            if (popped.isEmpty()) continue

            if (popped.size == 1) {
                if (bst == null) bst = BST(popped[0])
                else bst.insert(popped[0])
            } else {
                val div = popped.size / 2
                stack.push(popped.subList(0, div))
                stack.push(popped.subList(div + 1, popped.size))
                stack.push(listOf(popped[div]))
            }
        }

        return bst ?: throw Exception("Shouldn't have happened really ¯\\_(ツ)_/¯")
    }

    open class BST(value: Int) {
        var value = value
        var left: BST? = null
        var right: BST? = null

        fun insert(value: Int): BST {

            when {
                value < this.value -> {
                    if (this.left == null) this.left = BST(value)
                    else this.left?.insert(value)
                }

                else -> {
                    if (this.right == null) this.right = BST(value)
                    else this.right?.insert(value)
                }
            }

            return this
        }
    }
}