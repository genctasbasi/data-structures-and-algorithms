package solutions.hackerRank.strings;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.hackerrank.com/challenges/sherlock-and-valid-string/problem
 */
public class SherlockAndTheValidString {

    @Test
    public void isValid() {

        String isValid = isValid("ibfdgaeadiaefgbhbdghhhbgdfgeiccbiehhfcggchgghadhdhagfbahhddgghbdehidbibaeaagaeeigffcebfbaieggabcfbiiedcabfihchdfabifahcbhagccbdfifhghcadfiadeeaheeddddiecaicbgigccageicehfdhdgafaddhffadigfhhcaedcedecafeacbdacgfgfeeibgaiffdehigebhhehiaahfidibccdcdagifgaihacihadecgifihbebffebdfbchbgigeccahgihbcbcaggebaaafgfedbfgagfediddghdgbgehhhifhgcedechahidcbchebheihaadbbbiaiccededchdagfhccfdefigfibifabeiaccghcegfbcghaefifbachebaacbhbfgfddeceababbacgffbagidebeadfihaefefegbghgddbbgddeehgfbhafbccidebgehifafgbghafacgfdccgifdcbbbidfifhdaibgigebigaedeaaiadegfefbhacgddhchgcbgcaeaieiegiffchbgbebgbehbbfcebciiagacaiechdigbgbghefcahgbhfibhedaeeiffebdiabcifgccdefabccdghehfibfiifdaicfedagahhdcbhbicdgibgcedieihcichadgchgbdcdagaihebbabhibcihicadgadfcihdheefbhffiageddhgahaidfdhhdbgciiaciegchiiebfbcbhaeagccfhbfhaddagnfieihghfbaggiffbbfbecgaiiidccdceadbbdfgigibgcgchafccdchgifdeieicbaididhfcfdedbhaadedfageigfdehgcdaecaebebebfcieaecfagfdieaefdiedbcadchabhebgehiidfcgahcdhcdhgchhiiheffiifeegcfdgbdeffhgeghdfhbfbifgidcafbfcd");

        Assert.assertEquals(isValid, "NO");
    }

    String isValid(String string1) {

        Map<Character, Integer> map = new HashMap<>();

        for (Character c : string1.toCharArray()) {

            if (!map.containsKey(c) || map.get(c) == null) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }

        int count = -1;
        boolean hasChanged = false;

        for (Integer countIndex : map.values()) {

            if (count == -1) count = countIndex;

            if (countIndex != count) {
                if (hasChanged) return "NO";


                hasChanged = true;
            }
        }

        return "YES";
    }
}
