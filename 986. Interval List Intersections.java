class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int a = A.length, b = B.length;
        int ap = 0, bp = 0;
        int left = 0, right = 0;
        List<int[]> ans = new ArrayList();
        while(ap < a && bp < b){
            // left is max of start; right is min of end
            left = Math.max(A[ap][0], B[bp][0]);
            right = Math.min(A[ap][1], B[bp][1]);
            // valid intersection
            if(left <= right){
                ans.add(new int[]{left, right});
            }
            
            // move forward interval with min end;
            if(A[ap][1] < B[bp][1]) ++ap;
            else ++bp;
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
