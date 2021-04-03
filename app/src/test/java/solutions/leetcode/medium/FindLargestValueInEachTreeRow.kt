package solutions.leetcode.medium

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/find-largest-value-in-each-tree-row/
 */
class FindLargestValueInEachTreeRow {

    @Test
    fun test() {

        val node1 = TreeNode(1)
        val node3 = TreeNode(3)
        val node2 = TreeNode(2)
        val node5 = TreeNode(5)
        val node3_2 = TreeNode(3)
        val node9 = TreeNode(9)

        node1.left = node3
        node1.right = node2
        node3.left = node5
        node3.right = node3_2
        node2.right = node9

        val result = largestValues(node1).toTypedArray()
        assertArrayEquals(arrayOf(1, 3, 9), result)
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun largestValues(root: TreeNode?): List<Int> {
        val map = mutableMapOf<Int, Int>()  // key: level, value: largest
        dfs(root, map, 0)
        return map.values.toList()
    }

    fun dfs(node: TreeNode?, map: MutableMap<Int, Int>, level: Int) {

        if (node == null) return
        dfs(node.left, map, level + 1)
        dfs(node.right, map, level + 1)
        map[level] = Math.max(map[level] ?: Int.MIN_VALUE, node.`val`)
    }
}