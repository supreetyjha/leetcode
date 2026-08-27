public class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        int matchedLen = 0;
        while (matchedLen < n && count[target.charAt(matchedLen) - 'a'] > 0) {
            count[target.charAt(matchedLen) - 'a']--;
            matchedLen++;
        }
        
        for (int i = matchedLen; i >= 0; i--) {
            if (i < n) {
                int targetCharIdx = target.charAt(i) - 'a';
                
                for (int c = targetCharIdx + 1; c < 26; c++) {
                    if (count[c] > 0) {
                        count[c]--;
                        
                        StringBuilder sb = new StringBuilder();
                        sb.append(target, 0, i);
                        sb.append((char) ('a' + c));
                        
                        for (int rem = 0; rem < 26; rem++) {
                            while (count[rem] > 0) {
                                sb.append((char) ('a' + rem));
                                count[rem]--;
                            }
                        }
                        return sb.toString();
                    }
                }
            }
            
            if (i > 0) {
                count[target.charAt(i - 1) - 'a']++;
            }
        }
        
        return "";
    }
}