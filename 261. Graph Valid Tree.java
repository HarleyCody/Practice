//Best Solution: Union Find As there only be one common root, so when union, donot need to choose larger number as parent 
class Solution {
    int[] parent;
    public boolean validTree(int n, int[][] edges) {
        parent = new int[n];
        for(int i = 0; i < n; ++i){
            parent[i] = i;
        }
        if(edges.length != n - 1) return false;
        
        for(int[] edge : edges){
            if(!union(edge[0], edge[1])) return false;
        }
        return true;
    }
    
    private boolean union(int x, int y){
        int px = find(x);
        int py = find(y);
        if(px == py) return false;
        parent[px] = py; 
        return true;
    }
    
    private int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
}
//My Solution: Union found connect edge and choose larger number as parent
class Solution {
    int[] parent;
    public boolean validTree(int n, int[][] edges) {
        parent = new int[n];
        for(int i = 0; i < n; ++i){
            parent[i] = i;
        }
        
        for(int[] edge : edges){
            if(!union(edge[0], edge[1])) return false;
        }
        
        for(int i = 1; i < n; ++i){
            if(find(i) != find(i - 1)) return false;
        }
        return true;
    }
    
    private boolean union(int x, int y){
        int px = find(x);
        int py = find(y);
        if(px == py) return false;
        if(px <= py){
            parent[px] = py; 
        }else{
            parent[py] = px;
        }
        return true;
    }
    
    private int find(int x){
        if(x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
}

//My Solution: DFS to defect circle
class Solution {
    List<Integer>[] graph;
    boolean[] visited;
    int N = 0;
    public boolean validTree(int n, int[][] edges) {
        if(n == 1) return true;
        graph = new ArrayList[n];
        visited = new boolean[n];
        for(int i = 0; i < n; ++i){
            graph[i] = new ArrayList<Integer>();
        }
        
        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            graph[from].add(to);
            graph[to].add(from);
        }
        
        return isTree(-1, 0) && N == n;
    }
    
    private boolean isTree(int prev, int cur){
        if(visited[cur]) return false;
        
        ++N;
        visited[cur] = true;
        for(int i : graph[cur]){
            if(i == cur || i == prev) continue;
            if(!isTree(cur, i)) return false;
        }
        
        return true;
    }
}

//My Solution: BFS to defect circle
class Solution {
    List<Integer>[] graph;
    boolean[] visited;
    public boolean validTree(int n, int[][] edges) {
        if(n == 1) return true;
        graph = new ArrayList[n];
        visited = new boolean[n];
        for(int i = 0; i < n; ++i){
            graph[i] = new ArrayList<Integer>();
        }
        
        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            graph[from].add(to);
            graph[to].add(from);
        }
        int num = 0;
        Queue<int[]> q = new LinkedList<int[]>();
        q.offer(new int[]{-1, 0});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            ++num;
            visited[cur[1]] = true;
            for(int i : graph[cur[1]]){
                if(i == cur[0]) continue;
                if(visited[i]) return false;
                q.offer(new int[]{cur[1], i});
            }
        }
        return num == n;
    }
}
