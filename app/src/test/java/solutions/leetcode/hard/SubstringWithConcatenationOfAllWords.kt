package solutions.leetcode.hard

import junit.framework.TestCase
import org.junit.Test

/**
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 *
 * That's an interesting one. My solution works for the reasonable test cases, not the 5th one below (the longest).
 * Initially I thought I should build a suffix trie but that didn't work because I needed the index.
 */
class SubstringWithConcatenationOfAllWords {

    @Test
    fun test() {
        val result = findSubstring("barfoothefoobarman", arrayOf("foo", "bar"))
        TestCase.assertEquals(listOf(0, 9), result)
    }

    @Test
    fun test2() {
        val result = findSubstring("banaxyzban123na", arrayOf("ban", "xyz", "123"))
        TestCase.assertEquals(listOf(0, 9), result)
    }

    @Test
    fun test3() {
        val result = findSubstring("foobarfoobar", arrayOf("foo", "bar"))
        TestCase.assertEquals(listOf(0, 3, 6), result)
    }

    @Test
    fun test4() {
        val result = findSubstring("aaa", arrayOf("a", "a"))
        TestCase.assertEquals(listOf(0, 1), result)
    }

    /**
     * That didn't work as I'm getting the all possible combinations of words array and
     * Leetcode gives memory exception even for 11 words (which is 11! records!).
     *
     * So what I've learnt here: I should have checked the possible number of n in the requirements,
     * which was 30 - meaning 30! options if I wanted to get all combinations beforehand - wouldn't work!
     */
    @Test
    fun test5() {
        val result = findSubstring("pjzkrkevzztxductzzxmxsvwjkxpvukmfjywwetvfnujhweiybwvvsrfequzkhossmootkmyxgjgfordrpapjuunmqnxxdrqrfgkrsjqbszgiqlcfnrpjlcwdrvbumtotzylshdvccdmsqoadfrpsvnwpizlwszrtyclhgilklydbmfhuywotjmktnwrfvizvnmfvvqfiokkdprznnnjycttprkxpuykhmpchiksyucbmtabiqkisgbhxngmhezrrqvayfsxauampdpxtafniiwfvdufhtwajrbkxtjzqjnfocdhekumttuqwovfjrgulhekcpjszyynadxhnttgmnxkduqmmyhzfnjhducesctufqbumxbamalqudeibljgbspeotkgvddcwgxidaiqcvgwykhbysjzlzfbupkqunuqtraxrlptivshhbihtsigtpipguhbhctcvubnhqipncyxfjebdnjyetnlnvmuxhzsdahkrscewabejifmxombiamxvauuitoltyymsarqcuuoezcbqpdaprxmsrickwpgwpsoplhugbikbkotzrtqkscekkgwjycfnvwfgdzogjzjvpcvixnsqsxacfwndzvrwrycwxrcismdhqapoojegggkocyrdtkzmiekhxoppctytvphjynrhtcvxcobxbcjjivtfjiwmduhzjokkbctweqtigwfhzorjlkpuuliaipbtfldinyetoybvugevwvhhhweejogrghllsouipabfafcxnhukcbtmxzshoyyufjhzadhrelweszbfgwpkzlwxkogyogutscvuhcllphshivnoteztpxsaoaacgxyaztuixhunrowzljqfqrahosheukhahhbiaxqzfmmwcjxountkevsvpbzjnilwpoermxrtlfroqoclexxisrdhvfsindffslyekrzwzqkpeocilatftymodgztjgybtyheqgcpwogdcjlnlesefgvimwbxcbzvaibspdjnrpqtyeilkcspknyylbwndvkffmzuriilxagyerjptbgeqgebiaqnvdubrtxibhvakcyotkfonmseszhczapxdlauexehhaireihxsplgdgmxfvaevrbadbwjbdrkfbbjjkgcztkcbwagtcnrtqryuqixtzhaakjlurnumzyovawrcjiwabuwretmdamfkxrgqgcdgbrdbnugzecbgyxxdqmisaqcyjkqrntxqmdrczxbebemcblftxplafnyoxqimkhcykwamvdsxjezkpgdpvopddptdfbprjustquhlazkjfluxrzopqdstulybnqvyknrchbphcarknnhhovweaqawdyxsqsqahkepluypwrzjegqtdoxfgzdkydeoxvrfhxusrujnmjzqrrlxglcmkiykldbiasnhrjbjekystzilrwkzhontwmehrfsrzfaqrbbxncphbzuuxeteshyrveamjsfiaharkcqxefghgceeixkdgkuboupxnwhnfigpkwnqdvzlydpidcljmflbccarbiegsmweklwngvygbqpescpeichmfidgsjmkvkofvkuehsmkkbocgejoiqcnafvuokelwuqsgkyoekaroptuvekfvmtxtqshcwsztkrzwrpabqrrhnlerxjojemcxel", arrayOf("dhvf","sind","ffsl","yekr","zwzq","kpeo","cila","tfty","modg","ztjg","ybty","heqg","cpwo","gdcj","lnle","sefg","vimw","bxcb"))
        TestCase.assertEquals(listOf(0, 1), result)
    }

    fun findSubstring(s: String, words: Array<String>): List<Int> {

        val indexSet = mutableSetOf<Int>()

        // val suffixTrie = buildSuffixTrie(s)
        val wordCombinations = getWordCombinations(words.toList())

        var searchStart = 0
        wordCombinations.forEach {
            while (searchStart <= s.length) {
                val index = s.substring(searchStart).indexOf(it)
                if (index != -1)
                    indexSet.add(searchStart+ index)

                searchStart ++
            }

            searchStart = 0
        }

        return indexSet.toList()
    }

    private fun getWordCombinations(words: List<String>): List<String> {

        if (words.size < 2) return words
        val sublist = words.subList(1, words.lastIndex + 1)
        val restOfIt = getWordCombinations(sublist)
        val length = words[0].length
        var placeIndex = length
        val combinationList = mutableListOf<String>()
        val wordToAdd = words[0]
        restOfIt.forEach {

            while (placeIndex < it.length) {
                val before = it.substring(0, placeIndex)
                val after = it.substring(placeIndex, it.length)
                val combined = before + wordToAdd + after
                combinationList.add(combined)
                placeIndex += length
            }
            // add to start
            combinationList.add(wordToAdd + it)

            // add to end
            combinationList.add(it + wordToAdd)

            placeIndex = length
        }

        return combinationList
    }

    private fun buildSuffixTrie(s: String): SuffixTrie {
        val head = SuffixTrie(mutableMapOf())
        var p = s.lastIndex

        while (p >= 0) {
            val word = s.substring(p)
            addToTrie(head, word)
            p--
        }

        return head
    }

    private fun addToTrie(head: SuffixTrie, word: String) {

        var node = head

        word.forEach {

            if (node.children[it] == null) {
                node.children[it] = SuffixTrie(mutableMapOf())
            }

            node = node.children[it]!!
        }

        node.children['*'] = SuffixTrie(mutableMapOf())
    }

    class SuffixTrie(val children: MutableMap<Char, SuffixTrie?>)

}