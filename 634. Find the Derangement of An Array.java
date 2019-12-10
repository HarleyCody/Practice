_____________________________________________________Best Solution____________________________________________________________
class Solution {
//dp Finbonach F(i) = (i - 1) * f(i - 1) * f(i - 2)
    public int findDerangement(int n) {
        if(n < 4) return n - 1; 
        long prev = 1;
        long cur = 2;
        long temp = 0;
        for(int i = 3; i < n; ++i){
            temp = cur;
            cur = i * (prev + cur) % 1000000007;
            prev = temp;
        }
        return (int)cur;
    }
}
______________________________________________________My Solution_____________________________________________________________
class Solution {//dp improved
    public int findDerangement(int n) {
        if(n < 4) return n - 1; 
        int prev = 0;
        int cur = 0;
        for(int i = 1; i < n; ++i){
             // use mod directly will faster speed
            cur = (int)(((long)(i + 1) * prev) % 1000000007);
            if(i % 2 == 1) ++cur;
            else --cur;
            prev = cur;
        }
        return cur;
    }
}

class Solution {
//dp[i] = i * dp[i-1] + j( i is even j = +1 otherwise j = -1
    private int mod = 1000000007;
    public int findDerangement(int n) {
        long [] dp = new long[n + 1];
        dp[1] = 0;
        for(int i = 2; i <= n; ++i){
            dp[i] = (i * dp[i - 1]) % mod;
            if(i % 2 == 0) ++dp[i];
            else --dp[i];
        }
        return (int)(dp[n] % mod);
    }
}
