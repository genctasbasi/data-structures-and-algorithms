package solutions.leetcode.medium

import org.junit.Test

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/
 */
class BinarySearchTreeIterator {

    @Test
    fun test() {

        val node7 = TreeNode(7)
        val node3 = TreeNode(3)
        val node15 = TreeNode(15)
        val node9 = TreeNode(9)
        val node20 = TreeNode(20)

        node7.left = node3
        node7.right = node15

        node15.left = node9
        node15.right = node20

        val bstIterator = BSTIterator(node7)
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    class BSTIterator(root: TreeNode?) {

        val values = mutableListOf<Int>()
        var pointer = -1

        init {
            setPointer(root)
        }

        fun setPointer(node: TreeNode?) {

            if (node == null) return
            setPointer(node.left)
            values.add(node.`val`)
            setPointer(node.right)
        }

        fun next(): Int {
            pointer++
            return values[pointer]
        }

        fun hasNext(): Boolean {
            return pointer < values.lastIndex
        }
    }

}