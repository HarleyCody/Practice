//My Solution: based on the explaination for test case, start from 9 then 22, 34, 45, 55, 64, 72, 79, 85, 90, 94, 97, 99, and 100.
//Diff between then is 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13. (ans is 14 times)
//So start from 0 when curFloor is larger than target, that is the min steps
class Solution {
    public int twoEggDrop(int n) {
        int ans = 0;
        int curFloor = 0;
        int diff = 1;
        while(curFloor < n){
            curFloor += diff;
            ++diff;
            ++ans;
        }
        return ans;
    }
}
//Dp Solution: Break previous or not break current, get min
class Solution {
    int[][] dp;
    public int twoEggDrop(int n) {
        dp = new int[n+1][3];
        return helper(n , 2);
    }
    public int helper(int f , int e){
        if(f == 0 || f == 1)
            return f;
        
        if(e == 1)
            return f;
        if(dp[f][e] != 0)
            return dp[f][e];
        
        int ans = Integer.MAX_VALUE;
        
        for(int k = 1; k<f ;k++){
            int tmp = 1 + Math.max(helper( k - 1 , e - 1) , helper(f - k , e) );
            
            ans = Math.min( ans , tmp );
        }
        return dp[f][e] = ans;
    }
}
