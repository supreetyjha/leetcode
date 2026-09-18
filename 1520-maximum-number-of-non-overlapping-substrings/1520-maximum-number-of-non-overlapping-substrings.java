import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) first[c] = i;
            last[c] = i;
        }

        // List of valid [start, end] candidate intervals
        List<int[]> candidates = new ArrayList<>();

        for (int c = 0; c < 26; c++) {
            if (first[c] == -1) continue;

            int l = first[c];
            int r = last[c];
            boolean valid = true;

            for (int i = l; i <= r; i++) {
                int ch = s.charAt(i) - 'a';
                if (first[ch] < l) {
                    valid = false;
                    break; // Extends before l, invalid as a candidate starting at l
                }
                r = Math.max(r, last[ch]);
            }

            if (valid) {
                candidates.add(new int[]{l, r});
            }
        }

        // Sort intervals by their right boundary (greedy interval scheduling)
        candidates.sort(Comparator.comparingInt(a -> a[1]));

        List<String> result = new ArrayList<>();
        int prevEnd = -1;

        for (int[] interval : candidates) {
            int l = interval[0], r = interval[1];
            if (l > prevEnd) {
                result.add(s.substring(l, r + 1));
                prevEnd = r;
            }
        }

        return result;
    }
}