class Solution {
    public int[] sortedSquares(int[] A) {
        if(A.length == 0) return A;
        // two pointers
        int l = 0, r = A.length - 1;
        int[] ans = new int[A.length];
        int idx = A.length - 1;
        while(l < r){
            // only compare squre value, bigger one is recorded in the ans Array from tail to head
            if(A[l] * A[l] < A[r] * A[r]){
                ans[idx--] = A[r] * A[r--];
            }else if(A[l] * A[l] > A[r] * A[r]){
                ans[idx--] = A[l] * A[l++];
            }else{
                ans[idx--] = A[l] * A[l++];
                ans[idx--] = A[r] * A[r--];
            }
        }
        // when l = r and there is one element still not be recorded, record it to head
        if(idx >= 0) ans[idx] = A[l] * A[l];
        return ans;
    }
}
