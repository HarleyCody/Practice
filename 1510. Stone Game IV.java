_______________________________________________________________________Best Solution__________________________________________________________________________________________
// Alice first so dp[0] must be true;
// Extend dp[0] to the tail;
// mem + dfs to dp
class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        for (int i = 0; i <= n; i++){
            if (dp[i]){
                continue;
            }
            for (int k = 1; i + k * k <= n; k++){
                dp[i+k*k] = true;
            }
        }
        return dp[n];
    }
}
________________________________________________________________________My Solution__________________________________________________________________________________________
// Every one choose optimally, dfs + mem
// If next one can not win, then current can win;
// mem[i][j] : can player i win the game at stone j;
class Solution {
    int[][] mem;
    public boolean winnerSquareGame(int n) {
        mem = new int [2][n + 1];
        return win(n, 0);
    }
    
    private boolean win(int n, int id){
        if(n <= 0){
            return false;
        }
        if(mem[id][n] != 0){
            return mem[id][n] == 1;
        }
        int nId = (id + 1) % 2;
        boolean ans = false;
        int pick = 1;
        while(!ans && pick * pick <= n){
            ans |= !win(n - pick * pick, nId);
            ++pick;
        }
        
        mem[id][n] = ans? 1 : -1;
        return ans;
    }
}
