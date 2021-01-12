package solutions.hackerRank.arrays

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * HackerRank challenge:
 * https://www.hackerrank.com/challenges/crush/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
 *
 * Sample input:
 * 5 3
 * 1 2 100
 * 2 5 100
 * 3 4 100
 *
 * Output: 200
 *
 * I couldn't solve this in O(n) and had to look for other submissions to get the idea.
 * Once you understand it, it feels so simple. That's the beauty of the question I suppose.
 */
class ArrayManipulation {

    @Test
    fun `get max`() {
        // Arrange
        val testData1 = arrayOf(arrayOf(1, 2, 100), arrayOf(2, 5, 100), arrayOf(3, 4, 100))
        val testData2 =
            arrayOf(arrayOf(1, 4, 20), arrayOf(2, 3, 15), arrayOf(5, 6, 2), arrayOf(1, 6, 10))

        // Act
        val result1 = `array manipulation O(n)`(5, testData1)
        val result2 = `array manipulation O(n)`(6, testData2)

        // Asserts
        assertEquals(200, result1)
        assertEquals(45, result2)

    }

    private fun `array manipulation O(n)`(n: Int, queries: Array<Array<Int>>): Long {

        val arr = LongArray(n + 2)
        var maxValue = 0L
        var sum = 0L

        queries.indices.forEach { index ->
            val start = queries[index][0]
            val end = queries[index][1]
            val k = queries[index][2]

            arr[start] = arr[start] + k
            arr[end + 1] = arr[end + 1] - k
        }

        arr.forEach { element ->
            sum += element
            if (sum > maxValue)
                maxValue = sum
        }

        return maxValue
    }

    private fun `array manipulation O(n2)`(n: Int, queries: Array<Array<Int>>): Long {

        var currentMax = 0L
        val array = mutableListOf<Int>()
        (0..n).forEach { _ -> array.add(0) }

        queries.forEach { row ->


            for (index in row[0] - 1 until row[1]) {
                array[index] += row[2]

                if (array[index] > currentMax)
                    currentMax = array[index].toLong()
            }
        }

        return currentMax
    }
}