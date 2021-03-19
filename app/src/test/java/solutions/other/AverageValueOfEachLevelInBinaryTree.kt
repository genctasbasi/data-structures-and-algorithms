package solutions.other

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Given a binary tree, get the average value at each level
 */
class AverageValueOfEachLevelInBinaryTree {

    lateinit var root: Node

    @Before
    fun setup() {
        val node4 = Node(4)
        val node7 = Node(7)
        val node9 = Node(9)
        val node10 = Node(10)
        val node2 = Node(2)
        val node6 = Node(6)
        val node6_2 = Node(6)
        val node2_2 = Node(2)

        node4.left = node7
        node4.right = node9

        node7.left = node10
        node7.right = node2

        node9.right = node6
        node2.right = node6_2
        node6_2.left = node2_2

        root = node4
    }

    @Test
    fun test() {
        assertEquals(listOf(4.0, 8.0, 6.0, 6.0, 2.0), getAverage(root))
    }

    open class Node(val value: Int) {
        var left: Node? = null
        var right: Node? = null
    }

    fun getAverage(node: Node?): List<Double> {
        val map = hashMapOf<Int, MutableList<Double>>()
        helper(node, 0, map)
        return map.values.map {
            it.sum() / it.count()
        }
    }

    // map key: level, value: average
    fun helper(node: Node?, level: Int, map: HashMap<Int, MutableList<Double>>) {

        if (node == null) return

        if (map[level] == null)
            map[level] = mutableListOf(node.value.toDouble())
        else {
            map[level]!!.add(node.value.toDouble())
        }

        helper(node.left, level + 1, map)
        helper(node.right, level + 1, map)
    }
}