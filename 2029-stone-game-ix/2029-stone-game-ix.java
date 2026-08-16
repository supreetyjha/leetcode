class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];
        for (int stone : stones) {
            cnt[stone % 3]++;
        }
        
        int c0 = cnt[0];
        int c1 = cnt[1];
        int c2 = cnt[2];
        
        if (c0 % 2 == 0) {
            return c1 >= 1 && c2 >= 1;
        }
        
        return Math.abs(c1 - c2) > 2;
    }
}