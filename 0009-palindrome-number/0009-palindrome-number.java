class Solution {
    public boolean isPalindrome(int x) {
      int d,rev=0;
      int n=x;
      if(n<0) return false;
      else
      {
        while(n!=0)
      {
        d=n%10;
        rev=rev*10 +d;
        n=n/10;
      }
      return x==rev || x==rev/10;  } 
         
        
    }
}