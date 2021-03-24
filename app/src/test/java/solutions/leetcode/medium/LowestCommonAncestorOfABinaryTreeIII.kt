package solutions.leetcode.medium

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/
 */
class LowestCommonAncestorOfABinaryTreeIII {

    class Node(var `val`: Int) {
        var left: Node? = null
        var right: Node? = null
        var parent: Node? = null
    }

    fun lowestCommonAncestor(p: Node?, q: Node?): Node? {
        val set = hashSetOf<Node>()
        var parent = p
        while (parent != null) {
            set.add(parent)
            parent = parent.parent
        }

        parent = q
        while (parent != null) {
            if (set.contains(parent)) return parent
            parent = parent.parent
        }

        return null
    }
}