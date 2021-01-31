package solutions.leetcode.hard

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.*

/**
 * https://leetcode.com/problems/lfu-cache/
 * complexity: get: O(1), put: O(1)
 */
class LFUCacheOptimized {

    @Test
    fun test1() {
        val cache = LFUCache(2)
        cache.put(1, 1)
        cache.put(2, 2)
        assertEquals(1, cache.get(1))
        cache.put(3, 3)
        assertEquals(-1, cache.get(2))
        assertEquals(3, cache.get(3))
        cache.put(4, 4)
        assertEquals(-1, cache.get(1))
        assertEquals(3, cache.get(3))
        assertEquals(4, cache.get(4))
    }

    @Test
    fun test2() {
        val cache = LFUCache(2)
        cache.put(3, 1)
        cache.put(2, 1)
        cache.put(2, 2)
        cache.put(4, 4)
        assertEquals(2, cache.get(2))
    }

    @Test
    fun test3() {
        val cache = LFUCache(0)
        cache.put(0, 0)
        assertEquals(-1, cache.get(0))
    }

    class Node(
        val key: Int,
        var value: Int,
        var frequency: Int
    )

    class LFUCache(private val capacity: Int) {

        private val map = mutableMapOf<Int, Node>() // key: item key, value: Node
        private val mapFrequency = mutableMapOf<Int, Queue<Node>>() // key: frequency, value: Node

        fun get(key: Int): Int {
            val node = map[key] ?: return -1

            removeFromFrequencyList(node)

            // add to new queue
            node.frequency++
            if (mapFrequency[node.frequency] == null)
                mapFrequency[node.frequency] = LinkedList()

            mapFrequency[node.frequency]?.add(node)

            return node.value
        }

        fun put(key: Int, value: Int) {

            if (capacity == 0) return

            if (map[key] == null && map.size >= capacity) { // it's a new item and we don't have the space
                removeLFUItem()
            }

            val node = map[key] ?: Node(key, value, 0)

            // remove the node from both maps first (in case it isn't a new node but a node we're updating the value)
            map.remove(node.key)

            removeFromFrequencyList(node)

            node.frequency++
            node.value = value  // in case it isn't a a new node but just updating a value

            // put it back to both maps
            map[key] = node
            if (mapFrequency[node.frequency] == null)
                mapFrequency[node.frequency] = LinkedList()

            mapFrequency[node.frequency]?.add(node)
        }

        private fun removeLFUItem() {

            // get the minimum frequency we have now:
            val minFrequency = mapFrequency.keys.min()

            if (mapFrequency[minFrequency]?.isEmpty() == true) return

            minFrequency?.let {
                // removes the first in queue
                val node = removeFromFrequencyList(mapFrequency[minFrequency]?.peek())

                // remove from the map
                node?.let {
                    map.remove(it.key)
                }
            }
        }

        private fun removeFromFrequencyList(node: Node?): Node? {
            if (node == null) return null

            mapFrequency[node.frequency]?.remove(node)

            if (mapFrequency[node.frequency]?.isEmpty() == true)
                mapFrequency.remove(node.frequency)

            return node
        }
    }
}