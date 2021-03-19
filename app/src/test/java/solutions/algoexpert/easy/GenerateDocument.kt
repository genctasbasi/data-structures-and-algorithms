package solutions.algoexpert.easy

import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Generate%20Document
 */
class GenerateDocument {

    @Test
    fun TestCase1() {
        val characters = "Bste!hetsi ogEAxpelrt x "
        val document = "AlgoExpert is the Best!"
        val expected = true
        val output = generateDocument(characters, document)
        assert(expected == output)
    }

    fun generateDocument(characters: String, document: String): Boolean {

        if (document.isEmpty()) return true
        if (characters.isEmpty()) return false

        val map = hashMapOf<Char, Int>()

        characters.forEach {
            map[it] = (map[it] ?: 0) + 1
        }

        document.forEach {
            if ((map[it] ?: 0) <= 0) return false
            map[it] = (map[it] ?: 0) - 1
        }

        return true
    }
}