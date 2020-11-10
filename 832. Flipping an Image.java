____________________________________________________________________________________My Solution____________________________________________________________________________
// left and right different: reverse and revert == original
// left and right same: reverse and revert = revert;
class Solution {
    public int[][] flipAndInvertImage(int[][] A) {
        int colLen = A[0].length;
        int l = 0, r = colLen - 1;
        for(int[] row : A){
            l = 0;
            r = colLen - 1;
            while(l <= r){
                if(row[l] == row[r]){
                    if(l != r){
                        row[l] = 1 - row[l];
                    }
                    row[r] = 1 - row[r]; 
                }
                ++l;
                --r;
            }
        }
        return A;
    }
}
