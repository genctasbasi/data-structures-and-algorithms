package solutions.leetcode.easy

/**
 * https://leetcode.com/problems/invert-binary-tree/
 */
class InvertBinaryTree {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun invertTree(root: TreeNode?): TreeNode? {

        if (root == null) return null

        invertTree(root.left)
        invertTree(root.right)

        val temp = root.left
        root.left = root.right
        root.right = temp

        return root
    }
}