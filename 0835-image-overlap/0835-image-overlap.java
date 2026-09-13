import java.util.*;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (img1[r][c] == 1) ones1.add(new int[]{r, c});
                if (img2[r][c] == 1) ones2.add(new int[]{r, c});
            }
        }

        // Map encoded offset (dr * 100 + dc) to frequency
        Map<Integer, Integer> count = new HashMap<>();
        int maxOverlap = 0;

        for (int[] p1 : ones1) {
            for (int[] p2 : ones2) {
                int key = (p1[0] - p2[0]) * 100 + (p1[1] - p2[1]);
                int val = count.getOrDefault(key, 0) + 1;
                count.put(key, val);
                maxOverlap = Math.max(maxOverlap, val);
            }
        }

        return maxOverlap;
    }
}