import java.util.Arrays;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] totalCount = new int[26];
        for (int i = 0; i < n; i++) {
            totalCount[s.charAt(i) - 'a']++;
        }

        // 1. Verify palindromic feasibility
        int oddCount = 0;
        int oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (totalCount[i] % 2 != 0) {
                oddCount++;
                oddChar = i;
            }
        }
        if (oddCount > 1) {
            return "";
        }

        // Half-counts available for the first m positions
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = totalCount[i] / 2;
        }

        int m = n / 2;
        String best = null;

        // Case 1: Exact prefix match of length m
        int[] curHalf = Arrays.copyOf(halfCount, 26);
        boolean canMatchAll = true;
        char[] left = new char[m];

        for (int i = 0; i < m; i++) {
            int ch = target.charAt(i) - 'a';
            if (curHalf[ch] > 0) {
                curHalf[ch]--;
                left[i] = (char) ('a' + ch);
            } else {
                canMatchAll = false;
                break;
            }
        }

        if (canMatchAll) {
            String cand = constructPalindrome(left, n, oddChar);
            if (cand.compareTo(target) > 0) {
                best = cand;
            }
        }

        // Case 2: Diverge at index i (m-1 down to 0)
        for (int i = m - 1; i >= 0; i--) {
            int[] tempHalf = Arrays.copyOf(halfCount, 26);
            boolean prefixOk = true;
            char[] prefix = new char[m];

            for (int k = 0; k < i; k++) {
                int ch = target.charAt(k) - 'a';
                if (tempHalf[ch] > 0) {
                    tempHalf[ch]--;
                    prefix[k] = (char) ('a' + ch);
                } else {
                    prefixOk = false;
                    break;
                }
            }

            if (!prefixOk) continue;

            int targetChar = target.charAt(i) - 'a';
            int chosenChar = -1;
            for (int c = targetChar + 1; c < 26; c++) {
                if (tempHalf[c] > 0) {
                    chosenChar = c;
                    break;
                }
            }

            if (chosenChar != -1) {
                prefix[i] = (char) ('a' + chosenChar);
                tempHalf[chosenChar]--;

                int charIdx = 0;
                for (int k = i + 1; k < m; k++) {
                    while (charIdx < 26 && tempHalf[charIdx] == 0) {
                        charIdx++;
                    }
                    prefix[k] = (char) ('a' + charIdx);
                    tempHalf[charIdx]--;
                }

                String cand = constructPalindrome(prefix, n, oddChar);
                if (best == null || cand.compareTo(best) < 0) {
                    best = cand;
                }
                break;
            }
        }

        return best == null ? "" : best;
    }

    private String constructPalindrome(char[] left, int n, int oddChar) {
        char[] full = new char[n];
        int m = left.length;
        for (int i = 0; i < m; i++) {
            full[i] = left[i];
            full[n - 1 - i] = left[i];
        }
        if (n % 2 != 0) {
            full[m] = (char) ('a' + oddChar);
        }
        return new String(full);
    }
}