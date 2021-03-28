package solutions.leetcode.easy

/**
 * https://leetcode.com/problems/read-n-characters-given-read4/
 */
interface Reader4 {
    fun read4(buf4: CharArray): Int
}

class ReadNCharactersGivenRead4 : Reader4 {

    override fun read4(buf4: CharArray): Int {
        return 0
    }

    fun read(buf: CharArray, n: Int): Int {

        if (n <= 4) {
            val read = read4(buf)
            return Math.min(n, read)
        }

        var read = read4(buf)
        var index = read
        val temp = CharArray(n)
        buf.copyInto(temp)

        while (read != 0) {

            read = read4(buf)

            for (i in 0..read) {
                temp[index + i] = buf[i]
            }

            index += read
        }

        temp.copyInto(buf)
        return n
    }

}