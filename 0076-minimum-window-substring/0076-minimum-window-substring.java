import java.util.Arrays;
class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        int[] hash = new int[256];
        for (int i = 0; i < t.length(); i++) {
            hash[t.charAt(i)]++;
        }
        int l = 0, r = 0, c = 0;
        int minlen = Integer.MAX_VALUE;
        int sindex = -1;
        while (r < s.length()) {
            // If the character is needed, increment valid match count
            if (hash[s.charAt(r)] > 0) {
                c++;
            }
            hash[s.charAt(r)]--; // Always consume character in window

            // Shrink window when all characters of t are matched
            while (c == t.length()) {
                if ((r - l + 1) < minlen) {
                    minlen = r - l + 1;
                    sindex = l;
                }

                hash[s.charAt(l)]++;
                // If frequency becomes positive, we lost a required character
                if (hash[s.charAt(l)] > 0) {
                    c--;
                }
                l++; // Shrink left boundary
            }
            r++; // Expand right boundary
        }

        return sindex == -1 ? "" : s.substring(sindex, sindex + minlen);
    }
}