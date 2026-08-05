import java.util.Arrays;

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int child = 0;  // Index for greed factor array g
        int cookie = 0; // Index for cookie size array s

        // Iterate through both arrays
        while (child < g.length && cookie < s.length) {
            // If the current cookie can satisfy the current child
            if (s[cookie] >= g[child]) {
                child++; // Move to next child
            }
            cookie++; // Always move to the next cookie
        }

        return child; // Total satisfied children
    }
}