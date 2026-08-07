class Solution {
    public int jump(int[] nums) {
        int jumps=0,curr=0,farthest=0;
        for(int i=0;i<nums.length-1;i++){
            farthest=Math.max(farthest,i+nums[i]);
            if(i==curr){
                jumps++;
                curr=farthest;
            }
        }
        return jumps;
    }
}