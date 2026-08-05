class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n=s.length();
        int l=0,r=0,maxlen=0,len=0;
         int hashlen=256;
        int []hash=new int[hashlen];
        Arrays.fill(hash,-1);
        while(r<n){
            if(hash[s.charAt(r)]>-1){
                if(hash[s.charAt(r)]>=l){
                    l=hash[s.charAt(r)]+1;
                }
            }
            len=r-l+1;
            maxlen=Math.max(len,maxlen);
            hash[s.charAt(r)]=r;
            r=r+1;
        }
        return maxlen;
    }
}