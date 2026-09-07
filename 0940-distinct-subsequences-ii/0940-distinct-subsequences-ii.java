class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int[] ends = new int[26];
        long total = 0; // total distinct subsequences formed so far

        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            // New subsequences ending with ch = all existing + the single character
            long newEnds = (total + 1) % MOD;
            
            // Net addition = newEnds - previously recorded ends[idx]
            long added = (newEnds - ends[idx] + MOD) % MOD;
            
            total = (total + added) % MOD;
            ends[idx] = (int) newEnds;
        }

        return (int) total;
    }
}