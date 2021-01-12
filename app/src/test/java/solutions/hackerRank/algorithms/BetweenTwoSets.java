package solutions.hackerRank.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/between-two-sets/problem
 *
 * Complexity: O(n2)
 *
 * LCM (least-common-multiple)
 *  3, 4 -> 12
 *  2, 4 -> 4
 *
 * GCD (greatest-common-divider)
 *  6, 12 -> 6
 *  15, 30, 40 -> 5
 */
public class BetweenTwoSets {

    List<Integer> list1 = Arrays.asList(2, 6);
    List<Integer> list2 = Arrays.asList(24, 36);

    List<Integer> list3 = Arrays.asList(2, 4);
    List<Integer> list4 = Arrays.asList(16, 32, 96);

    List<Integer> list5 = Arrays.asList(1);
    List<Integer> list6 = Arrays.asList(100);

    @Test
    public void test1() {

        List<Integer> betweenNumbers = getBetweenNumbers(list1, list2);

        assertEquals(2, betweenNumbers.size());
        assertEquals(6, (int) betweenNumbers.get(0));
        assertEquals(12, (int) betweenNumbers.get(1));
    }

    @Test
    public void test2() {

        List<Integer> betweenNumbers = getBetweenNumbers(list3, list4);

        assertEquals(3, betweenNumbers.size());
        assertEquals(4, (int) betweenNumbers.get(0));
        assertEquals(8, (int) betweenNumbers.get(1));
        assertEquals(16, (int) betweenNumbers.get(2));
    }

    @Test
    public void test3() {
        List<Integer> betweenNumbers = getBetweenNumbers(list5, list6);
        assertEquals(9, betweenNumbers.size());
    }

    private List<Integer> getBetweenNumbers(List<Integer> a, List<Integer> b) {

        List<Integer> betweenNumbers = new ArrayList();

        int start = a.get(a.size() - 1);
        int end = b.get(b.size() - 1) ;
        boolean isFail = false;

        for (int numberConsidered = start; numberConsidered <= end; numberConsidered++) {

            for (int currentArr1Number : a) {

                if (numberConsidered % currentArr1Number != 0) {
                    isFail = true;
                    break;
                }
            }

            if (isFail) {
                isFail = false;
                continue;
            }

            for (int currentArr2Number : b) {

                if (currentArr2Number % numberConsidered != 0) {
                    isFail = true;
                    break;
                }
            }

            if (!isFail) {
                betweenNumbers.add(numberConsidered);
            }

            isFail = false;
        }

        return betweenNumbers;
    }

}
