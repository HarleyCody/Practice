____________________________________________________________________________My Solution(Imporved dp)___________________________________________________________________
class Solution {
//two scenarios equal or not
    public int longestCommonSubsequence(String text1, String text2) {
        int R = text1.length(), C = text2.length();
        int[][] dp = new int[R + 1][C + 1];
        
        for(int r = R - 1; 0 <= r; --r){
            for(int c = C - 1; 0 <= c; --c){
               if(text1.charAt(r) == text2.charAt(c)){
                   dp[r][c] = dp[r + 1][c + 1] + 1;
               }else{
                   dp[r][c] = Math.max(dp[r + 1][c], dp[r][c + 1]);
               }
            }
        }
        return dp[0][0];
    }
}
____________________________________________________________________________My Solution(dp)____________________________________________________________________________
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int R = text1.length(), C = text2.length();
        int[][] dp = new int[R][C];
        
        for(int r = R - 1; 0 <= r; --r){
            for(int c = C - 1; 0 <= c; --c){
                
                int incre = text1.charAt(r) == text2.charAt(c)? 1 : 0;
                if(r == R - 1 && c == C - 1){
                    dp[r][c] = incre;
                }else if(c == C - 1){
                    dp[r][c] = incre;
                    dp[r][c] = Math.max(dp[r + 1][c], dp[r][c]);
                }else if(r == R - 1){
                    dp[r][c] = incre;
                    dp[r][c] = Math.max(dp[r][c + 1], dp[r][c]);
                }else{
                    dp[r][c] = Math.max(incre + dp[r + 1][c + 1], dp[r][c]);
                    dp[r][c] = Math.max(dp[r][c + 1], dp[r][c]);
                    dp[r][c] = Math.max(dp[r + 1][c], dp[r][c]);
                }
            }
        }
        return dp[0][0];
    }
}
____________________________________________________________________________My Solution(Mem) _________________________________________________________________________________________
class Solution {
    int R, C;
    int[][] mem;
    
    public int longestCommonSubsequence(String text1, String text2) {
        R = text1.length();
        C = text2.length();
        mem = new int[R][C];
        for(int[] m : mem){
            Arrays.fill(m, -1);
        }
        findMax(text1, text2, 0, 0);
        
        return mem[0][0];
    }
    
    private int findMax(String t1, String t2, int r, int c){
        if(r >= R || c >= C){
            return 0;
        }
        
        if(mem[r][c] >= 0){
            return mem[r][c];
        }
        int cur = t1.charAt(r) == t2.charAt(c) ? 1 : 0;
        int ans = findMax(t1, t2, r + 1, c);
        ans = Math.max(findMax(t1, t2, r, c + 1), ans);
        ans = Math.max(findMax(t1, t2, r + 1, c + 1) + cur, ans);
        
        mem[r][c] = ans;
        
        return ans;
    }
}
