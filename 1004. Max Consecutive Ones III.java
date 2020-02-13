________________________________________________________________My Solution________________________________________________________________
// sliding window to control flippings
class Solution {
    public int longestOnes(int[] A, int K) {
        int l = 0, r = 0;
        int len = A.length;
        int ans = -1;
        while(l < len){
            while(r < len && K > 0){
                if(A[r] == 0){
                    --K;
                    ++r;
                }
                while(r < len && A[r] == 1){
                    ++r;
                }
            }
            ans = Math.max(ans, r - l);
            if(A[l] == 0) ++K;
            ++l;
        }
        return ans;
    }
}
