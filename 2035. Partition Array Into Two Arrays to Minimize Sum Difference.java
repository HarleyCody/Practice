//Best Solution: Get the result of left half and right half of result and combine them into answer
class Solution {
    public int minimumDifference(int[] nums) {
        int n = nums.length>>1, total = Arrays.stream(nums).sum();
        int[][] subl = new int[n+1][], subr = new int[n+1][];
        for (int i = 0, sz = 1; i <= n; ++i, sz = sz*(n-i+1)/i) {
            subl[i] = new int[sz+1];
            subr[i] = new int[sz+1];
        }
        dfs(nums, 0, 0, 0, 0, subl, subr);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= n && ans> 0; ++i) {
            Arrays.sort(subl[i],0,subl[i].length-1);
            Arrays.sort(subr[n-i],0,subr[n-i].length-1);
            
            int j = 0, k = subr[n-i].length-2;
            while (j+1 < subl[i].length && k >= 0) {
                int v = subl[i][j]+subr[n-i][k];
                if (v > (total>>1)) k--;
                else {
                    ans = Math.min(total - (v<<1), ans);
                    j++;
                }
            }
        }
        return ans;
    }
    private void dfs(int[] a, int p, int sl, int sr, int sz, int[][] l, int[][] r) {
        if (p == (a.length>>1)) {
            int ps = l[sz][l[sz].length-1]++;
            l[sz][ps] = sl;
            ps = r[sz][r[sz].length-1]++;
            r[sz][ps] = sr;
            return;
        }
        dfs(a, p+1, sl, sr, sz, l, r);
        dfs(a, p+1, sl+a[p], sr+a[p+(a.length>>1)], sz+1, l, r);
    }
}
