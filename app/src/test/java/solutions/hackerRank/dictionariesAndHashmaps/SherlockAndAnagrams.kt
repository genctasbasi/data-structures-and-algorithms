package solutions.hackerRank.dictionariesAndHashmaps

import org.junit.Assert
import org.junit.Test

/**
 * https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
 *
 * TODO: I scored 20 out of 50 for this, it works but slow. Need to come back to this.
 *
 * TODO: abba -> create a list of each combination:
 * - a
 * - ab
 * - abb
 * - abba
 * - b
 * - bb
 * - bba
 * - b
 * - ba
 * - a
 *
 * Then, loop the list and compare each element with the rest of the list, to see if there is a combination that matches.
 * For example bba matches with abb because
 * 1- bba comes after abb
 * 2- they have same size & characters
 */
class SherlockAndAnagrams {

    @Test
    fun `test cases`() {
        // Assert.assertEquals(4, sherlockAndAnagrams("abba"))
        // Assert.assertEquals(0, sherlockAndAnagrams("abcd"))
        Assert.assertEquals(3, sherlockAndAnagrams("ifailuhkqq"))
        Assert.assertEquals(
            840,
            sherlockAndAnagrams("mhmgmbbccbbaffhbncgndbffkjbhmkfncmihhdhcebmchnfacdigflhhbekhfejblegakjjiejeenibemfmkfjbkkmlichlkbnhc")
        )
        // Assert.assertEquals(166650, sherlockAndAnagrams("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"))
        // Assert.assertEquals(1464, sherlockAndAnagrams("dbcfibibcheigfccacfegicigcefieeeeegcghggdheichgafhdigffgifidfbeaccadabecbdcgieaffbigffcecahafcafhcdg"))
        // Assert.assertEquals(10, sherlockAndAnagrams("kkkk"))
    }

    private fun sherlockAndAnagrams(s: String): Int {

        var totalCount = 0
        var searchString = s
        s.forEachIndexed { index, c ->

            searchString = s.substring(index + 1)
            for (readAmount in 1..(searchString.length + 1)) {
                val read = s.substring(index, index + readAmount)

                val countInSearchString = count(read, searchString)

                totalCount += countInSearchString
            }
        }

        return totalCount
    }

    private fun count(read: String, substring: String): Int {

        var count = 0
        for (i in 0..(substring.length - read.length)) {

            val subRead = substring.substring(i, i + read.length)

            val sorted = subRead.toCharArray().sorted()
            val readSorted = read.toCharArray().sorted()

            if (sorted == readSorted) {
                count++
            }
        }

        return count
    }
}