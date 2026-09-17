import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        // minLen[i] stores the minimum length of a valid subarray found in arr[0...i]
        int[] minLen = new int[n];
        Arrays.fill(minLen, Integer.MAX_VALUE);

        // Map prefix_sum -> index
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int currentSum = 0;
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            currentSum += arr[i];
            map.put(currentSum, i);

            // Carry forward the best length from the previous index
            if (i > 0) {
                minLen[i] = minLen[i - 1];
            }

            int complement = currentSum - target;
            if (map.containsKey(complement)) {
                int start = map.get(complement);
                int currLen = i - start;

                // Check if there is a valid non-overlapping subarray before 'start'
                if (start >= 0 && minLen[start] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, currLen + minLen[start]);
                }

                // Update minLen[i] with the new valid subarray length
                minLen[i] = Math.min(minLen[i], currLen);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}