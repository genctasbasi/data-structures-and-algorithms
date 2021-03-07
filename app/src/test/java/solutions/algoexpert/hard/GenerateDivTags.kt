package solutions.algoexpert.hard

import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Generate%20Div%20Tags
 *
 * Complexity?
 *
 */
class GenerateDivTags {

    @Test
    fun testCase1() {
        val input = 3
        val expected = listOf(
            "<div><div><div></div></div></div>",
            "<div><div></div><div></div></div>",
            "<div><div></div></div><div></div>",
            "<div></div><div><div></div></div>",
            "<div></div><div></div><div></div>"
        )
        val output = generateDivTags(input)
        assert(expected == output)
    }

    fun generateDivTags(numberOfTags: Int): List<String> {
        val output = mutableListOf<String>()
        helper("", numberOfTags, numberOfTags, output)
        return output
    }

    fun helper(
        sb: String,
        opening: Int,
        closing: Int,
        output: MutableList<String>
    ) {

        if (closing == 0) {
            output.add(sb)
        }

        if (opening > 0) {
            val nsb = "$sb<div>"
            helper(nsb, opening - 1, closing, output)
        }

        if (opening < closing) {
            val nsb = "$sb</div>"
            helper(nsb, opening, closing - 1, output)
        }
    }
}