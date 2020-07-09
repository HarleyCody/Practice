__________________________________________________________________________________________Best Solution________________________________________________________________________
class Solution {
    // pure dp without recursion
    // find third point to update range dp[i][j]
    public int getMoneyAmount(int n) {
        int[][] table = new int[n+1][n+1];
        for(int j=2; j<=n; ++j){
            for(int i=j-1; i>0; --i){
                int globalMin = Integer.MAX_VALUE;
                for(int k=i+1; k<j; k++){
                    int localMax = k + Math.max(table[i][k-1], table[k+1][j]);
                    globalMin = Math.min(globalMin, localMax);
                }
                // adjscent choose min to guess
                table[i][j] = i+1 == j ? i : globalMin;
            }
        }
        return table[1][n];
    }
}
__________________________________________________________________________________________Best Solution________________________________________________________________________
class Solution {
    // dfs + memorization
    // larger range comes from minMax of smaller range
    int[][] mem;
    public int getMoneyAmount(int n) {
        mem = new int[n + 1][n + 1];
        return getMoney(1, n);
    }
    
    private int getMoney(int l, int r){
        if(l >= r){
            return 0;
        }
        if(l == r + 1){
            return 1;
        }
        if(mem[l][r] > 0){
            return mem[l][r];
        }
        int mid = (l + r) / 2;
        
        int ans = Integer.MAX_VALUE;
        for(int n = r - 1; mid <= n; n -= 2){
            ans = Math.min(ans, n + Math.max(getMoney(l, n - 1), getMoney(n + 1, r)));
        }
        
        mem[l][r] = ans;
        return ans;                     
    }
}
