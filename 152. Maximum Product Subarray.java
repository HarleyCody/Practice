_____________________________________________________O(n) Solution_____________________________________________________________
class Solution {
    public int maxProduct(int[] nums) {
        
        int prod = 1;
        int result = Integer.MIN_VALUE;
        
        for(int i = 0; i < nums.length; i++) {
            prod = prod * nums[i];
            result = Math.max(prod, result);
            if(prod == 0) {
                prod = 1;
            }
        }
        prod = 1;
        
        for(int i = nums.length - 1; i >= 0; i--) {
        
            prod = prod * nums[i];
            result = Math.max(prod, result);
            if(prod == 0) {
                prod = 1;
            }      
        }
        return result;
    }
}
_____________________________________________________DP Solution_______________________________________________________________
public class Solution {
  public int maxProduct(int[] A) {
    if (A == null || A.length == 0) {
        return 0;
    }
    // record max value of each number
    int[] f = new int[A.length];
    // record min value of each number
    int[] g = new int[A.length];
    f[0] = A[0];
    g[0] = A[0];
    int res = A[0];
    for (int i = 1; i < A.length; i++) {
        //f[i-1] *A[i]: previous accumulatively max value * current
        //g[i-1] *A[i]: previous accumulatively min value * current
        // previous accumulatively max value: new Start from A[i] or product of i-1 and i is larger;
        // previous accumulatively min value: new Start from A[i] or product of i-1 and i is smaller;
        f[i] = Math.max(Math.max(f[i - 1] * A[i], g[i - 1] * A[i]), A[i]);
        g[i] = Math.min(Math.min(f[i - 1] * A[i], g[i - 1] * A[i]), A[i]);
        res = Math.max(res, f[i]);
    }
    return res;
  }
}
______________________________________________________My Solution______________________________________________________________
class Solution {
    public int maxProduct(int[] nums) {
        int ans = Integer.MIN_VALUE, cur = 0;
        for( int i = 0; i < nums.length; ++i){
            cur = 1;
            for(int j = i; j < nums.length; ++j){
                cur *= nums[j];
                ans = cur > ans ? cur : ans;
            }
        }
        return ans;
    }
}
