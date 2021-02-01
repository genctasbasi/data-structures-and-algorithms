package solutions.leetcode.easy

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * https://leetcode.com/problems/logger-rate-limiter/
 */
class LoggerRateLimiter {

    @Test
    fun test() {

        val logger = Logger()
        assertEquals(true, logger.shouldPrintMessage(1, "foo"))
        assertEquals(true, logger.shouldPrintMessage(2, "bar"))
        assertEquals(false, logger.shouldPrintMessage(3, "foo"))
        assertEquals(false, logger.shouldPrintMessage(8, "bar"))
        assertEquals(false, logger.shouldPrintMessage(10, "foo"))
        assertEquals(true, logger.shouldPrintMessage(11, "foo"))
    }

    class Logger() {

        /** Initialize your data structure here. */

        val map = mutableMapOf<String, Int>()

        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
        fun shouldPrintMessage(timestamp: Int, message: String): Boolean {

            if (map[message] == null) {
                map[message] = timestamp
                return true
            }

            if (timestamp - map[message]!! >= 10) {
                map[message] = timestamp
                return true
            }

            return false
        }
    }
}