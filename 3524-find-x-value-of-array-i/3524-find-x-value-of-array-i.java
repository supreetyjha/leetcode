class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] nextDp = new long[k];
            int mod = num % k;

            // Start a new subarray with the current element
            nextDp[mod]++;

            // Extend existing subarrays ending at the previous element
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int nextMod = (r * mod) % k;
                    nextDp[nextMod] += dp[r];
                }
            }

            // Accumulate counts of all subarrays ending at this index
            for (int r = 0; r < k; r++) {
                ans[r] += nextDp[r];
            }

            dp = nextDp;
        }

        return ans;
    }
}