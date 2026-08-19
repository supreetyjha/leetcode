import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Map row -> bitmask of occupied seats (seats 2 to 9)
        Map<Integer, Integer> rowMasks = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            // Seats 1 and 10 do not affect 4-person family blocks
            if (col >= 2 && col <= 9) {
                rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << col));
            }
        }

        // Bitmasks for the 3 placement blocks
        int leftMask = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);   // Seats 2, 3, 4, 5
        int rightMask = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);  // Seats 6, 7, 8, 9
        int midMask = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);    // Seats 4, 5, 6, 7

        // Unreserved rows automatically accommodate 2 families each
        int totalFamilies = (n - rowMasks.size()) * 2;

        for (int mask : rowMasks.values()) {
            boolean leftOk = (mask & leftMask) == 0;
            boolean rightOk = (mask & rightMask) == 0;

            if (leftOk && rightOk) {
                totalFamilies += 2;
            } else if (leftOk || rightOk) {
                totalFamilies += 1;
            } else if ((mask & midMask) == 0) {
                totalFamilies += 1;
            }
        }

        return totalFamilies;
    }
}