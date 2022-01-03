//Best Solution: Use the first row and do the binary search for the rest of rows;
class Solution {
    public int smallestCommonElement(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        for (int j = 0; j < m; j++) {
            boolean found = true;
            for (int i=1; i<n; i++) {
              // found = Arrays.binarySearch(mat[i], mat[0][j]);
                found = binarySearch(mat[i], mat[0][j]);
                if (!found) {
                    break;
                }
            }
            if (found) {
                return mat[0][j];
            }
        }
        return -1;
    }
    
    private boolean binarySearch(int[] arr, int tar){
        int l = 0;
        int r = arr.length - 1;
        while(l <= r){
            int m = (l + r) / 2;
            if(arr[m] == tar) return true;
            if(arr[m] < tar){
                l = m + 1;
            }else{
                r = m - 1;
            }
        }
        
        return false;
    }
}

//My Solution: count the frequence and return immediately
class Solution {
    public int smallestCommonElement(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        int[] freq = new int[10001];
        for(int r = 0; r < row; ++r){
            for(int c = 0; c < col; ++c){
                ++freq[mat[r][c]];
                if(freq[mat[r][c]] == row) return mat[r][c];
            }
        }

        return -1;
    }
}
//My Solution: Use pinter to record the position of element, like merge sort but move the pointers untill all values are same
class Solution {
    public int smallestCommonElement(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        int[] pt = new int[row];
        boolean done = false;
        
        while(!done){
            int max = 0;
            for(int r = 0; r < row; ++r){
                max = Math.max(max, mat[r][pt[r]]);
            }
            boolean moved = false;
            for(int r = 0; r < row; ++r){
                if(max > mat[r][pt[r]]){
                    ++pt[r];
                    moved = true;
                    if(pt[r] == col){
                        done = true;
                    }
                }
            }
            if(!moved) return max;
            
        }

        return -1;
    }
}
