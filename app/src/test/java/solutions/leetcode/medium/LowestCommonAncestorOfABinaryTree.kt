package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
class LowestCommonAncestorOfABinaryTree {

    @Test
    fun test() {

        val node3 = TreeNode(3)
        val node5 = TreeNode(5)
        val node1 = TreeNode(1)
        val node6 = TreeNode(6)
        val node2 = TreeNode(2)
        val node0 = TreeNode(0)
        val node8 = TreeNode(8)
        val node7 = TreeNode(7)
        val node4 = TreeNode(4)

        node3.left = node5
        node3.right = node1

        node5.left = node6
        node5.right = node2

        node1.left = node0
        node1.right = node8

        node2.left = node7
        node2.right = node4

        val result = lowestCommonAncestor(node3, node4, node5)
        assertEquals(node5, result)
    }

    class TreeNode(var `val`: Int = 0) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        helper(root, p, q)
        return lca
    }

    var lca: TreeNode? = null

    fun helper(node: TreeNode?, p: TreeNode?, q: TreeNode?): Boolean {

        if (node == null) return false

        val hasInLeft = helper(node.left, p, q)
        val hasInRight = helper(node.right, p, q)

        if (listOf(
                hasInLeft,
                hasInRight,
                node.`val` == p?.`val`,
                node.`val` == q?.`val`
            ).count { it } > 1
        ) {
            lca = node
        }

        return hasInLeft || hasInRight || node.`val` == p?.`val`
                || node.`val` == q?.`val`
    }

    fun lowestCommonAncestorWithList(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
        val list1 = dfs(root, p)
        val list2 = dfs(root, q)

        if (list1 == null || list2 == null) throw IllegalStateException()

        var pointer1 = list1.lastIndex
        var pointer2 = list2.lastIndex

        var lcs = root
        while (pointer1 >= 0 && pointer2 >= 0) {
            val node1 = list1[pointer1]
            val node2 = list2[pointer2]

            if (node1.`val` == node2.`val`) {
                lcs = node1
            }

            pointer1--
            pointer2--
        }

        return lcs
    }

    fun dfs(node: TreeNode?, lookFor: TreeNode?): MutableList<TreeNode>? {

        if (node == null) return null
        if (node == lookFor) return mutableListOf(node)

        val listLeft = dfs(node.left, lookFor)
        if (listLeft != null) {
            listLeft.add(node)
            return listLeft
        } else {
            val listRight = dfs(node.right, lookFor)
            if (listRight != null) {
                listRight.add(node)
                return listRight
            }
        }

        return null
    }
}