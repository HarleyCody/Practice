_____________________________________________________________________________My Solution______________________________________________________________________
class Solution {
    public boolean isIdealPermutation(int[] A) {
        int len = A.length;
        int[] tailMin = new int[len];
        tailMin[len - 1] = A[len - 1];
        for(int i = len - 2; 0 <= i; --i){
            tailMin[i] = Math.min(A[i], tailMin[i + 1]);
        }
        int local = 0, global = 0;
        for(int i = 0; i < len - 1; ++i){
            if(A[i] > A[i + 1] && i < len - 2 && A[i] > tailMin[i + 2] ||
              A[i] <= A[i + 1] && i < len - 1 && A[i] > tailMin[i + 1]){
                return false;
            }
        }
        return true;
    }
}
