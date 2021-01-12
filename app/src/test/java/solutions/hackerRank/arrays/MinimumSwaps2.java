package solutions.hackerRank.arrays;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * https://www.hackerrank.com/challenges/minimum-swaps-2/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
 */
public class MinimumSwaps2 {

    @Test
    public void test1() {
        int[] arr1 = new int[]{2, 3, 4, 1, 5};
        assertEquals(3, minimumSwaps(arr1));
    }

    @Test
    public void test2() {
        int[] arr1 = new int[]{4, 3, 2, 1};
        assertEquals(2, minimumSwaps(arr1));
    }

    @Test
    public void test3() {
        int[] arr1 = new int[]{1, 3, 5, 2, 4, 6, 7};
        assertEquals(3, minimumSwaps(arr1));
    }

    int minimumSwaps(int[] arr) {
        boolean hasSwapped;
        int swapCount = 0;

        do {
            hasSwapped = false;
            for (int i = 0; i < arr.length; i++) {
                int currentNumber = arr[i];

                if (currentNumber != i + 1) {

                    // swap here
                    arr[i] = arr[currentNumber - 1];
                    arr[currentNumber - 1] = currentNumber;

                    hasSwapped = true;
                    swapCount++;
                    break;
                }
            }


        } while (hasSwapped);

        return swapCount;
    }

}
