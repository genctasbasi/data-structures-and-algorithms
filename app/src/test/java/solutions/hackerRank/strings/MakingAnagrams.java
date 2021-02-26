package solutions.hackerRank.strings;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO WIP
 */
public class MakingAnagrams {

    @Test
    public void makeAnagram() {

        String string1 = "cde";
        String string2 = "abce";

        int deletion = makeAnagram(string1, string2);

        Assert.assertEquals(4, deletion);
    }

    int makeAnagram(String string1, String string2) {

        if (string1 == null || string2 == null)
            return -1;  // indicates an error

        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();

        for (Character c : string1.toCharArray()) {

            if (!map1.containsKey(c) || map1.get(c) == null) {
                map1.put(c, 0);
            }
            map1.put(c, map1.get(c) + 1);
        }

        for (Character c : string2.toCharArray()) {

            if (!map2.containsKey(c) || map2.get(c) == null) {
                map2.put(c, 0);
            }

            map2.put(c, map2.get(c) + 1);
        }

        Map<Character, Integer> checkedCharacters = new HashMap<>();

        for (Character c : map1.keySet()) {
            if (!map2.containsKey(c)) {
                checkedCharacters.put(c, map1.get(c));  // that many deletion
            } else {  // map 2 has it too

                // difference
                int diff = Math.abs(map1.get(c) - map2.get(c));
                checkedCharacters.put(c, diff);  // that many deletion
            }
        }

        for (Character c : map2.keySet()) {

            if (checkedCharacters.containsKey(c))
                continue;   // this is already checked

            if (!map1.containsKey(c)) {
                checkedCharacters.put(c, map2.get(c));  // that many deletion
            } else {  // map 1 has it too

                // difference
                int diff = Math.abs(map1.get(c) - map2.get(c));
                checkedCharacters.put(c, diff);  // that many deletion
            }
        }

        // get total
        int total = 0;
        for (Integer val : checkedCharacters.values()) {
            total += val;
        }

        return total;

    }
}
