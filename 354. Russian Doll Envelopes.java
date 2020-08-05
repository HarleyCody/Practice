____________________________________________________________________________________Best dp Solution________________________________________________________________________
O(nlogn)
class Solution {
    // first sort in maxEnvelopes sort the width with ascending order;
    // traverse all heights after sorted with descending at same width;
    // if current height is max means width > prev and height > prev, so add one more dolls
    // dp[] will be collections for max Russian dolls
    public int maxEnvelopes(int[][] envelopes) {
        //sort by x with ascending y with descending
        Arrays.sort(envelopes,(x, y) -> x[0] == y[0] ? y[1] - x[1] : x[0] - y[0]);
        int[] heights = new int[envelopes.length];
        for(int i=0; i<envelopes.length; i++){
            heights[i] = envelopes[i][1];
        }//end of for
        
        return lengthOfLIS(heights);
    }
    
    private int lengthOfLIS(int[] second_dimension){
        int[] dp = new int[second_dimension.length];
        int len = 0;
        for(int num: second_dimension){
            int i = Arrays.binarySearch(dp, 0, len, num);
            if(i < 0){
                i = -(i+1);
            }
            dp[i] = num;
            if(i == len){
                len++;
            }
        }//end of for
        
        return len;
    }
}
____________________________________________________________________________________My dp Solution________________________________________________________________________
O(n^2)
class Solution {
    // find previous one by one and record max dolls can collect at point i
    // dp[i] = dp[j] + 1;
    public int maxEnvelopes(int[][] dolls) {
        int len = dolls.length;
        if(len == 0){
            return 0;
        }
        Arrays.sort(dolls, (x, y) -> x[0] == y[0] ? x[1] - y[1] : x[0] - y[0]);
        int[] dp = new int[len];
        dp[0] = 1;
        int ans = 0;
        for(int i = 0; i < len; ++i){
            dp[i] = 1;
            for(int j = i; 0 <= j; --j){
                if(dolls[j][0] < dolls[i][0] && dolls[j][1] < dolls[i][1]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    
                }
            }
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }
    
}
