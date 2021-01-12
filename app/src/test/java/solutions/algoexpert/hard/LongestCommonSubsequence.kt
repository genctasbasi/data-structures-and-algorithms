package solutions.algoexpert.hard

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Longest%20Common%20Subsequence
 */
class LongestCommonSubsequence {

    @Test
    fun test() {
        val subsequence = longestCommonSubsequence("ZXVVYZW", "XKYKZPW")
        assertEquals(listOf("X", "Y", "Z", "W"), subsequence)
    }

    @Test
    fun test2() {
        val subsequence = longestCommonSubsequence("ABCDEFG", "APPLES")
        assertEquals(listOf("A", "E"), subsequence)
    }

    fun longestCommonSubsequence(str1: String, str2: String): List<Char> {

        if (str1.isBlank() || str2.isBlank()) return emptyList()
        if (str1.contains(str2)) return str2.toCharArray().toList()
        if (str2.contains(str1)) return str1.toCharArray().toList()

        val subsequence = Array<String?>(Math.min(str1.length, str2.length)) { null }
        val subsequencePrev = Array<String?>(Math.min(str1.length, str2.length)) { null }

        for (i in str2.indices) {

            val char2 = str2[i]

            for (j in str1.indices) {

                if (j >= subsequence.size) continue

                val char1 = str1[j]

                if (char1 == char2) {
                    val diagonalChar = if (j > 0) subsequencePrev[j - 1] else null
                    val newVal = (diagonalChar ?: "") + char1
                    subsequence[j] = newVal
                } else {
                    // get max (longest) of minus char1 and minus char2
                    if (j == 0)
                        subsequence[j] = subsequencePrev[j]
                    else {
                        val length1 = (subsequencePrev[j] ?: "").length
                        val length2 = (subsequence[j - 1] ?: "").length

                        subsequence[j] =
                            if (length1 > length2) subsequencePrev[j] else subsequence[j - 1]
                    }
                }
            }

            subsequence.copyInto(subsequencePrev)
        }

        return subsequence.last()?.toCharArray()?.toList() ?: emptyList()
    }

}