package solutions.algoexpert.hard

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Find%20Nodes%20Distance%20K
 */
class FindNodesDistanceK {

    @Test
    fun test() {
        val tree1 = BinaryTree(1)
        val tree2 = BinaryTree(2)
        val tree3 = BinaryTree(3)
        val tree4 = BinaryTree(4)
        val tree5 = BinaryTree(5)
        val tree6 = BinaryTree(6)
        val tree7 = BinaryTree(7)
        val tree8 = BinaryTree(8)

        tree1.left = tree2
        tree1.right = tree3

        tree2.left = tree4
        tree2.right = tree5

        tree3.right = tree6

        tree6.left = tree7
        tree6.right = tree8

        val nodes = findNodesDistanceK(tree1, 3, 2)
        assertEquals(2, nodes[0])
        assertEquals(7, nodes[1])
        assertEquals(8, nodes[2])
    }

    @Test
    fun test1() {
        val tree1 = BinaryTree(1)
        val tree2 = BinaryTree(2)
        val tree3 = BinaryTree(3)
        val tree4 = BinaryTree(4)
        val tree5 = BinaryTree(5)
        val tree6 = BinaryTree(6)
        val tree7 = BinaryTree(7)
        val tree8 = BinaryTree(8)

        tree1.left = tree2
        tree1.right = tree3

        tree2.left = tree4

        tree3.right = tree5

        tree4.left = tree6

        tree5.left = tree7
        tree5.right = tree8

        val nodes = findNodesDistanceK(tree1, 6, 6)
        assertEquals(2, nodes[0])
        assertEquals(7, nodes[1])
        assertEquals(8, nodes[2])
    }

    @Test
    fun test2() {
        val tree1 = BinaryTree(1)
        val tree2 = BinaryTree(2)
        val tree3 = BinaryTree(3)
        val tree4 = BinaryTree(4)
        val tree5 = BinaryTree(5)
        val tree6 = BinaryTree(6)
        val tree7 = BinaryTree(7)
        val tree8 = BinaryTree(8)
        val tree9 = BinaryTree(9)

        tree1.left = tree2
        tree1.right = tree3

        tree2.left = tree4
        tree2.right = tree5

        tree3.left = tree6
        tree3.right = tree7

        tree4.left = tree8
        tree4.right = tree9

        val nodes = findNodesDistanceK(tree1, 8, 2)
        assertEquals(9, nodes[0])
        assertEquals(2, nodes[1])
    }

    open class BinaryTree(value: Int) {
        var value = value
        var left: BinaryTree? = null
        var right: BinaryTree? = null
        var parent: BinaryTree? = null
    }

    var targetSide = 0  // -1 left, 1 right

    fun findNodesDistanceK(tree: BinaryTree, target: Int, k: Int): List<Int> {
        val set = mutableSetOf<Int>()
        setParents(tree, null)

        val targetNode = getTarget(tree, target) ?: return emptyList()

        val listLeft = getChildDistance(targetNode.left, k - 1)
        val listRight = getChildDistance(targetNode.right, k - 1)

        var parent: BinaryTree? = targetNode.parent
            ?: return listLeft + listRight // already root

        var parentDistance = k - 1
        if (parentDistance == 0) return listLeft + listRight + listOf(parent!!.value)

        set.addAll(listLeft)
        set.addAll(listRight)

        var listSiblings = listOf<Int>()
        if (parentDistance >= 1) {
            if (targetSide == -1) {
                listSiblings = getChildDistance(parent?.right, parentDistance - 1)
            } else listSiblings = getChildDistance(parent?.left, parentDistance - 1)

        }

        set.addAll(listSiblings)

        while (parent?.parent != null && parentDistance > 0) {
            parent = parent.parent
            parentDistance--
        }

        if (parentDistance == 0) {    // we haven't crossed the root
            set.addAll(listOf(parent!!.value))
            return set.toList()
        } else {   // parentDistance is not zero and we're at the root
            var childList = listOf<Int>()
            childList = if (targetSide == -1) {   // target was on the left so now we go to right
                getChildDistance(parent!!.right, parentDistance - 1)
            } else {
                getChildDistance(parent!!.left, parentDistance - 1)
            }
            set.addAll(childList)
            return set.toList()
        }
    }

    private fun getChildDistance(node: BinaryTree?, distance: Int): List<Int> {
        if (node == null) return emptyList()
        if (distance == 0) return listOf(node.value)

        val listLeft = getChildDistance(node.left, distance - 1)
        val listRight = getChildDistance(node.right, distance - 1)

        return listLeft + listRight
    }

    private fun getTarget(node: BinaryTree?, target: Int): BinaryTree? {

        if (node == null) return null
        if (node.value == target) return node

        val left = getTarget(node.left, target)
        if (left != null) {
            targetSide = -1
            return left
        }

        val right = getTarget(node.right, target)
        targetSide = 1
        return right
    }

    private fun setParents(node: BinaryTree?, parent: BinaryTree?) {

        if (node == null) return

        if (parent != null)
            node.parent = parent

        setParents(node.left, node)
        setParents(node.right, node)
    }
}