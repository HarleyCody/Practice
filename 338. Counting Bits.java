______________________________________________________________Clean up DP__________________________________________________________________
// general dp[i] = dp[i - base] + 1;
// update base when base * 2 = i; so i = dp[0] + 1;
class Solution {
    public int[] countBits(int num) {
        
        int[] ans = new int[num + 1];
        ans[0] = 0;
        
        int num1 = 1, base = 1;
        for(int i = 1; i < num + 1; ++i){
            ans[i] = 1;
            if(2 * base == i){
                base <<= 1;
            }
            ans[i] += ans[i - base]; 
        }
        return ans;
    }
}
_______________________________________________________________My dp_______________________________________________________________________
// ans[i] = base + offset
// increase base when base == offset
class Solution {
    public int[] countBits(int num) {
        
        int[] ans = new int[num + 1];
        ans[0] = 0;
        
        int num1 = 1, base = 1;
        for(int i = 1; i < num + 1; ++i){
            ans[i] = 1;
            if(2 * base == i){
                base <<= 1;
                ans[i] = 1;
            }else{
                ans[i] += ans[i - base]; 
            }
        }
        return ans;
    }
}
