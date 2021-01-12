package solutions.crackingTheCodingInterview.chapter1

import junit.framework.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Chapter 1: Arrays and Strings
 * Question 1: Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structure
 */
class IsUnique {

    @Test
    fun `is unique test data`() {

        // Arrange
        val string1 = "here is a string..."
        val string2 = "it seamz-dIflcuLT"
        val string3 = "a"
        val string4 = ""

        // Assert
        assertFalse(`is unique with a list`(string1))
        assertTrue(`is unique with a list`(string2))
        assertTrue(`is unique with a list`(string3))
        assertTrue(`is unique with a list`(string4))

        assertFalse(`is unique with another string`(string1))
        assertTrue(`is unique with another string`(string2))
        assertTrue(`is unique with another string`(string3))
        assertTrue(`is unique with another string`(string4))

        assertFalse(`is unique with no other structure`(string1))
        assertTrue(`is unique with no other structure`(string2))
        assertTrue(`is unique with no other structure`(string3))
        assertTrue(`is unique with no other structure`(string4))

        assertFalse(`is unique with sorting`(string1))
        assertTrue(`is unique with sorting`(string2))
        assertTrue(`is unique with sorting`(string3))
        assertTrue(`is unique with sorting`(string4))
    }

    /**
     * This is O(n) complexity, O(n) space
     */
    private fun `is unique with a list`(value: String): Boolean {

        val chars = mutableListOf<Char>()

        value.forEach {
            if (chars.contains(it)) return false
            chars.add(it)
        }

        return true
    }

    /**
     * This is O(n) complexity, O(n) space
     */
    private fun `is unique with another string`(value: String): Boolean {

        var charsFound = ""

        value.forEach {

            if (charsFound.contains(it)) return false
            charsFound += it
        }

        return true
    }

    /**
     * This is O(n2) complexity, 1 space
     */
    private fun `is unique with no other structure`(value: String): Boolean {

        value.forEachIndexed { index, c ->
            if (index == value.length - 1) return true

            val rest = value.substring(index + 1)
            if (rest.contains(c)) return false
        }

        return true
    }

    /**
     * This is O(nlog(n)) complexity as there is a sorting, 1 space
     * Sorting - always remember!
     */
    private fun `is unique with sorting`(value: String): Boolean {

        var currentChar = -1

        val sorted = value.chars().sorted()

        sorted.toArray().forEach {
            if (currentChar == it) return false
            currentChar = it
        }

        return true
    }
}