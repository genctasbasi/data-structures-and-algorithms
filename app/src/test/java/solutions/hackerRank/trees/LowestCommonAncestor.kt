package solutions.hackerRank.trees

import com.escmobile.lab.domain.model.Node
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.junit.Test

/**
 * Tree Challenges
 * https://www.hackerrank.com/interview/interview-preparation-kit/trees/challenges
 *
 */
class LowestCommonAncestor : TreeTestBase() {
    @Test
    fun bst_lowest_common_ancestor() {
        // Arrange
        val arr = arrayOf(4, 2, 3, 1, 7, 6)

        val number1 = 1
        val number2 = 7

        var node: Node? = null
        arr.forEach {
            node = insertNode(node, it)
        }

        val routeToNumber1 = getRoute(node, number1)    // 1, 2, 4
        val routeToNumber2 = getRoute(node, number2)    // 7, 4

        var lca: Node? = null

        routeToNumber1.forEach { node1 ->
            if (routeToNumber2.any { node2 -> node2.data == node1.data }) {
                lca = node1
                return
            }
        }

        assertNotNull(lca)
        assertTrue(lca?.data == 4)
    }

    private fun getRoute(root: Node?, number: Int): MutableList<Node> {
        if (root == null) return mutableListOf()

        if (root.data == number) {
            return mutableListOf(Node(number))
        }

        if (number < root.data) {
            val listLeft = getRoute(root.left, number)
            return listLeft.plus(Node(root.data)).toMutableList()
        }

        val listRight = getRoute(root.right, number)
        return listRight.plus(Node(root.data)).toMutableList()
    }
}
