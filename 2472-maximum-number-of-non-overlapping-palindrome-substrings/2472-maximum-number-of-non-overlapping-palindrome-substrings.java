class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;
        int start = 0; // Left bound for non-overlapping substrings

        for (int i = k - 1; i < n; i++) {
            // Check substring of length k ending at index i
            if (i - k + 1 >= start && isPalindrome(s, i - k + 1, i)) {
                count++;
                start = i + 1;
            } 
            // Check substring of length k + 1 ending at index i
            else if (i - k >= start && isPalindrome(s, i - k, i)) {
                count++;
                start = i + 1;
            }
        }

        return count;
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }
}