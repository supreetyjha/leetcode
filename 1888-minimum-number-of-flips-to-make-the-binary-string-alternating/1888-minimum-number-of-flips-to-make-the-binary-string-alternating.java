class Solution {
    public int minFlips(String s) {
        int n = s.length();
        int[] start0 = new int[n];
        int[] start1 = new int[n];
        start0[0] = s.charAt(0) - '0';
        start1[0] = ((s.charAt(0) - '0') + 1) % 2;
        for (int i=1; i<n; i++)
        {
            int d = s.charAt(i) - '0';
            if (i%2==0)
            {
                if (d==0)
                {
                    start0[i] = start0[i-1];
                    start1[i] = start1[i-1] + 1;
                }
                else
                {
                    start0[i] = start0[i-1] + 1;
                    start1[i] = start1[i-1];
                }
            }
            else
            {
                if (d==0)
                {
                    start0[i] = start0[i-1] + 1;
                    start1[i] = start1[i-1];
                }
                else
                {
                    start0[i] = start0[i-1];
                    start1[i] = start1[i-1] + 1;
                }
            }
        }

        int res = Math.min(start0[n-1], start1[n-1]);
        if (n%2==0) return res;
        for (int i=0; i<n-1; i++)
        {
            int left = start0[i];
            int right = start1[n-1] - start1[i];
            res = Math.min(res, left+right);

            left = start1[i];
            right = start0[n-1] - start0[i];
            res = Math.min(res, left+right);
        }

        return res;
    }
}