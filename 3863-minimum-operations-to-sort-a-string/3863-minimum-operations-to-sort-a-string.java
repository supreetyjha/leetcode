class Solution {
    public int minOperations(String s) {
        int n = s.length();
        
        boolean sorted = true;
        char mn = s.charAt(0), mx = s.charAt(0);
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) sorted = false;
            if (s.charAt(i) < mn) mn = s.charAt(i);
            if (s.charAt(i) > mx) mx = s.charAt(i);
        }
        
        if (sorted) return 0;
        if (n == 2) return -1;
        
        if (s.charAt(0) == mn || s.charAt(n - 1) == mx) return 1;
        
        int firstMin = s.indexOf(mn);
        int lastMax = s.lastIndexOf(mx);
        
        if (firstMin == n - 1 && lastMax == 0) return 3;
        
        return 2;
    }
}