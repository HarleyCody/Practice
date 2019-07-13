____________________________________________________________________Best Solution__________________________________________________________
class Solution {
    
    // dijistra
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] dist = new int[n];
        Arrays.fill(dist, 2000000);
        dist[src] = 0;
        // K + 1 times for k stop
        for(int count = 0; count < K+1; ++count) {
            updateDist(dist, flights);
        }
        
        return dist[dst] == 2000000 ? -1 : dist[dst];
    }
    // renew distance Array and only stroes minimal value;
    void updateDist(int[] dist, int[][] flights) {
        int[] originalDist = new int[dist.length];
        System.arraycopy(dist, 0, originalDist, 0, dist.length);
        
        for(int f = 0; f < flights.length; ++f) {
            int mid = flights[f][0];
            int dest = flights[f][1];
            int cost = flights[f][2];       
            
            if (originalDist[mid] + cost < dist[dest]) {
                dist[dest] = originalDist[mid] + cost;
            }
        }
    }
}
______________________________________________________________________My Solution__________________________________________________________
class Solution {
    // DFS, search next place and add costs, if current cost + next Stop costs > ans; then do not search
    private int ans = Integer.MAX_VALUE;
    private int[][] graph;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        
        graph = new int[n][n];
        int[]visited = new int[n];
        for(int i = 0; i < flights.length; ++i){
            graph[flights[i][0]][flights[i][1]] = flights[i][2];
        }
        dfs(graph, visited, src, dst, 0, K);
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    // search next stop in adj set, if it is valid(not visited && costs + current to next stop costs is smaller than ans)
    private void dfs(int[][] graph, int visited[], int cur, int end, int costs, int K){
        if(visited[cur] == 1 && visited[cur] == -1) return;
        if(cur == end && -1 <= K){
            ans = costs;
            return;
        }
        if( -1 > K) {
            return;
        }
        
        visited[cur] = 1;
        //if current cost + next Stop costs > ans; then do not search, only search possible minimal value
        for(int i = 0; i < graph.length; ++i){
            if(graph[cur][i] != 0 && visited[i] == 0 && costs + graph[cur][i] <= ans){// prunning, be faster
                costs += graph[cur][i];
                dfs(graph, visited, i, end, costs, K - 1);
                costs -= graph[cur][i];
            }
        }
        visited[cur] = 0;
    }
}
