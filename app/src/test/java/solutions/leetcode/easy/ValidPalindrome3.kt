package solutions.leetcode.easy

import org.junit.Assert
import org.junit.Test

/**
 * https://leetcode.com/problems/valid-palindrome-iii/
 */
class ValidPalindrome3 {

    @Test
    fun test() {
        Assert.assertEquals(true, isValidPalindrome("bacddcxyab", 2))
    }

    @Test
    fun test1() {
        Assert.assertEquals(
            true,
            isValidPalindrome(
                "fcgihcgeadfehgiabegbiahbeadbiafgcfchbcacedbificicihibaeehbffeidiaiighceegbfdggggcfaiibefbgeegbcgeadcfdfegfghebcfceiabiagehhibiheddbcgdebdcfegaiahibcfhheggbheebfdahgcfcahafecfehgcgdabbghddeadecidicchfgicbdbecibddfcgbiadiffcifiggigdeedbiiihfgehhdegcaffaggiidiifgfigfiaiicadceefbhicfhbcachacaeiefdcchegfbifhaeafdehicfgbecahidgdagigbhiffhcccdhfdbd",
                216
            )
        )
    }

    @Test
    fun test2() {
        Assert.assertEquals(
            true,
            isValidPalindrome(
                "dadaabbdddcabbccdcaabadbbcccbacdcacacbdbabbadcdabacbadcdabcbbaacdabbdcbabdcbbbcaddccbdbbaaadcbaabaadbadcdadcdcddddcdbbddcbddbbdbabababcbdabddabcdcbcaaaacbbbdcbabbbcaacbcbacaaabccdbdddbbcbbbcacacbbdccacacddaabccbaccddcbdcbcacadbaacccbaabaccdaddbdadcccbbbcbbbdcbbacacbabdcddcbbbccdcbbabddcbbabbacdcadcaaddcdcdaaccadbcdacbbdccaccdaaaabbcdcadddadcddbdccadabcbbadbbdbbaccbdadbdbbdcaccccccaddaaacbadcbbacbbbddacbadbdabdcbcacadbcdabbdbaddcdcabbbbabbacdacbacdaccbcbbcaccbabcbdbaccacddccdbabcbaadadacadabacddadadcbdaacdbd",
                216
            )
        )
    }

    fun isValidPalindrome(s: String, k: Int): Boolean {
        if (s.length < 2) return true
        if (s.length == k) return true
        return helper(s, 0, s.lastIndex, k, hashMapOf())
    }

    fun helper(s: String, i: Int, j: Int, k: Int, mem: HashMap<String, Boolean>): Boolean {

        if (mem["$i-$j-$k"] != null) return mem["$i-$j-$k"]!!

        var pStart = i
        var pEnd = j

        while (pStart < pEnd) {
            if (s[pStart] != s[pEnd]) {

                if (k == 0) return false

                val option1 = helper(s, pStart + 1, pEnd, k - 1, mem)
                mem["${pStart + 1}-$pEnd-${k - 1}"] = option1

                val option2 = helper(s, pStart, pEnd - 1, k - 1, mem)
                mem["$pStart-${pEnd - 1}-${k - 1}"] = option2

                return option1 || option2
            }

            pStart++
            pEnd--
        }

        return true
    }
}