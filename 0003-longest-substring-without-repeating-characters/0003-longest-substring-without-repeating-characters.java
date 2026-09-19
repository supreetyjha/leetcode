class Solution {
    int lengthOfLongestSubstring(String s) {

    int[] last = new int[128];
    Arrays.fill(last, -1);

    int left = 0;
    int ans = 0;

    for (int right = 0; right < s.length(); right++) {

        char ch = s.charAt(right);

        if (last[ch] >= left) {
            left = last[ch] + 1;
        }

        last[ch] = right;

        ans = Math.max(ans, right - left + 1);
    }

    return ans;
}
}