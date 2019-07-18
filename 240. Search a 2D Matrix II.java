________________________________________________________Best Solution__________________________________________________________
class Solution {
    //find a middle point(one scenario corresponding to one direction)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        // use bottom left as middle point, so if tar > cur, only can go left, if tar < cur, only can go up.
        int i = matrix.length - 1, j = 0;
        while (i >= 0 && j < matrix[0].length) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                i--;
            } else {
                j++;
            }
            
        }
        return false;
    }
}
_________________________________________________________My Solution___________________________________________________________
class Solution {
    // find row by row and col by col
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0) return false;
        int row = matrix.length, col = matrix[0].length;
        if(target < matrix[0][0] || target > matrix[row - 1][col - 1])return false;

        for(int i = 0; i < row; ++i){
            if(matrix[i][0] > target) return false;
            if(matrix[i][col - 1] < target) continue;
            for(int j = 0 ; j < col; ++j){
                if(matrix[i][j] == target) return true;
            }
        }
        return false;
    }
}
