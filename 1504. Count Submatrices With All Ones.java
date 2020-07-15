______________________________________________________________________________Best Solution______________________________________________________________________________
class Solution {
    // Rectangles !!!!! not square
    /*Imagine you have an one-dimension array, how to count number of all 1 submetrics (size is 1 * X). It's pretty simple right?

        int res = 0, length = 0;
        for (int i = 0; i < A.length; ++i) {
	        length = (A[i] == 0 ? 0 : length + 1);
	        res += length;
        }
        return res;
    */
    public int numSubmat(int[][] mat) {
        //看你的2d方法不如人家3d快，主要是因为你的stack里面放的是int数组，人家只放一个index
        int res = 0;
        // get length of rectangles in a col
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (i != 0 && mat[i][j] != 0) mat[i][j] = mat[i - 1][j] + 1;
            }
            res += helper(mat[i]);
        }
        return res;
    }
    private int helper(int[] A) {
        print(A);
	    int[] sum = new int[A.length];
	    int[] stack = new int[A.length + 1];
        int index = -1;
        
        //key
	    for (int i = 0; i < A.length; ++i) {

		    while (index != -1 && A[stack[index]] >= A[i]) index--;
            // maximize width
            // get width
		    if (index != -1) {
                // height is not largest, accumualte the val
			    int preIndex = stack[index];
			    sum[i] = sum[preIndex];
			    sum[i] += A[i] * (i - preIndex);
		    } else {
                // this height is largest
			    sum[i] = A[i] * (i + 1);
		    }
            stack[++index] = i;
	    }

	    int res = 0;
	    for (int s : sum) res += s;

	    return res;
    }
}
