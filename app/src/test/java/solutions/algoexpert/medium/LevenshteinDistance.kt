package solutions.algoexpert.medium

import org.junit.Assert
import org.junit.Test

/**
 * https://www.algoexpert.io/questions/Levenshtein%20Distance
 */
class LevenshteinDistance {

    @Test
    fun testCase1() {
        Assert.assertEquals(2, levenshteinDistance("abc", "yabd"))
    }

    @Test
    fun testCase2() {
        Assert.assertEquals(1, levenshteinDistance("abc", "abcx"))
    }

    @Test
    fun testCase3() {
        Assert.assertEquals(1, levenshteinDistance("abcd", "abc"))
    }

    @Test
    fun testCase4() {
        Assert.assertEquals(2, levenshteinDistance("xabc", "abcx"))
    }

    @Test
    fun testCase5() {
        Assert.assertEquals(4, levenshteinDistance("biting", "mitten"))
    }

    fun levenshteinDistance(str1: String, str2: String): Int {

        if (str1.isEmpty()) return str2.length
        if (str2.isEmpty()) return str1.length

        val arr = Array(str2.length) { Int.MAX_VALUE }
        val prevArr = Array(str2.length) { Int.MAX_VALUE }

        arr[0] = 0

        str1.forEachIndexed { _, char1 ->
            str2.forEachIndexed { index2, char2 ->

                val existingVal = arr[index2]
                val previousVal = if (index2 > 0) arr[index2 - 1] else Int.MAX_VALUE
                val previousDiagonalVal = if (index2 > 0) prevArr[index2 - 1] else Int.MAX_VALUE

                val min = Math.min(Math.min(existingVal, previousVal), previousDiagonalVal)

                val updatedValue = when {
                    char1 == char2 -> if (previousDiagonalVal == Int.MAX_VALUE)
                        if (previousVal == Int.MAX_VALUE) existingVal else previousVal else previousDiagonalVal
                    else -> min + 1
                }

                arr[index2] = updatedValue
            }

            arr.copyInto(prevArr)
        }

        return arr[str2.length - 1]
    }

    fun levenshteinDistance2(str1: String, str2: String): Int {

        if (str1.isEmpty()) return str2.length
        if (str2.isEmpty()) return str1.length

        var index1 = 0
        var index2 = 0
        var str1Insert = 0
        var str2Insert = 0

        var changeCount = 0

        do {

            val char1 = if (index1 < str1.length) str1.elementAt(index1) else null
            val char2 = if (index2 < str2.length) str2.elementAt(index2) else null

            if (char1 == null && char2 == null) break

            if (char1 == null) {
                index2++
                changeCount++
                continue
            }

            if (char2 == null) {
                index1++
                changeCount++
                continue
            }

            if (char1 != char2) {

                when {

                    str1.length + str1Insert == str2.length + str2Insert -> {
                        index1++
                        index2++
                    } // replace
                    str1.length > str2.length + str2Insert -> {
                        index1++    // like deleting from str1
                        str2Insert++
                    }
                    str1.length + str1Insert < str2.length -> {
                        index2++    // like inserting to str1
                        str1Insert++
                    }
                }
                changeCount++
            } else {
                index1++
                index2++
            }
        } while (char1 != null || char2 != null)

        return changeCount
    }
}