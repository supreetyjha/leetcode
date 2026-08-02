class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[] dp = new int[n];
        
        // Base case: sub-arrays of length 1
        for (int i = 0; i < n; i++) {
            dp[i] = piles[i];
        }

        // Fill DP table for larger sub-arrays
        for (int d = 1; d < n; d++) {
            for (int i = 0; i < n - d; i++) {
                dp[i] = Math.max(piles[i] - dp[i + 1], piles[i + d] - dp[i]);
            }
        }

        return dp[0] > 0;
    }
}