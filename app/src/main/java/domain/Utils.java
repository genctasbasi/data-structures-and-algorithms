package domain;

import java.util.Arrays;

public class Utils {

    public static String sort(String value) {

        char[] charArray = value.toCharArray();

        // this part is O(nlogn)
        Arrays.sort(charArray);

        return String.copyValueOf(charArray);
    }
}
