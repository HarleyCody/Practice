//Best Solution: Monotone stack curSum = sum at prev min + sum between cur and prev min
//Only record Sum at number i 
class Solution {
    public int sumSubarrayMins(int[] arr) {   
        int mod = (int) 1e9 + 7;
        int len = arr.length;
        int[] idx = new int[len];
        int[] sum = new int[len];
        int p = -1;
        
        int ans = 0;
        for(int i = 0; i < arr.length; ++i){
            while(p >= 0 && arr[idx[p]] > arr[i]){
                --p;
            }
            
            int prevIdx = -1;
            int prevSum = 0;
            if(p >= 0){
                prevIdx = idx[p];
                prevSum = sum[p];
            }else{
                p = -1;
            }
            
            int curSum = (prevSum + (i - prevIdx) * arr[i]) % mod;
            ans = (ans + curSum) % mod;
            idx[++p] = i;
            sum[p] = curSum;
        }
        return ans;
    }
}
