__________________________________________________________My Solution(while loop)________________________________________________________
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0) return false;
        int l = 0, r = matrix.length * matrix[0].length - 1;
        while(l < r){
            int mid = (l + r) >> 1;
            if(matrix[mid / matrix[0].length][mid % matrix[0].length] == target) return true;
            if(matrix[mid / matrix[0].length][mid % matrix[0].length] < target){
                l = mid + 1;
            }else{
                r = mid;
            }
        }
        return matrix[l / matrix[0].length][l % matrix[0].length] == target;
    }
}
___________________________________________________________My Solution(recursion)_________________________________________________________
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0) return false;
        return searchMatrix(matrix, target, 0, matrix.length * matrix[0].length - 1);
    }
    private boolean searchMatrix(int[][] m, int tar, int s, int e){
        int mid = (s + e) / 2;
        int X = mid / m[0].length, Y = mid % m[0].length;
        if(m[X][Y] == tar) return true;
        if(s >= e) return false;
        if(m[X][Y] > tar) return searchMatrix(m, tar, s, mid);
        else return searchMatrix(m, tar, mid + 1, e);
    }
}
