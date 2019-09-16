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
