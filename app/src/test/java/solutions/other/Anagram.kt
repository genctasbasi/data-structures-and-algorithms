package solutions.other

/**
 * https://www.geeksforgeeks.org/problems/anagram-1587115620/1
 *
 * Given two strings a and b consisting of lowercase characters.
 * The task is to check whether two given strings are an anagram of each other or not.
 *
 * An anagram of a string is another string that contains the same characters,
 * only the order of characters can be different.
 * For example, act and tac are an anagram of each other.
 *
 */
class Anagram {

    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val list = s.toMutableList()

        t.forEach {
            list.remove(it)
        }

        return list.isEmpty()

        // could be just
        // return s.toList().sorted() == t.toList().sorted()
    }
}
