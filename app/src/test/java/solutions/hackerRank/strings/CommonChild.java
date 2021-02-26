package solutions.hackerRank.strings;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * CommonChild
 * https://www.hackerrank.com/challenges/common-child/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=strings
 * This is a dynamic programming question, the best explanation is here:
 * https://www.youtube.com/watch?v=ASoaQq66foQ&ab_channel=BackToBackSWE
 */
public class CommonChild {

    @Test
    public void test() {
        assertEquals(0, commonChild("AA", "BB"));
        assertEquals(2, commonChild("ABCDEF", "FBDAMN"));
        assertEquals(2, commonChild("HARRY", "SALLY")); // AY
        assertEquals(3, commonChild("SHINCHAN", "NOHARAAA"));   // NHA
        assertEquals(2, commonChild("OUDFRMYMAW", "AWHYFCCMQX"));   // NHA
        assertEquals(15, commonChild("WEWOUCUIDGCGTRMEZEPXZFEJWISRSBBSYXAYDFEJJDLEBVHHKS", "FDAGCXGKCTKWNECHMRXZWMLRYUCOCZHJRRJBOAJOQJZZVUYXIC"));
    }

    /**
     *            S     H     I     N     C     H     A     N
     * ------------------------------------------------------------
     *   |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |  0  |
     * N |  0  |  0  |  0  |  0  |  1  |  1  |  1  |  1  |  1  |
     * O |  0  |  0  |  0  |  0  |  1  |  1  |  1  |  1  |  1  |
     * H |  0  |  0  |  1  |  1  |  1  |  1  |  2  |  2  |  2  |
     * A |  0  |  0  |  1  |  1  |  1  |  1  |  2  |  3  |  3  |
     * R |  0  |  0  |  1  |  1  |  1  |  1  |  2  |  3  |  3  |
     * A |  0  |  0  |  1  |  1  |  1  |  1  |  2  |  3  |  3  |
     * A |  0  |  0  |  1  |  1  |  1  |  1  |  2  |  3  |  3  |
     * A |  0  |  0  |  1  |  1  |  1  |  1  |  2  |  3  |  3  |
     * ------------------------------------------------------------
     */

    int commonChild(String s1, String s2) {

        int[][] table = new int[s1.length()][s2.length()];

        for (int i = 0; i < s2.length(); i++) {
            for (int j = 0; j < s1.length(); j++) {

                int sub1;
                int sub2;
                int prev;

                if (i == 0) sub2 = 0; else sub2 = table[i - 1][j];
                if (j == 0) sub1 = 0; else sub1 = table[i][j - 1];
                if (i > 0 && j > 0) prev = table[i - 1][j - 1]; else prev = 0;

                int maxPrev = Math.max(sub2, sub1);

                if (s2.charAt(i) == s1.charAt(j)) {
                    table[i][j] = 1 + prev;
                } else {
                    table[i][j] = maxPrev;
                }
            }
        }

        return table[s1.length() - 1][s2.length() - 1];
    }
}
