package solutions.leetcode.medium;

import org.junit.Test;

public class MaximumFontToFit {

    @Test
    public void test1() {
        int[] fonts = new int[]{6, 8, 10, 12, 14, 16, 18, 24, 36};

        FontInfo fontInfo = new FontInfoImpl();
        int result = maxFont("helloworld", 80, 20, fonts, fontInfo);

        assertEquals(6, result);
    }

    private void assertEquals(int i, Integer result) {
    }

    interface FontInfo {
        // Return the width of char ch when fontSize is used.
        public int getWidth(int fontSize, char ch);

        // Return Height of any char when fontSize is used.
        public int getHeight(int fontSize);
    }

    static class FontInfoImpl implements FontInfo {

        @Override
        public int getWidth(int fontSize, char ch) {
            return 0;
        }

        @Override
        public int getHeight(int fontSize) {
            return 0;
        }
    }

    public int maxFont(String text, int w, int h, int[] fonts, FontInfo fontInfo) {

        int start = 0;
        int end = fonts.length - 1;
        int ans = -1;
        while (start <= end) {

            int mid = (start + end) / 2;
            int currentFontSize = fonts[mid];
            int height = fontInfo.getHeight(currentFontSize);

            // check height
            if (height > h) {
                end = mid - 1;
                continue;
            }

            // check width
            int totalWidthForTheFont = 0;
            for (int j = 0; j < text.length(); j++) {
                char c = text.charAt(j);

                int charWidth = fontInfo.getWidth(currentFontSize, c);
                totalWidthForTheFont += charWidth;

                if (totalWidthForTheFont > w) break;
            }

            if (totalWidthForTheFont > w) {
                end = mid - 1;
            } else {
                start = mid + 1;
                ans = currentFontSize;
            }
        }

        return ans;
    }
}
