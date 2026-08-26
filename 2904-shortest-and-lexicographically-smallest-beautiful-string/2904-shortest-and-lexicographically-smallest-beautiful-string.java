class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int left = 0, ones = 0;
        String ans = "";

        for (int right = 0; right < s.length(); right++) {
            if (s.charAt(right) == '1')
                ones++;

            while (ones > k || (left < right && s.charAt(left) == '0')) {
                if (s.charAt(left) == '1')
                    ones--;
                left++;
            }

            if (ones == k) {
                String cur = s.substring(left, right + 1);

                if (ans.isEmpty()
                        || cur.length() < ans.length()
                        || (cur.length() == ans.length()
                            && cur.compareTo(ans) < 0)) {
                    ans = cur;
                }
            }
        }
        return ans;
    }
}