_________________________________________________________Best Solution(UF + pq)________________________________________________
// UF count circles pq choose the minimal cost to unite two cities
class Solution {
    int[] rank, parent;
    int n;
    public int minimumCost(int N, int[][] connections) {
        if (N <= 1) {
            return 0;
        }
        if (connections.length < N - 1) {
            return -1;
        }
        rank = new int[N + 1];
        parent = new int[N + 1];
        n = N;
        for(int i = 0; i < N + 1; ++i){
            parent[i] = i;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[2] - b[2];
            }
        });
        for (int[] road: connections) {
            pq.add(road);
        }
        int cost = 0;
        while(n > 1 && !pq.isEmpty()){
            int[] cons = pq.poll();
            if(find(cons[0]) != find(cons[1])){
                cost += cons[2];
                union(cons[0], cons[1]);
            }
        }
        return n == 1? cost : -1;
    }
    private void union(int x, int y){
        int px = find(x);
        int py = find(y);
        if(px != py){
            if(rank[px] > rank[py]){
                parent[px] = x;
            }else if (rank[px] == rank[py]){
                ++rank[px];
                parent[px] = py;
            }else{
                parent[px] = py;
            }
            --n;
        }
    }
    private int find(int x){
        int px = parent[x];
        while(x != px){
            x = px;
            px = parent[x];
        }
        return x;
    }
}
