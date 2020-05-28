class Solution {
    // find peak return
    public int peakIndexInMountainArray(int[] A){
        int len = A.length;
        
        for(int i = 0; i < len - 1; ++i){
            if(A[i] > A[i + 1]){
                return i;
            }
        }      
        throw new NullPointerException("Invalid Input");  
    }
}
