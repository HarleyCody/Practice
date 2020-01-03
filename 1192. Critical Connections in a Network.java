___________________________________________________Best Solution(Tarjan with array)____________________________________________
class Solution {
    // go one path find cycle update low, record if low > dis as critical;
    // disc is timestamp that arrive at node i;
    // low record minimal arriving time that in a circle
    // if low[next] > node[cur] next can not reach to head, this connection is critical
    int edgeIndex = 0, time = -1;
    int[] to, next, head, low, disc;
    List<List<Integer>> res = new ArrayList<>();
    private void addEdge(int u, int v) {
        // record node that cur edge can arrive;
        to[edgeIndex] = v;
        // record another start point of u;
        next[edgeIndex] = head[u];
        // first road of node u; the last constructed; (from tail to start);
        head[u] = edgeIndex++;
    }
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        low = new int[n];
        disc = new int[n];
        int m = connections.size();
        to = new int[m * 2];
        head = new int[n];
        next = new int[m * 2];
        Arrays.fill(head, -1);
        Arrays.fill(next, -1);
        Arrays.fill(low, -1);
        Arrays.fill(disc, -1);
        // initialize Array to
        for (List<Integer> edge : connections) {
            int u = edge.get(0);
            int v = edge.get(1); 
            addEdge(u, v);
            addEdge(v, u);
        }
        dfs(1, -1);
        return res;
    }
    private void dfs(int node, int parent) {
        if (disc[node] != -1) {
            return;
        }
        low[node] = disc[node] = ++time;
        for (int edge = head[node]; edge != -1; edge = next[edge]) {
            int next = to[edge];
            // unvisited
            if (disc[next] == -1) {
                // using backtrack to update low for node;
                dfs(next, node);
                // update low from next node and cur node;
                low[node] = Math.min(low[node], low[next]);
                if (low[next] > disc[node]) {
                        res.add(Arrays.asList(node, next));
                }
            } else if (next != parent) {
                // end of circle; update low from here
                low[node] = Math.min(low[node], disc[next]);
            }
        }
    }
}
______________________________________General Solution(Tarjan with list or hashmap to record adj)______________________________
class Solution {
    // go one path find cycle update low, record if low > dis as critical;
    int[] low;
    int[] disc;
    boolean[] visited;
    ArrayList<Integer>[] adjs;
    int time = 0;
    List<List<Integer>> ans = new ArrayList();
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        low = new int[n];
        disc = new int[n];
        visited = new boolean[n];
        adjs = new ArrayList[n];
        for(int i = 0; i < n; i++){
            adjs[i] = new ArrayList();
        }
        for(List<Integer> con : connections){
            int first = con.get(0);
            int second = con.get(1);
            adjs[first].add(second);
            adjs[second].add(first);
        }
        dfs(0, -1);
        return ans;
    }
    private void dfs(int cur, int parent){
        visited[cur] = true;
        disc[cur] = low[cur] = time++;
        for(int nei : adjs[cur]){
            if(nei == parent) continue;
            if(!visited[nei]){
                dfs(nei,cur);
                low[cur] = Math.min(low[nei], low[cur]);
                if(disc[cur] < low[nei]){
                    ans.add(Arrays.asList(cur, nei));
                }
            }else{
                low[cur] = Math.min(low[cur], disc[nei]);
            }
        }
    }
}
