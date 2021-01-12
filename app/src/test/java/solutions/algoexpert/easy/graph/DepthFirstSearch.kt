package solutions.algoexpert.easy.graph

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import solutions.algoexpert._utils.Helper
import solutions.algoexpert._utils.Node

/**
 * https://www.algoexpert.io/questions/Depth-first%20Search
 *
 * Complexity: O(v+e): O(v) because we visit each node
 *
 * Space: O(v+e). O(v) because we add each vertex to the list. O(e) because of ?? I don't understand that part.
 * I think O(e) is the worst case scenario (where the graph is a single branch). On average it must be O(d) where d is the depth
 * there fore the space complexity on average becoming O(v+d)
 */
class DepthFirstSearch {

    lateinit var node: Node

    @Before
    fun setup() {
        // TODO: build the node first, then it'll pass
        node = Helper.buildNode()
    }

    @Test
    fun test() {
        assertEquals(
            listOf("A", "B", "E", "F", "I", "J", "C", "D", "G", "K", "H"),
            node.depthFirstSearch()
        )
    }

}