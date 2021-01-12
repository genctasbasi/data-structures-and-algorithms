package solutions.hackerRank.trees

import com.escmobile.lab.domain.model.Node
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

/**
 * Tree Challenges
 * https://www.hackerrank.com/interview/interview-preparation-kit/trees/challenges
 * Kotlin implementation
 */
class IsThisABinarySearchTreeK : TreeTestBase() {

    @Test
    fun is_this_a_binary_search_tree() {

        // Arrange
        val arr = listOf(4, 2, 6, 1, 3, 5, 7)
        val node: Node? = buildBST(arr)

        // Act
        assertTrue(checkBST(node))

        // not BST anymore
        node?.left?.left = Node(20)
        assertFalse(checkBST(node))
    }

    private fun checkBST(root: Node?): Boolean {
        return checkBSTWithLimits(root, Int.MIN_VALUE, Int.MAX_VALUE)
    }

    private fun checkBSTWithLimits(root: Node?, min: Int, max: Int): Boolean {

        if (root == null) return true

        if (root.data < min || root.data > max) return false

        val isLeftValid = checkBSTWithLimits(root.left, min, root.data - 1)
        val isRightValid = checkBSTWithLimits(root.right, root.data, max)

        return isLeftValid && isRightValid
    }
}
