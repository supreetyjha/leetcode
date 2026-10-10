class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        
        int maxDiff = 0;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            if (diff[i] > maxDiff) {
                maxDiff = diff[i];
            }
        }
        
        // If all differences are already 0
        if (maxDiff == 0) {
            return 0;
        }
        
        // Count frequencies of each difference
        int[] count = new int[maxDiff + 1];
        for (int d : diff) {
            count[d]++;
        }
        
        // Greedily reduce largest differences down
        for (int d = maxDiff; d > 0 && k > 0; d--) {
            if (count[d] == 0) {
                continue;
            }
            
            long take = Math.min((long) count[d], k);
            count[d] -= (int) take;
            count[d - 1] += (int) take;
            k -= take;
        }
        
        // Calculate the sum of squares
        long ans = 0;
        for (long d = 1; d <= maxDiff; d++) {
            if (count[(int) d] > 0) {
                ans += (long) count[(int) d] * d * d;
            }
        }
        
        return ans;
    }
}