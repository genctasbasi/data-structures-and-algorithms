package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Dijkstra's%20Algorithm
 */
class DijkstrasAlgorithm {

    @Test
    fun test() {
        val distances = dijkstrasAlgorithm(
            2, listOf(
                listOf(listOf(1, 7)),
                listOf(listOf(2, 6), listOf(3, 20), listOf(4, 3)),
                listOf(listOf(3, 14)),
                listOf(listOf(4, 2)),
                listOf(),
                listOf()
            )
        )

        assertEquals(listOf(0, 7, 13, 27, 10, -1), distances)
    }

    fun dijkstrasAlgorithm(start: Int, edges: List<List<List<Int>>>): List<Int> {

        val vertexCount = edges.size

        if (vertexCount == 0) return listOf()
        if (vertexCount == 1) return listOf(0)

        val visitedVertices = mutableListOf<Int>()
        val minDistancesFromStart = Array(vertexCount) { Int.MAX_VALUE }

        // from start index to index zero, distance is zero
        minDistancesFromStart[start] = 0

        while (visitedVertices.size < vertexCount) {

            val smallestUnvisitedDistanceIndex =
                getUnvisitedVertexWithSmallestDistance(minDistancesFromStart, visitedVertices)

            if (smallestUnvisitedDistanceIndex == Int.MAX_VALUE) {    // this means all vertices are visited already
                return minDistancesFromStart.map { if (it == Int.MAX_VALUE) -1 else it }
            }

            // add to visited vertices
            visitedVertices.add(smallestUnvisitedDistanceIndex)

            edges[smallestUnvisitedDistanceIndex].forEach {

                val destinationVertexIndex = it[0]
                val destinationDistanceFromCurrentIndex = it[1]
                val totalDistance =
                    minDistancesFromStart[smallestUnvisitedDistanceIndex] + destinationDistanceFromCurrentIndex

                // what is the existing distance for the destination we're looking at
                val existingDistance = minDistancesFromStart[destinationVertexIndex]

                if (totalDistance < existingDistance) { // then update it
                    minDistancesFromStart[destinationVertexIndex] = totalDistance
                }

            }
        }

        return minDistancesFromStart.map { if (it == Int.MAX_VALUE) -1 else it }
    }

    private fun getUnvisitedVertexWithSmallestDistance(
        minDistancesFromStart: Array<Int>,
        visitedVertices: MutableList<Int>
    ): Int {
        var smallestDistance = Int.MAX_VALUE
        var smallestDistanceIndex = Int.MAX_VALUE

        // smallest index that is not visited
        minDistancesFromStart.forEachIndexed { index, i ->
            if (visitedVertices.contains(index).not()) {
                if (i < smallestDistance) {
                    smallestDistance = i
                    smallestDistanceIndex = index
                }
            }
        }

        return smallestDistanceIndex
    }

}