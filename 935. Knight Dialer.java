class Solution {
    public static final int MOD = 1000000007;
    public static int[] res;   
    public int knightDialer(int N) {
        if(N==1) 
            return 10;
        
        if(res!=null) 
            return res[N];
        
        res = new int[5001];
        // 0
        // 4,6
        // 1,3,7,9
        // 2,8
        long f0 = 1, f46 = 2, f1379 = 4, f28 = 2;
        int i = 2;
        while (i < 5001) {
            //change status of every number, 
            long b0 = f46;
            long b46 = (f0 * 2 + f1379) % MOD;
            long b1379 = (f46 * 2 + f28 * 2) % MOD;
            long b28 = f1379;
            
            res[i++] = (int)((b0 + b46 + b1379 + b28) % MOD);
            // newstatus to current status
            f0 = b0;
            f46 = b46;
            f1379 = b1379;
            f28 = b28;
        }
        return res[N];
    }
}
