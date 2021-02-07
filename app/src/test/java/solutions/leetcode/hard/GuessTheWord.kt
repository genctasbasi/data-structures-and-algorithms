package solutions.leetcode.hard

import org.junit.Test

/**
 * https://leetcode.com/problems/guess-the-word/
 * The 'top' Google interview question according to Leetcode.
 */
class GuessTheWord {

    @Test
    fun test1() {
        val sol = Solution()
        val master = object : Master {
            override fun guess(word: String): Int {
                return 3    // cannot mock this more, needs to be resolved on LC
            }
        }

        val a = arrayOf(
            "gaxckt",
            "trlccr",
            "jxwhkz",
            "ycbfps",
            "peayuf",
            "yiejjw",
            "ldzccp",
            "nqsjoa",
            "qrjasy",
            "pcldos",
            "acrtag",
            "buyeia",
            "ubmtpj",
            "drtclz",
            "zqderp",
            "snywek",
            "caoztp",
            "ibpghw",
            "evtkhl",
            "bhpfla",
            "ymqhxk",
            "qkvipb",
            "tvmued",
            "rvbass",
            "axeasm",
            "qolsjg",
            "roswcb",
            "vdjgxx",
            "bugbyv",
            "zipjpc",
            "tamszl",
            "osdifo",
            "dvxlxm",
            "iwmyfb",
            "wmnwhe",
            "hslnop",
            "nkrfwn",
            "puvgve",
            "rqsqpq",
            "jwoswl",
            "tittgf",
            "evqsqe",
            "aishiv",
            "pmwovj",
            "sorbte",
            "hbaczn",
            "coifed",
            "hrctvp",
            "vkytbw",
            "dizcxz",
            "arabol",
            "uywurk",
            "ppywdo",
            "resfls",
            "tmoliy",
            "etriev",
            "oanvlx",
            "wcsnzy",
            "loufkw",
            "onnwcy",
            "novblw",
            "mtxgwe",
            "rgrdbt",
            "ckolob",
            "kxnflb",
            "phonmg",
            "egcdab",
            "cykndr",
            "lkzobv",
            "ifwmwp",
            "jqmbib",
            "mypnvf",
            "lnrgnj",
            "clijwa",
            "kiioqr",
            "syzebr",
            "rqsmhg",
            "sczjmz",
            "hsdjfp",
            "mjcgvm",
            "ajotcx",
            "olgnfv",
            "mjyjxj",
            "wzgbmg",
            "lpcnbj",
            "yjjlwn",
            "blrogv",
            "bdplzs",
            "oxblph",
            "twejel",
            "rupapy",
            "euwrrz",
            "apiqzu",
            "ydcroj",
            "ldvzgq",
            "zailgu",
            "xgqpsr",
            "wxdyho",
            "alrplq",
            "brklfk"
        )
        val result = sol.findSecretWord(a, master)
    }

    interface Master {
        fun guess(word: String): Int
    }

    class Solution {

        fun findSecretWord(wordList: Array<String>, master: Master) {

            var count = 0
            var options = wordList.toMutableList()

            while (count < 10) {    // 10 guesses

                val map = mutableMapOf<String, Int>()

                // count the zeros
                options.forEach { word1 ->
                    options.forEach { word2 ->

                        val matchCount = getMatchCount(word1, word2)
                        if (matchCount == 0)
                            map[word1] = (map[word1] ?: 0) + 1

                    }
                }

                val minZeroWord = map.minBy { it.value }?.key ?: options.firstOrNull() ?: return
                val targetMatchCount = master.guess(minZeroWord)

                if (targetMatchCount == 6) return

                val newOptions = mutableListOf<String>()
                options.forEach { word ->
                    val matchCount = getMatchCount(minZeroWord, word)
                    if (matchCount == targetMatchCount)
                        newOptions.add(word)
                }

                options = newOptions
                count++
            }
        }

        private fun getMatchCount(w1: String, w2: String): Int {

            var count = 0
            for (index in 0..w1.lastIndex) {
                if (w1[index] == w2[index])
                    count++
            }

            return count
        }
    }
}