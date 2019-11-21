class Solution {
    public int twoSumLessThanK(int[] A, int K) {
        Arrays.sort(A);
        int i = 0, j = A.length - 1;
        int ans = Integer.MIN_VALUE;
        while(i < j){
            int sum = A[i] + A[j];
            if(sum >= K)j--;
            if(sum < K){
                ans = Math.max(ans, sum);
                i++;
            }
        }
        return ans == Integer.MIN_VALUE ? -1 : ans;
    }
}
