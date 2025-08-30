package solutions.other

import org.junit.Test
import java.util.LinkedList

class LRUGeneric {
    @Test
    fun testLRUGen() {
        val cache = LRUGenImpl<String>(capacity = 3)
    }
}
class LRUGenImpl<T>(val capacity: Int) : LRUGen<T> {

    private val map = mutableMapOf<String, CacheNode<T>>()

    private var head: CacheNode<T>? = null
    private var tail: CacheNode<T>? = null

    override fun put(key: String, value: T) {

        // 3 scenarios:
        // 1- map already has the key
        // 2- map doesn't have the key, has capacity
        // 3- map doesn't have the key, has no capacity

        when {

            map[key] != null -> {
                map[key]?.value = value
                makeHead(map[key])
            }

        }

        if (map.size < capacity) {
            val newNode = CacheNode(key = key, value = value)
            map[key] = newNode
            makeHead(newNode)

        } else {    // no capacity

            // TODO: remove tail
        }


        // TODO: make head newNode
    }

    private fun makeHead(value: CacheNode<T>?) {
        if (value == null) return

    }

    override fun get(key: String): T? {
        return map[key]?.value
    }

    override fun delete(key: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun reset() {
        map.clear()
    }

    internal data class CacheNode<T>(
        val key: String,
        var value: T,
        val prev: CacheNode<T>? = null,
        val next: CacheNode<T>? = null
    )
}

interface LRUGen<T> {
    fun put(key: String, value: T)
    fun get(key: String): T?
    fun delete(key: String): Boolean
    fun reset()
}