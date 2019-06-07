class Solution {
    public void rotate(int[][] matrix) {
        
        int len = matrix.length;
        for(int i = 0; i < len; ++i){
            // swap based on principal diagonal
            for(int j = i + 1; j < len; ++j){
                swap(i, j, j, i, matrix);
            }
        }
        for(int i = 0; i < len; ++i){
            //swap N-th of the row to last N-th of the row 
            for(int j = 0; j < len/2; ++j){
                swap(i, j, i, len - j - 1, matrix);
            }
        }
    }
    
    public void swap(int r1, int c1, int r2, int c2, int[][] matrix){
        int temp = matrix[r1][c1];
        matrix[r1][c1] = matrix[r2][c2];
        matrix[r2][c2] = temp;
    }
}
