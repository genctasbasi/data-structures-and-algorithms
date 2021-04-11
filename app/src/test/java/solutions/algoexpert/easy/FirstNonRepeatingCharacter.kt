package solutions.algoexpert.easy

import org.junit.Test

/**
 * https://www.algoexpert.io/questions/First%20Non-Repeating%20Character
 */
class FirstNonRepeatingCharacter {

    @Test
    fun test() {
        val input = "abcdcaf"
        val expected = 1
        val output = firstNonRepeatingCharacter(input)
        assert(expected == output)
    }

    fun firstNonRepeatingCharacter(string: String): Int {

        val map = LinkedHashMap<Char, Int>()
        string.forEach {
            map[it] = (map[it] ?: 0) + 1
        }

        map.forEach { entry ->
            if (entry.value == 1) return string.indexOf(entry.key)
        }

        return -1
    }
}