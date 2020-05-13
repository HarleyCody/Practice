___________________________________________________________Best Solution____________________________________________________________________
class Solution {
    // sol1: left top corner
    // T: O(mn)
    // S: O(1)
    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for(int i = 1; i < m; ++i){
            for(int j = 1; j < n; ++j){
                if(matrix[i][j] != matrix[i - 1][j - 1]) return false;
            }
        }
        return true;
    }
}
_____________________________________________________________My Solution____________________________________________________________________
class Solution {
    // root idx of diagonal is rootX: X - dis; rootY: Y - dis;
    // dis = Min(X, Y);
    public boolean isToeplitzMatrix(int[][] matrix) {
        int r = matrix.length, c = matrix[0].length;
        
        for(int i = 0; i < r; ++i){
            for(int j = 0; j < c; ++j){
                int dis = Math.min(i, j);
                int rootX = i - dis, rootY = j - dis;
                if(matrix[i][j] != matrix[rootX][rootY]){
                    return false;
                }
            }
        }
        return true;
    }
}
