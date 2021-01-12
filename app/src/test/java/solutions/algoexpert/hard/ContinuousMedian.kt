package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Continuous%20Median
 */
class ContinuousMedian {

    @Test
    fun test() {

        val c = ContinuousMedianHandler()
        c.insert(5)
        c.insert(10)
        assertEquals(7.5, c.getMedian())

        c.insert(100)
        assertEquals(10.0, c.getMedian())
    }

    class Node(val value: Int, var next: Node? = null)

    open class ContinuousMedianHandler {

        private var median: Double? = null

        private var root: Node? = null

        private var itemSize = 0

        fun insert(number: Int) {
            if (root == null) {
                root = Node(number)
                onItemAdded()
                return
            }

            var currentNode: Node? = root!!
            while (currentNode != null) {

                if (currentNode.next == null) {
                    currentNode.next = Node(number) // adding to the end
                    onItemAdded()
                    break
                } else {
                    if (currentNode.next!!.value > number) {
                        val newNode = Node(number, currentNode.next)
                        currentNode.next = newNode
                        onItemAdded()
                        break
                    }
                }

                currentNode = currentNode.next
            }
        }

        private fun onItemAdded() {
            itemSize++

            var count = 0
            var node = root

            if (itemSize.rem(2) == 1) {
                val index = ((itemSize + 1) / 2) - 1
                while (count < index) {
                    node = node?.next
                    count++
                }

                median = node?.value?.toDouble()

            } else {
                val index = ((itemSize + 1) / 2) - 1

                while (count < index) {
                    node = node?.next
                    count++
                }

                val val1: Double = (node?.value?.toDouble() ?: 0.0)
                val val2: Double = (node?.next?.value?.toDouble() ?: 0.0)
                median = (val1 + val2) / 2
            }
        }

        fun getMedian(): Double? {
            return this.median
        }
    }

    open class ContinuousMedianHandlerNLogN() {

        private var median: Double? = null
        private var numbers = mutableListOf<Int>()
        private var pointer1 = 0
        private var pointer2 = 0

        fun insert(number: Int) {
            numbers.add(number)
            numbers.sort()  // O(nlog(n))

            median =
                when (numbers.size) {
                    0 -> null
                    1 -> numbers[0].toDouble()
                    else -> {

                        if (numbers.size.rem(2) == 1) {
                            val index = ((numbers.size + 1) / 2) - 1
                            numbers[index].toDouble()
                        } else {
                            val up = ((numbers.size + 1) / 2)
                            val down = up - 1
                            ((numbers[up].toDouble() + numbers[down].toDouble()) / 2)
                        }
                    }
                }
        }

        fun getMedian(): Double? {
            return this.median
        }
    }

}