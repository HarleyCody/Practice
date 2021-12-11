//My Solution: Use sliding window to record number of 1 in the window, window size = number of one in data
//count one. ans = Math.min(numOfOne - count one, ans);
class Solution {
    public int minSwaps(int[] data) {
        int numOfOne = 0;
        for(int i : data){
            numOfOne += i;
        }
        
        int l = 0;
        int r = 0;
        int sum = 0;
        while(r < numOfOne){
            sum += data[r++];
        }
        
        int ans = numOfOne - sum;
        while(r < data.length){
            sum += data[r++] - data[l++];
            ans = Math.min(ans, numOfOne - sum);
        }
        
        return ans;
    }
}
