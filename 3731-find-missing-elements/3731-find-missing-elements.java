import java.util.*;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        int minVal = nums[0];
        int maxVal = nums[0];

        for (int num : nums) {
            numSet.add(num);
            minVal = Math.min(minVal, num);
            maxVal = Math.max(maxVal, num);
        }

        List<Integer> missing = new ArrayList<>();
        for (int i = minVal; i <= maxVal; i++) {
            if (!numSet.contains(i)) {
                missing.add(i);
            }
        }

        return missing;
    }
}