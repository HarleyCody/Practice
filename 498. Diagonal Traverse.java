_________________________________________________________Best Solution_________________________________________________________
class Solution {
        public int[] findDiagonalOrder(int[][] matrix) {
        int m = matrix.length;
        if(m == 0) return (new int[0]); //if empty matrix
        int n = matrix[0].length;
        int[] ans = new int[m*n];
        
        int resultIndex = 0;
        int i = 0, j = 0;
        
        while(i < m && j < n){
            
            while(i >= 0 && j < n){ //moving up
                ans[resultIndex++] = matrix[i][j];
                i--;    //move up
                j++;    //move right
                
            }
            // currently i, j is out of array and at top right position;
            // move back to last row as next point only can be in next or right
            i++;
            // move to down element;
            if(j == n){ //reached end of columns
                i++;
                j--;
            }
            while(j >= 0 && i < m){ //moving down
                ans[resultIndex++] = matrix[i][j];
                //Move down
                i++;
                //move left
                j--;
            }
            // currently i, j is out of array and at bottom left position;
            // move to down element
            j++;
            //if reach under rows, move to right element
            if(i == m){
                i--; 
                j++;
            }
        }
        return ans;
    }
}
__________________________________________________________My Solution__________________________________________________________
class Solution {
    int[][] dirs = new int[][]{{-1, 1},{1, -1}};
    int[] ans;
    // save diagonal in dirction.
    public int[] findDiagonalOrder(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return new int[0];
        ans = new int[matrix.length * matrix[0].length];
        int dir = 0, x = 0, y = 0, index = 0;
        while(index < ans.length){
            ans[index++] = matrix[x][y];
            int nextX = x + dirs[dir][0];
            int nextY = y + dirs[dir][1];
            // if next one is out of matrix, find a new starter for new diagonal.
            if(nextX < 0 || nextX >= matrix.length || nextY < 0 || nextY >= matrix[0].length){
                int newPos[] = nextPos(x, y, matrix, dir);
                dir = (dir + 1) % 2;
                x = newPos[0];
                y = newPos[1];
                
            }else{
                x = nextX;
                y = nextY;
            }
        }
        return ans;
    }
    // find new starter.
    private int[] nextPos(int x, int y, int[][]matrix, int dir){
        int[] ans = new int[2];
        int newX = x;
        int newY = y + 1;
        // go right-top
        if(dir == 0){
            // test go right;
            if(newX < matrix.length && 0 <= newX && newY < matrix[0].length && 0 <= newY){
                ans[0] = newX;
                ans[1] = newY;
                return ans;
            }
            newX = x + 1;
            newY = y;
            // test go down
            if(newX < matrix.length && 0 <= newX && newY < matrix[0].length && 0 <= newY){
                ans[0] = newX;
                ans[1] = newY;
                return ans;
            }
        // go bottom-left
        }else{
            newX = x + 1;
            newY = y;
            // test go down
            if(newX < matrix.length && 0 <= newX && newY < matrix[0].length && 0 <= newY){
                ans[0] = newX;
                ans[1] = newY;
                return ans;
            }
            newX = x;
            newY = y + 1;
            // test go right
            if(newX < matrix.length && 0 <= newX && newY < matrix[0].length && 0 <= newY){
                ans[0] = newX;
                ans[1] = newY;
                return ans;
            }
        }
        return ans;
    }
}
