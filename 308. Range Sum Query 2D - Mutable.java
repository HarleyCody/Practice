____________________________________________________________________________My Solution____________________________________________________________________________
class NumMatrix {
    // prefix sum of every row, not multi-row
    // add row by row in range col1 + col2
    // only update col, not prefix sum
    int[][] table;
    int R, C;
    public NumMatrix(int[][] matrix) {
        table = matrix;
        R = matrix.length;
        C = R == 0 ? 0 : matrix[0].length;
        for(int r = 0; r < R; ++r){
            for(int c = 0; c < C; ++c){
                if(c == 0){
                    continue;
                }
                matrix[r][c] += matrix[r][c - 1];
            }
        }
    }
    
    public void update(int row, int col, int val) {
        if(R == 0 || C == 0){
            return;
        }
        int ogVal = col == 0 ? table[row][0] : table[row][col] - table[row][col - 1];
        int diff = val - ogVal;
        for(int c = col; c < C; ++c){
            table[row][c] += diff;    
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(R == 0 || C == 0){
            return 0;
        }
        int ans = 0;
        for(int r = row1; r <= row2; ++r){
            ans += col1 == 0 ? table[r][col2] - 0 : table[r][col2] - table[r][col1 - 1];
        }
        
        return ans;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
