package solutions.leetcode.hard

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/lfu-cache/
 * complexity: get: O(1), put: O(n)
 */
class LFUCache {

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

    class Node(val key: Int, var value: Int, var frequency: Int, var counter: Int)

    class LFUCache(private val capacity: Int) {

        var counter = 0
        val map = mutableMapOf<Int, Node>()

        fun get(key: Int): Int {
            val node = map[key] ?: return -1

            node.frequency++
            node.counter = counter++

            return node.value
        }

        fun put(key: Int, value: Int) {

            if (capacity == 0) return

            if (map[key] == null && map.size >= capacity) { // it's a new item and we don't have the space
                removeLFUItem()
            }

            val node = map[key] ?: Node(key, value, 0, 0)
            node.frequency++
            node.counter = counter++
            node.value = value

            map[key] = node
        }

        /**
         * an idiomatic one
         */
        private fun removeLFUItem() {
            val key = map.values.minWith(compareBy({ it.frequency }, { it.counter }))
            key.let {
                map.remove(it.key)
            }
        }

        private fun removeLFUItem2() {
            var lfu: Node? = null
            var minFrequency = Int.MAX_VALUE
            var minCount = Int.MAX_VALUE

            map.values.forEach {
                if (it.frequency < minFrequency) {
                    lfu = it
                    minFrequency = it.frequency
                    minCount = it.counter
                } else if (it.frequency == minFrequency) {
                    if (it.counter < minCount) {
                        lfu = it
                        minCount = it.counter
                    }
                }
            }

            lfu?.let {
                map.remove(it.key)
            }
        }
    }
}