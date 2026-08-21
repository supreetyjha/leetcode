import java.util.ArrayList;
import java.util.List;

class Solution {
    // Helper record to store subset size and its LCM
    private static class Subset {
        long lcm;
        int size;

        Subset(long lcm, int size) {
            this.lcm = lcm;
            this.size = size;
        }
    }

    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        List<Subset> subsets = new ArrayList<>();

        // Generate all 2^n - 1 non-empty subsets and precompute their LCM
        for (int mask = 1; mask < (1 << n); mask++) {
            long currentLcm = 1;
            int bitCount = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bitCount++;
                    currentLcm = lcm(currentLcm, coins[i]);
                }
            }
            subsets.add(new Subset(currentLcm, bitCount));
        }

        // Determine min element to set binary search bounds
        int minCoin = Integer.MAX_VALUE;
        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }

        long left = 1;
        long right = (long) minCoin * k;
        long ans = right;

        // Binary Search on the answer
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (countMultiples(mid, subsets) >= k) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    // Inclusion-Exclusion counting
    private long countMultiples(long m, List<Subset> subsets) {
        long count = 0;
        for (Subset subset : subsets) {
            long terms = m / subset.lcm;
            if (subset.size % 2 == 1) {
                count += terms;
            } else {
                count -= terms;
            }
        }
        return count;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}