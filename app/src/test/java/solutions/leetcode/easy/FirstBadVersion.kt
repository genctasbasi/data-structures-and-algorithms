package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/first-bad-version/
 */
class FirstBadVersion {

    @Test
    fun test() {
        val sol = Solution()
        val version = sol.firstBadVersion(2126753390)
        assertEquals(1702766719, version)
    }

    interface VersionControl {
        fun isBadVersion(version: Int): Boolean
    }

    class Solution : VersionControl {

        fun firstBadVersion(n: Int): Int {
            return helper(0, n.toLong())
        }

        fun helper(start: Long, end: Long): Int {

            if (start >= end) return start.toInt()

            val mid = (start + end) / 2
            print("$start, $end, $mid; ")

            val isBadVersion = isBadVersion(mid.toInt())

            return if (isBadVersion) {
                helper(start, mid)
            } else {
                helper(mid + 1, end)
            }
        }

        override fun isBadVersion(version: Int): Boolean {
            return version >= 1702766719
        }
    }

}