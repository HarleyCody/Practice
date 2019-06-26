_____________________________________________________________Best Solution___________________________________________________________________
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
_____________________________________________________________DP Solution___________________________________________________________________
class Solution {
    public int knightDialer(int N){
        int[][] dirs = new int[][]{{4,6},{6,8},{7,9},{4,8},{3,9,0},{},{1,7,0},{2,6},{1,3},{2,4}};
        
        int[][] dp = new int[ N ][10];
        for(int j = 0; j < dp[0].length; ++j){
            dp[0][j] = 1;
        }
        int mod = (int)1e9 + 7;
        for(int i = 1; i < dp.length ; ++i)
            for(int j = 0; j < dp[0].length; ++j){
                
                for(int num : dirs[j]){
                // newStatus = sum of neighbor's in last status
                    dp[i][j] += dp[i - 1][num]; 
                    dp[i][j] %= mod;
                }
            }
        int count = 0;
        for(int i = 0; i < dp[0].length; ++i){
            count += dp[N - 1][i];
            count %= mod;
        }
       return count;
       
    }
}
