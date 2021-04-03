package solutions.leetcode.medium

import org.junit.Assert.assertArrayEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/strobogrammatic-number-ii/
 * I studied the solution from:
 *  https://leetcode.com/problems/strobogrammatic-number-ii/discuss/358470/Java-easy-recursive-solution-with-explanation
 */
class StrobogrammaticNumberII {

    @Test
    fun test() {
        val result = findStrobogrammatic(2).toTypedArray()
        assertArrayEquals(arrayOf("11", "69", "88", "96"), result)
    }

    @Test
    fun test1() {
        val result = findStrobogrammatic(3).toTypedArray()
        assertArrayEquals(
            arrayOf(
                "101",
                "808",
                "609",
                "906",
                "111",
                "818",
                "619",
                "916",
                "181",
                "888",
                "689",
                "986"
            ), result
        )
    }

    fun findStrobogrammatic(n: Int): List<String> {

        val output = mutableListOf<String>()

        if (n.rem(2) == 1) {  // odd
            helper(output, "0", n, 1)
            helper(output, "1", n, 1)
            helper(output, "8", n, 1)
        } else {
            helper(output, "", n, 0)
        }

        return output.filterNot { it.length > 1 && it.startsWith('0') }
    }

    fun helper(output: MutableList<String>, root: String, n: Int, length: Int) {

        if (length == n) {
            output.add(root)
            return
        }

        helper(output, "0${root}0", n, length + 2)
        helper(output, "1${root}1", n, length + 2)
        helper(output, "8${root}8", n, length + 2)
        helper(output, "6${root}9", n, length + 2)
        helper(output, "9${root}6", n, length + 2)
    }
}