class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        int total = n + k - 1;
        int r = 2 * k;

        // Compute C(total, r) % MOD
        long num = 1;
        long den = 1;

        for (int i = 1; i <= r; i++) {
            num = (num * (total - i + 1)) % MOD;
            den = (den * i) % MOD;
        }

        return (int) ((num * modInverse(den, MOD)) % MOD);
    }

    private long modInverse(long base, int mod) {
        return power(base, mod - 2, mod);
    }

    private long power(long base, long exp, int mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}