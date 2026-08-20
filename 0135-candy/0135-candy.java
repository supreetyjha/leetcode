class Solution {
    public int candy(int[] ratings) {
        int i=1;
        int sum=1;
        int n=ratings.length;
        while(i<n){
            if(ratings[i]==ratings[i-1]){ //straight line
                sum+=1;
                i++;
                continue;
            }
            int peak=1;
            while(i<n && ratings[i]>ratings[i-1]){ //up
                peak+=1;
                sum+=peak;
                i++;
            }
           int  down=1;
            while(i<n && ratings[i]<ratings[i-1]){ //down
                sum+=down;
                i++;
                down+=1;
            }
            if(down>peak){
                sum+=down-peak;
            }
        }
        return sum;
    }
}