package solutions.hackerRank.arrays;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * https://www.hackerrank.com/challenges/ctci-array-left-rotation/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
 */
public class LeftRotation {

    @Test
    public void test1() {
        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        int[] expected = new int[]{5, 1, 2, 3, 4};
        int[] rotated = rotLeft(arr1, 4);

        assertArrayEquals(rotated, expected);
    }

    @Test
    public void test2() {
        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        int[] expected = new int[]{3, 4, 5, 1, 2};
        int[] rotated = rotLeft(arr1, 2);

        assertArrayEquals(rotated, expected);
    }

    int[] rotLeft(int[] a, int d) {
        int rotateCount = d % a.length;
        if (rotateCount == 0) return a;

        int[] rotatedArray = new int[a.length];

        for (int i = d; i < d + a.length; i++) {
            rotatedArray[i - d] = a[i % a.length];
        }

        return rotatedArray;
    }

}
