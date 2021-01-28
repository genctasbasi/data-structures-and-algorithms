package solutions.other

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

/**
 * Return the given level nodes of a binary tree
 */
class BinaryTreeSameLevelNodes {

    lateinit var root: Node

    @Before
    fun setup() {
        val node1 = Node(1)
        val node2 = Node(2)
        val node3 = Node(3)
        val node4 = Node(4)
        val node5 = Node(5)
        val node6 = Node(6)
        val node7 = Node(7)

        node1.left = node2
        node1.right = node3

        node2.left = node4
        node2.right = node5

        node4.left = node6

        node3.right = node7

        root = node1
    }

    @Test
    fun test1() {
        val result = getNodes(root, 0)
        TestCase.assertEquals(listOf(1), result)
    }

    @Test
    fun test2() {
        val result = getNodes(root, 1)
        TestCase.assertEquals(listOf(2, 3), result)
    }

    @Test
    fun test3() {
        val result = getNodes(root, 2)
        TestCase.assertEquals(listOf(4, 5, 7), result)
    }

    open class Node(val value: Int) {
        var left: Node? = null
        var right: Node? = null
    }

    private fun getNodes(root: Node, level: Int): List<Int> {

        val list = mutableListOf<Int>()
        getNodesHelper(root, 0, level, list)
        return list
    }

    private fun getNodesHelper(root: Node?, currentLevel: Int, level: Int, map: MutableList<Int>) {

        if (root == null) return
        if (currentLevel > level) return

        if (currentLevel == level)
            map.add(root.value)

        getNodesHelper(root.left, currentLevel + 1, level, map)
        getNodesHelper(root.right, currentLevel + 1, level, map)
    }

}