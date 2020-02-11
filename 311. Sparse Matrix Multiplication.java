_______________________________________________________________Best Solution____________________________________________________________
class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        if(A.length == 0) {
            return new int[0][0];
        }
        if(A[0].length == 0) {
            return new int[A.length][0];
        }
        
        //record index of none zero (zero times any == zero ) element of matrix b
        ArrayList<Integer>[] BB = new ArrayList[B.length];
        for(int i = 0; i < B.length; i++) {
            BB[i] = new ArrayList<>();
            for(int j = 0; j < B[0].length; j++) {
                if(B[i][j] != 0)
                    BB[i].add(j);
            }
        }
        
        int[][] result = new int[A.length][B[0].length];
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < A[0].length; j++) {
                if(A[i][j] != 0) {
                    for(int num : BB[j]) {
                        // only calculate valid value
                        result[i][num] += A[i][j] * B[j][num];
                    }
                }
            }
        }
        
        return result;
    }
}
___________________________________________________________My Solution____________________________________________________________________
class Solution {
    // directly calcualte
    public int[][] multiply(int[][] A, int[][] B) {
        if(A.length == 0) return B;
        if(B.length == 0) return A;
        int row = A.length, col = B[0].length;
        int[][] ans = new int[row][col];
        for(int i = 0; i < row; ++i){
            for(int j = 0; j < col; ++j){
                int sum = 0;
                for(int idx = 0; idx < B.length; ++idx){
                    sum += A[i][idx] * B[idx][j];
                }
                ans[i][j] = sum;
            }
        }
        return ans;
    }
}
