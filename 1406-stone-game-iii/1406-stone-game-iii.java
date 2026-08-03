public class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        // dp[i % 4] stores the max relative advantage from index i
        int[] dp = new int[4];

        for (int i = n - 1; i >= 0; i--) {
            int ans = Integer.MIN_VALUE;
            int currentSum = 0;

            // Try taking 1, 2, or 3 stones
            for (int k = 1; k <= 3 && i + k - 1 < n; k++) {
                currentSum += stoneValue[i + k - 1];
                ans = Math.max(ans, currentSum - dp[(i + k) % 4]);
            }

            dp[i % 4] = ans;
        }

        int aliceAdvantage = dp[0];

        if (aliceAdvantage > 0) {
            return "Alice";
        } else if (aliceAdvantage < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}