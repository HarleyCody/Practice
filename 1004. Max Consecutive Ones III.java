________________________________________________________________Best Solution(Sliding window)______________________________________________
class Solution {
// left chase right only when left have to keep number of zero is K
    public int longestOnes(int[] A, int k) {
        int left = 0, right = 0, n = A.length;
        while (right < n) {
            // right keeps move to right
            if (A[right++] == 0) k--;
            if (k < 0) {
                // left will chasing right with same pace, so distance keeps unchanged
                if (A[left++] == 0) k++;
            }
        }
        // distance between left and right will increase; as left only move when k < 0 but right always keep move to right
        return right - left; // at the end, right == n
    }
}
____________________________________________________________________My Solution(Sliding window)______________________________________________
// Sliding window control number of flipping zero
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
