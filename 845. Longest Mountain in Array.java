________________________________________________________________________________My Solution(O(n) + Space O(1))_____________________________________________________________
// climb to top and go down the mountain
// recorde start and top end is different from them
// calcualate length
class Solution {
    public int longestMountain(int[] A) {
        int len = A.length;
        int idx = 0, ans = 0;
        while(idx < len - 1){
            int l = idx;
            while(idx < len - 1 && A[idx] < A[idx + 1])++idx;
            int top = idx;
            while(idx < len - 1 && A[idx] > A[idx + 1])++idx;
            
            if(idx != top && top != l){
                ans = Math.max(idx - l + 1, ans);
            }
            while(idx < len - 1 && A[idx] >= A[idx + 1]) ++idx;
        }
        return ans;
    }
}
________________________________________________________________________________My Solution________________________________________________________________________________
class Solution {
    //Space O(N)
    // two pass
    public int longestMountain(int[] A) {
        int len = A.length;
        int[] lUp = new int[len];
        int[] rUp = new int[len];
        
        for(int i = 1; i < len; ++i){
            int l = i, r = len - 1 - i;
            if(A[l] > A[l - 1]){
                lUp[l] = lUp[l - 1] + 1;
            }
            if(A[r] > A[r + 1]){
                rUp[r] = rUp[r + 1] + 1;
            }
        }

        int ans = 0;
        for(int i = 1; i < len; ++i){
            if(lUp[i] * rUp[i] == 0){
                continue;
            }
            ans = Math.max(ans, lUp[i] + rUp[i] + 1);
        }
        return ans;
    }
}
