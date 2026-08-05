import java.util.*;

class Solution {
    public int totalFruit(int[] fruits) {
        int maxlen = 0, l = 0, r = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        int k = 2; // Fruit into Baskets allows at most 2 distinct types

        while (r < fruits.length) {
            // 1. Add current fruit to map
            mp.put(fruits[r], mp.getOrDefault(fruits[r], 0) + 1);

            // 2. Shrink window if distinct types exceed k
            if (mp.size() > k) {
                mp.put(fruits[l], mp.get(fruits[l]) - 1);
                if (mp.get(fruits[l]) == 0) {
                    mp.remove(fruits[l]);
                }
                l++; // Shrink left boundary
            }

            // 3. Update answer
            if (mp.size() <= k) {
                maxlen = Math.max(maxlen, r - l + 1);
            }
            r++;
        }
        return maxlen;
    }
}