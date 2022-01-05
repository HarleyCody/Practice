//My Solution: Union found, union to the group with larger size, return time when all are unioned to gether
//existing friends should not be unioned again
class Solution {
    int[] parent;
    int[] rank;
    public int earliestAcq(int[][] logs, int n) {
        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; ++i){
            parent[i] = i;
            rank[i] = 1;
        }
        
        Arrays.sort(logs, (a, b) -> a[0] - b[0]);
        
        for(int[] log : logs){
            union(log[1], log[2]);
            if(rank[find(log[1])] == n) return log[0];
        }
        
        return -1;
    }
    
    private int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
    
    private void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        if(pa == pb) return;
        if(rank[pa] >= rank[pb]){
            rank[pa] += rank[pb];
            parent[pb] = pa;
        }else{
            rank[pb] += rank[pa];
            parent[pa] = pb;
        }
    }
}
