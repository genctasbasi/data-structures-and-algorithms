package solutions.algoexpert.medium

import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Sort%20Stack
 */
class SortStack {

    @Test
    fun test() {
        val input = mutableListOf(-5, 2, -2, 4, 3, 1)
        val expected = mutableListOf(-5, -2, 1, 2, 3, 4)
        val output = sortStack(input)
        assert(expected == output)
    }

    fun sortStack(stack: MutableList<Int>): MutableList<Int> {

        if (stack.isEmpty()) return mutableListOf()

        val top = stack.removeAt(stack.lastIndex)
        sortStack(stack)

        insert(stack, top)
        return stack
    }

    fun insert(stack: MutableList<Int>, item: Int) {

        if (stack.isEmpty() || stack.last() <= item) {
            stack.add(item)
            return
        }

        val top = stack.removeAt(stack.lastIndex)
        insert(stack, item)

        stack.add(top)
    }
}