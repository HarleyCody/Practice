class NumMatrix {
    int[][] recorder;
    public NumMatrix(int[][] matrix) {
        if(matrix.length == 0) return;
        // record sum of points on the top left of current point, sum includes current point
        recorder = new int[matrix.length + 1][matrix[0].length + 1];
        for(int i = 1; i < recorder.length; ++i){
            for(int j = 1; j < recorder[0].length; ++j){
                recorder[i][j] = matrix[i - 1][j - 1] + recorder[i - 1][j] + recorder[i][j-1] - recorder[i - 1][j - 1] ;
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        // sum = sum at bottom right point - sum at left - sum at top + sum at top left(cus it is deducted twice)
        return recorder[row2 + 1][col2 + 1] - recorder[row2 + 1][col1] - recorder[row1][col2 + 1] + recorder[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
