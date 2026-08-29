import java.util.Arrays;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] result = new int[n];
        int i = 0;

        while (i < n) {
            int j = i + 1;
            while (j < n && nums[order[j]] - nums[order[j - 1]] <= limit) {
                j++;
            }

            int[] groupIndices = new int[j - i];
            for (int k = i; k < j; k++) {
                groupIndices[k - i] = order[k];
            }
            Arrays.sort(groupIndices);

            for (int k = 0; k < groupIndices.length; k++) {
                result[groupIndices[k]] = nums[order[i + k]];
            }

            i = j;
        }

        return result;
    }
}