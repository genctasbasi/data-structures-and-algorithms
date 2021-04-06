package solutions.other

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * FB practice
 */
class RotationalCipher {

    @Test
    fun test() {
        val output = rotationalCipher("Zebraz-493?", 3)
        assertEquals("Cheudc-726?", output)
    }

    fun rotationalCipher(input: String, rotationFactor: Int): String {

        if (input.isEmpty()) return input

        val sb = StringBuilder()
        input.forEach {

            val add = it + rotationFactor

            when (it) {
                in ('a'..'z') -> sb.append(if (add > 'z') 'a' + (add - 'z' - 1) else add)
                in ('A'..'Z') -> sb.append(if (add > 'Z') 'A' + (add - 'Z' - 1) else add)
                in ('0'..'9') -> sb.append(if (add > '9') '0' + (add - '9' - 1) else add)
                else -> sb.append(it)
            }
        }

        return sb.toString()
    }
}