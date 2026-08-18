import java.util.HashMap;
import java.util.Map;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        
        // Count total frequencies of each number
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        
        // Case 1: Subarrays of size 1
        if (k == 1) {
            int maxUnique = -1;
            for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                if (entry.getValue() == 1) {
                    maxUnique = Math.max(maxUnique, entry.getKey());
                }
            }
            return maxUnique;
        }
        
        // Case 2: Subarray of size n (the whole array)
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }
        
        // Case 3: 1 < k < n (only boundary elements can appear in exactly 1 window)
        int ans = -1;
        if (freq.get(nums[0]) == 1) {
            ans = Math.max(ans, nums[0]);
        }
        if (freq.get(nums[n - 1]) == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }
        
        return ans;
    }
}