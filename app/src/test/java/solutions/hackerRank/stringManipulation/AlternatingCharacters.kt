package solutions.hackerRank.stringManipulation

/**
 * https://www.hackerrank.com/challenges/alternating-characters/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=strings
 */
class AlternatingCharacters {

    fun alternatingCharacters(s: String): Int {
        var prevChar = Char.MIN_VALUE
        var deleteCount = 0
        s.toCharArray().forEach { currentChar ->

            if (currentChar == prevChar) {
                deleteCount++
            }

            prevChar = currentChar
        }

        return deleteCount
    }
}
