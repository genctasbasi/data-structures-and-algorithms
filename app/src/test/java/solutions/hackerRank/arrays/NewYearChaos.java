package solutions.hackerRank.arrays;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
 * https://www.hackerrank.com/challenges/new-year-chaos/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
 */

public class NewYearChaos {

    @Test
    public void test1() {
        int bribeCount = minimumBribes(new int[]{2, 1, 5, 3, 4});
        assertEquals(3, bribeCount);
    }

    @Test
    public void test2() {
        int bribeCount = minimumBribes(new int[]{2, 5, 1, 3, 4});
        assertEquals(-1, bribeCount);
    }

    public int minimumBribes(int[] q) {
        int swapCount = 0;

        Map<Integer, Integer> bribesMap = new HashMap();
        boolean hasSwapped;

        do {
            hasSwapped = false;

            for (int index = 0; index < q.length - 1; index++) {
                int person = q[index];

                if (index + 1 < q.length) {  // then there is a next person
                    int nextPerson = q[index + 1];

                    if (person > nextPerson) {  // then 'person' bribed

                        Integer existingBribesOfThePerson = bribesMap.get(person);
                        if (existingBribesOfThePerson == null) existingBribesOfThePerson = 0;
                        bribesMap.put(person, existingBribesOfThePerson + 1);

                        q[index] = q[index + 1];
                        q[index + 1] = person;
                        swapCount++;
                        hasSwapped = true;
                    }

                }
            }

        } while (!isTooChaotic(bribesMap) && hasSwapped);

        if (isTooChaotic(bribesMap))
            return -1;
        else
            return swapCount;

    }

    private boolean isTooChaotic(Map<Integer, Integer> map) {
        for (int val : map.values()) {
            if (val > 2) return true;
        }

        return false;
    }
}
