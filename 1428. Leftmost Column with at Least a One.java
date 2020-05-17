__________________________________________________________My Solution O(n + m)___________________________________________________________________
class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> info = binaryMatrix.dimensions();
        int row = info.get(0), col = info.get(1);
        
        int r = 0, c = col - 1;
        while(0 <= c && r < row){
            if(binaryMatrix.get(r, c) == 1){
                --c;
            }else{
                ++r;
            }
        }
        return r == row && c == col - 1 ? -1 : c + 1; 
    }
}
__________________________________________________________My Solution O(nlog(m))___________________________________________________________________
// non descending, determine col by binary search
// traverse every val in that col
class Solution {
    static int row = -1, col = -1;
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> info = binaryMatrix.dimensions();
        row = info.get(0);
        col = info.get(1);
        return checkOne(binaryMatrix);
    }
                
    private int checkOne(BinaryMatrix binaryMatrix){
        int l = 0, r = col;
        while(l < r){
            int m = (l + r) / 2;
            if(hasOne(binaryMatrix, m)){
                r = m;
            }else{
                l = m + 1;
            }
        }
        return l == col ? -1 : r;
    }
    private boolean hasOne(BinaryMatrix binaryMatrix, int col){
        for(int i = 0; i < row; ++i){
            if(binaryMatrix.get(i, col) == 1){
                return true;
            }
        }
        return false;
    }
}
