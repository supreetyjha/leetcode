public class Solution {
    public int minFlips(String s) {
        int n = s.length();
        String str = s + s; // Handles circular shifts
        
        // Target patterns
        StringBuilder alt1 = new StringBuilder();
        StringBuilder alt2 = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            alt1.append(i % 2 == 0 ? '0' : '1');
            alt2.append(i % 2 == 0 ? '1' : '0');
        }
        
        int diff1 = 0, diff2 = 0;
        int minFlips = Integer.MAX_VALUE;
        
        // Sliding window of size n
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != alt1.charAt(i)) diff1++;
            if (str.charAt(i) != alt2.charAt(i)) diff2++;
            
            // Remove the character sliding out of the window
            if (i >= n) {
                if (str.charAt(i - n) != alt1.charAt(i - n)) diff1--;
                if (str.charAt(i - n) != alt2.charAt(i - n)) diff2--;
            }
            
            // Record minimum once window reaches length n
            if (i >= n - 1) {
                minFlips = Math.min(minFlips, Math.min(diff1, diff2));
            }
        }
        
        return minFlips;
    }
}