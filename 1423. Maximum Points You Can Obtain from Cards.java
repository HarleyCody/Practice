____________________________________________________________________________________My Solution____________________________________________________________________________________
class Solution {
    // left head + right head
    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length, right = 0;
        for(int i = 0; i < k; ++i){
            right += cardPoints[len - i - 1];
        }
        
        int ans = right;
        int left = 0;
        for(int i = 0; i < k; ++i){
            left += cardPoints[i];
            right -= cardPoints[len - k + i];
            ans = Math.max(left + right, ans);
        }
        
        return ans;
    }
}
