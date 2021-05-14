package solutions.leetcode.medium

import org.junit.Test

/**
 * https://leetcode.com/problems/delete-nodes-and-return-forest/
 */
class DeleteNodesAndReturnForest {

    @Test
    fun test() {

        val node1 = TreeNode(1)
        val node2 = TreeNode(2)
        val node3 = TreeNode(3)
        val node4 = TreeNode(4)
        val node5 = TreeNode(5)
        val node6 = TreeNode(6)
        val node7 = TreeNode(7)

        node1.left = node2
        node1.right = node3

        node2.left = node4
        node2.right = node5

        node3.left = node6
        node3.right = node7

        delNodes(node1, intArrayOf(3, 5))
    }

    @Test
    fun test1() {

        val node1 = TreeNode(1)
        val node2 = TreeNode(2)
        val node3 = TreeNode(3)
        val node4 = TreeNode(4)

        node1.left = node2
        node1.right = node3

        node3.left = node4

        delNodes(node1, intArrayOf(3, 5))
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun delNodes(root: TreeNode?, to_delete: IntArray): List<TreeNode?> {

        val toDelete = to_delete.toHashSet()
        val output = hashSetOf<TreeNode>()
        val node = del(root, toDelete, output)

        if (node != null) output.add(node)
        return output.toList()
    }

    fun del(root: TreeNode?, toDelete: HashSet<Int>, output: HashSet<TreeNode>): TreeNode? {

        if (root == null) return null

        root.left = del(root.left, toDelete, output)
        root.right = del(root.right, toDelete, output)

        if (toDelete.contains(root.`val`)) {
            root.left?.let { output.add(it) }
            root.right?.let { output.add(it) }

            return null
        }

        return root
    }

    fun del2(root: TreeNode?, set: HashSet<Int>, deleted: HashSet<TreeNode>) {

        if (root == null) return

        root.left?.let {
            if (set.contains(it.`val`)) {
                it.left?.let {
                    if (!set.contains(it.`val`)) deleted.add(it)
                }
                it.right?.let {
                    if (!set.contains(it.`val`)) deleted.add(it)
                }

                root.left = null
            } else del(root.left, set, deleted)
        }

        root.right?.let {
            if (set.contains(it.`val`)) {
                it.left?.let { deleted.add(it) }
                it.right?.let { deleted.add(it) }
                root.right = null
            } else del(root.right, set, deleted)
        }
    }
}