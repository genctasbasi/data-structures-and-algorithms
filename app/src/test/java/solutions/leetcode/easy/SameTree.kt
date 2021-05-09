package solutions.leetcode.easy

import junit.framework.TestCase.assertTrue
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/same-tree/
 */
class SameTree {

    @Test
    fun test1() {

        val root1 = TreeNode(1)
        val root2 = TreeNode(1)

        val root1Left = TreeNode(3)
        val root1Right = TreeNode(8)

        val root2Left = TreeNode(3)
        val root2Right = TreeNode(18)

        root1.left = root1Left
        root1.right = root1Right

        root2.left = root2Left
        root2.right = root2Right

        val isSameTree = isSameTree(root1, root2)
        assertTrue(isSameTree)
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {

        if (p == null && q == null) return true
        if (p == null || q == null) return false

        if (p.`val` != q.`val`) return false

        return isSameTree(p.left, q.left) &&
                isSameTree(p.right, q.right)
    }

    fun `isSameTree O(n) space`(p: TreeNode?, q: TreeNode?): Boolean {

        val list1 = LinkedList<Int>()
        val list2 = LinkedList<Int>()

        getAsList(p, list1)
        getAsList(q, list2)
        return list1.equals(list2)
    }

    fun getAsList(node: TreeNode?, list: LinkedList<Int>) {

        if (node == null) {
            list.add(-1)
            return
        }

        getAsList(node.left, list)
        getAsList(node.right, list)
        list.add(node.`val`)
    }

}