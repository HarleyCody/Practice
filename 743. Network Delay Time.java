_____________________________________________________________________________Djistra Solution(Best)____________________________________________________________________________
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
		// use a 2D array to represent the graph and initilize it with max values
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.MAX_VALUE;
            }
        }
		// Vertices in this problem start with 1
        for (int i = 0; i < times.length; i++) {
            graph[times[i][0] - 1][times[i][1] - 1] = times[i][2];
        }
        int[] dist = new int[N];
        boolean[] visited = new boolean[N];
        visited[K - 1] = true;
        for (int i = 0; i < N; i++) {
            dist[i] = graph[K-1][i];
        }
        dist[K - 1] = 0;
        
        // for all vertices N - 1
        // make sure every node will be checked,
        // after N times, dist[] should be completed updated
        for (int i = 0; i < N - 1; i++) {
            int min = Integer.MAX_VALUE;
            int pos = -1;
            //find minimal adjascent of every node;
            //min = graph[K][pos]
            for (int j = 0; j < N; j++) {
                if (!visited[j] && dist[j] < min) {
                    min = dist[j];
                    pos = j;
                }
            }
            if (pos == -1) break;
            visited[pos] = true;
            
            for (int k = 0; k < N; k++) {
			// check if it is adjacent
                // update dist[k] if K can go to pos and then go to k;
                if (graph[pos][k] != Integer.MAX_VALUE) {
                    if (dist[k] > dist[pos] + graph[pos][k]) {
                        dist[k] = dist[pos] + graph[pos][k];
                    }
                }
            }
        }
        int res = 0;
        for(int i = 0; i < N ; i++) {
            res = Math.max(res, dist[i]);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
}
_____________________________________________________________________________Djistra Solution(heap + bfs)____________________________________________________________________________
class Solution {
    //BFS, to get min dist from k to the rest ndoes
    //using pq instead of q, dist is using for sort;
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] edge: times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>(
                (info1, info2) -> info1[0] - info2[0]);
        heap.offer(new int[]{0, K});

        Map<Integer, Integer> dist = new HashMap();

        while (!heap.isEmpty()) {
            int[] info = heap.poll();
            int d = info[0], node = info[1];
            if (dist.containsKey(node)) continue;
            dist.put(node, d);
            if (graph.containsKey(node))
                for (int[] edge: graph.get(node)) {
                    int nei = edge[0], d2 = edge[1];
                    if (!dist.containsKey(nei))
                        heap.offer(new int[]{d+d2, nei});
                }
        }

        if (dist.size() != N) return -1;
        int ans = 0;
        for (int cand: dist.values())
            ans = Math.max(ans, cand);
        return ans;
    }
}
______________________________________________________________________________General Solution____________________________________________________________________________
class Solution {
    // dfs to update minimal dist, get max among all divergents as ans
    int[][] graph;
    int[] dist;
    int n;
    public int networkDelayTime(int[][] times, int N, int K) {
        n = N + 1;
        graph = new int[n][n];
        dist = new int[n];
        
        for(int i = 0; i < n; ++i){
            dist[i] = Integer.MAX_VALUE;
            Arrays.fill(graph[i], -1);
        }
        
        for (int[] edge: times) {
            graph[edge[0]][edge[1]] = edge[2];
        }
        
        dfs(graph, K, 0);
        int ans = 0;
        dist[0] = 0;
        for (int cand: dist) {
            if (cand == Integer.MAX_VALUE) return -1;
            ans = Math.max(ans, cand);
        }
        return ans;
    }
    
    // go through all map to record min time from k to node
    public void dfs(int[][] graph, int node, int elapsed) {
        if (elapsed >= dist[node]) return;
        dist[node] = elapsed;
        for (int i = 0; i < n; ++i){
            if(graph[node][i] >= 0){
                dfs(graph, i, elapsed + graph[node][i]);
            }
        }
    }
}
