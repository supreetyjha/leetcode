class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        double balance = 0.0;
        
        for (int i = 0; i < n / 2; i++) {
            char c = num.charAt(i);
            balance += (c == '?') ? 4.5 : (c - '0');
        }
        for (int i = n / 2; i < n; i++) {
            char c = num.charAt(i);
            balance -= (c == '?') ? 4.5 : (c - '0');
        }
        
        return balance != 0.0;
    }
}