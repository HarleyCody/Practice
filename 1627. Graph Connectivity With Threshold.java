//Union find from small to large
//in order to find the gcd larger than threshold: use threshold + 1 as base, times 1 ~ m until m * base and union (base and m * base)
class Solution {
    int[] parent;
    int[] rank;
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        
        int len = queries.length;
        List<Boolean> ans = new ArrayList();
        if(threshold == 0){
            for(int i = 0; i < len; ++i){
                ans.add(true);
            }
            return ans;
        }
        for(int i = 1; i <= n; ++i){
            parent[i] = i;
        }
        for(int i = threshold + 1; i <= n; ++i){
            int times = 1, num = i;
            while(num <= n){
                union(i, num);
                ++times;
                num = i * times;
            }
        }
        
        int x, y, px, py;
        for(int i = 0; i < len; ++i){
            x = queries[i][0];
            y = queries[i][1];
            px = find(x);
            py = find(y);
            ans.add(px == py);
        }
        return ans;
    }
    
    private void union(int x, int y){
        int px = find(x);
        int py = find(y);
        
        if(px == py) return;
        
        if(rank[px] > rank[py]){
            parent[py] = px;
        }else if(rank[py] > rank[px]){
            parent[px] = py;
        }else{
            parent[py] = px;
            rank[px]++;
        }
    }
    
    private int find(int x){
        while(x != parent[x]){
            x = parent[x];
        }
        return x;
    }
}
