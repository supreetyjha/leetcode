import java.util.Arrays;

class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[] last = new int[m];
        Arrays.fill(last, -1);

        for (int i = n - 1, j = m - 1; i >= 0 && j >= 0; i--) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
        }

        int[] result = new int[m];
        boolean changed = false;
        int j = 0;
        for (int i = 0; i < n && j < m; i++) {
            boolean match = (word1.charAt(i) == word2.charAt(j));

            if (match || (!changed && (j == m - 1 || last[j + 1] > i))) {
                if (!match) {
                    changed = true;
                }
                result[j] = i;
                j++;
            }
        }

        return (j == m) ? result : new int[0];
    }
}