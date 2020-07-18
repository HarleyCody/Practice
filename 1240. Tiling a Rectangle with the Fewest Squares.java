____________________________________________________________________________________Best Solution________________________________________________________________________
class Solution {
    // set used for prunning
    // use h to record height for every column
    // so h can be used to find cur lowest left grid to fill.
    // loweset left 
    Map<Long, Integer> set = new HashMap<>();
    int res = Integer.MAX_VALUE;
    public int tilingRectangle(int n, int m) {
        if (n == m) return 1;
        if (n > m) {
            int temp = n;
            n = m;
            m = temp;
        }
        dfs(n, m, new int[n + 1], 0);
        return res;
    }
    
    private void dfs(int n, int m, int[] h, int cnt) {
        if (cnt >= res) return;
        boolean isFull = true;
        int pos = -1, minH = Integer.MAX_VALUE;
        
        // find minimal height to fill max;
        for (int i = 1; i <= n; i++) {
            if (h[i] < m) isFull = false;
            if (h[i] < minH) {
                pos = i;
                minH = h[i];
            }
        }
        if (isFull) {
            res = Math.min(cnt, res);
            return;
        }
        
        long key = 0, base = m + 1;
        for (int i = 1; i <= n; i++) {
            key += h[i] * base;
            base *= m + 1;
        }
        if (set.containsKey(key) && set.get(key) <= cnt) return;
        set.put(key, cnt);
        
        int end = pos;
        // find end with at least m - minH, fill this block
        // end is at the end of block, inclusive
        while (end + 1 <= n && h[end + 1] == h[pos] && (end + 1 - pos + 1 + minH) <= m) end++;
        // fill block with width j + 1 - pos;
        for (int j = end; j >= pos; j--) {
            int curH = j - pos + 1;
            
            int[] next  = Arrays.copyOf(h, n + 1);
            for (int k = pos; k <= j; k++) {
                next[k] += curH;
            }
            dfs(n, m, next, cnt + 1);
        }
        
    }
}
