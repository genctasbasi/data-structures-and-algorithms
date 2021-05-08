package solutions.leetcode.medium

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/
 */
class FindKLengthSubstringsWithNoRepeatedCharacters {

    @Test
    fun test() {
        val result = numKLenSubstrNoRepeats("havefunonleetcode", 5)
        assertEquals(6, result)
    }

    fun numKLenSubstrNoRepeats(S: String, K: Int): Int {

        if (S.length < K) return 0

        var winStart = 0
        var winEnd = 0
        var uniqueCount = 0
        val map = mutableMapOf<Char, Int>()
        while (winEnd <= S.lastIndex) {

            val char = S[winEnd]
            map[char] = (map[char] ?: 0) + 1

            if (winEnd >= K - 1) {

                if (map.size == K) {
                    uniqueCount++
                }

                val startChar = S[winStart]
                map[startChar] = map[startChar]!! - 1

                if (map[startChar] == 0) map.remove(startChar)

                winStart++
            }
            winEnd++
        }

        return uniqueCount
    }

}