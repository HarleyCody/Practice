//Best Solution: Union Found with criteria that (cost + build) and build + build 
// Only join when cost + build is smaller;
class Solution {
    int root[];
    int rank[];
    
    private int find(int n) {
        if(root[n] != n) {
            root[n] = find(root[n]);
        }
        return root[n];
    }
    
    private void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x != y) {
            if(rank[x] <= rank[y]) {
                root[y] = x;
            } else {
                root[x] = y;
            }
        }
    }
    
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        rank = wells;
        root = new int[n];
        
        for(int i = 0; i < n; i++) {
            root[i] = i;
        }
        
        Arrays.sort(pipes, (int[] p1, int[] p2) -> p1[2] - p2[2]);
        
        int costOfPipes = 0;
        for(int[] pipe : pipes) {
            int a = pipe[0] - 1;
            int b = pipe[1] - 1;
            int cost = pipe[2];
            
            int rootA = find(a);
            int rootB = find(b);
            
            if(rootA != rootB) {
                if(cost + Math.min(wells[rootA], wells[rootB]) < wells[rootA] + wells[rootB]) {
                    union(a, b);
                    costOfPipes += cost;
                }
            }
        }
                
        for(int i = 0; i < root.length; i++) {
            if(root[i] == i) {
                costOfPipes += wells[i];
            }
        }
        
        return costOfPipes;
    }
}
