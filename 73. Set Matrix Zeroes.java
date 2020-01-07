_________________________________________________________________Best Solution_____________________________________________________________
class Solution {
    // save the header of row( ie, col0) as it will change at last;(as second iteration will start from bot-right) 
    // otherwise if header is set with mat[i][j] it will when it goes from bot-right, it may affect row 0 by set mat[0][0] = 0; 
    // if mat[i][j] == 0 then set mat[i][0] = mat[0][j] = 0;
    // mat[i][j] can be settled by mat[i][0] = 0 or mat[0][j] = 0;
    public void setZeroes(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        int c0 = 1;
        for(int i = 0; i < r; ++i){
            if(matrix[i][0] == 0) c0 = 0;
            for(int j = 1; j < c; ++j){
                if(matrix[i][j] == 0)
                matrix[i][0] = matrix[0][j] = 0;
            }
        }
        for(int i = r - 1; i >= 0; --i){
            for(int j = c - 1; j >= 1; --j){
                if(matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
            if(c0 == 0)matrix[i][0] = 0;
        }
    }
}
___________________________________________________________________My Solution_____________________________________________________________
// use queue record zeros;
class Solution {
    public void setZeroes(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        Queue<int[]> zeros = new LinkedList();
        for(int i = 0; i < r; ++i){
            for(int j = 0; j < c; ++j){
                if(matrix[i][j] == 0){
                    zeros.add(new int[]{i,j});
                }
            }
        }
        int[] row = new int[c]; 
        while(!zeros.isEmpty()){
            int[] cur = zeros.poll();
            int x = cur[0];
            int y = cur[1];
            matrix[x] = row;
            for(int i = 0; i < r; ++i){
                matrix[i][y] = 0;
            }
        }
    }
}
