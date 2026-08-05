class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums,k)- atMost(nums,k-1);
    }
    public int atMost(int[]nums,int K){
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0, count = 0;
        for (int right = 0; right < nums.length; right++) {
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);
            if (freq.get(nums[right]) == 1) {
                K--;
            }
            while (K < 0) {
                freq.put(nums[left], freq.get(nums[left]) - 1);
                if (freq.get(nums[left]) == 0) {
                    K++;
                }
                left++;
            }
            count += (right - left + 1);
        }

        return count;
    }
}
    