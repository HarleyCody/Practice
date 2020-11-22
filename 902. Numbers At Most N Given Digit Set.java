_______________________________________________________________________________________Concise Solution________________________________________________________________________
https://leetcode.com/problems/numbers-at-most-n-given-digit-set/discuss/168279/Python-O(logN)
    def atMostNGivenDigitSet(self, D, N):
        N = str(N)
        n = len(N)
        res = sum(len(D) ** i for i in range(1, n))
        i = 0
        while i < len(N):
            res += sum(c < N[i] for c in D) * (len(D) ** (n - i - 1))
            if N[i] not in D: break
            i += 1
        return res + (i == n)
        
Here is my explanation:

N has n digits, so all numbers less than n digits are valid, which are: sum(len(D) ** i for i in range(1, n))
The loop is to deal with all numbers with n digits, considering from N[0], N[1] back to N[n-1]. For example, N[0] is valid only for c in D if c <= N[0]. If c < N[0], then N[1], ..., N[n-1] can take any number in D but if c == N[0], then we need consider N[1], and the iteration repeats. That's why if N[i] not in D, then we don't need to repeat the loop anymore.
Finally i==n is addressed at the end when there exists all c in D that matches N

___________________________________________________________________________________My Solution(Already best)_________________________________________________________________________________
//dp, dp[0][i] rocords the numbers of number that is equal to n.substring(0, i)(either 0, 1)
//dp[1][i] records the number of numbers that is smaller than n.substring(0, i)
//dp[1][cur] can be from dp[1][i - 1] + 1 (+1 is digits it per se) and dp[0][i - 1] (iff d < n.charAt(i));
class Solution {
    public int atMostNGivenDigitSet(String[] digits, int n) {
        char[] tar = String.valueOf(n).toCharArray(); 
        char[] digs = s2c(digits);
        
        int len = tar.length;
        int[][] dp = new int[2][len];
        
        for(char c : digs){
            if(tar[0] == c){
                dp[0][0] = 1;
            }else if(tar[0] > c){
                ++dp[1][0];
            }
        }
        for(int i = 1; i < len; ++i){
            for(char c : digs){
                dp[1][i] += dp[1][i - 1] + 1;
                if(c == tar[i]){
                    dp[0][i] = dp[0][i - 1];
                }else if(c < tar[i]){
                    dp[1][i] += dp[0][i - 1]; 
                }
            }
        }
        
        return dp[0][len - 1] + dp[1][len - 1];
    }
    
    private char[] s2c(String[] strs){
        int len = strs.length;
        char[] ans = new char[len];
        
        for(int i = 0; i < len; ++i){
            ans[i] = strs[i].charAt(0);
        }
        
        return ans;
    }
}
