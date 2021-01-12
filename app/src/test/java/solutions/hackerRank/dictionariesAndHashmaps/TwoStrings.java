package solutions.hackerRank.dictionariesAndHashmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TwoStrings {

    @Test
    public void test() {
        assertEquals("YES", twoStrings("hello", "world"));
        assertEquals("NO", twoStrings("hi", "world"));
    }

    String twoStrings(String s1, String s2) {

        List<Character> list = new ArrayList();
        for (char c : s1.toCharArray()) {
            if (!list.contains(c))
                list.add(c);
        }

        for (char c : s2.toCharArray()) {
            if (list.contains(c)) return "YES";
        }

        return "NO";
    }
}

