________________________________________________________________________DFS similar to Tarjan__________________________________________________________________
// find max depth by going through every node
// go to the bottom recursivly go back with adding layer one by one, update max
class Solution {
    List<Integer>[] g;
    int[] depth;
    int[] visited;  /* visited -> 0: not visited yet, 1: visiting, 2: visited already */
    int max;
    public int minimumSemesters(int N, int[][] relations) {
        g = new List[N];
        for (int i = 0; i < N; i++) g[i] = new ArrayList<>();
        depth = new int[N];
        visited = new int[N];
        for (int[] relation : relations) {
            int a = relation[0] - 1;
            int b = relation[1] - 1;
            g[a].add(b);
        }
        max = 0;
        for (int i = 0; i < N; i++) {
            if (visited[i] == 0) {
                if (!dfs(i)) {
                    return -1;
                }
            }
        }
        return max;
    }
    
    private boolean dfs(int u) {
        visited[u] = 1;
        for (int v : g[u]) {
            if (visited[v] == 0) {
                if (!dfs(v)) {
                    return false;
                }
            } else if (visited[v] == 1) {
                return false;
            }
            depth[u] = Math.max(depth[u], depth[v]);
        }
        ++depth[u];
        max = Math.max(max, depth[u]);
        visited[u] = 2;
        return true;
    }
}
__________________________________________________________________________________Best BFS______________________________________________________________________
class Solution {
    public int minimumSemesters(int N, int[][] relations) {
        //improve update modifed courses faster, use int to record the number of req course
        Map<Integer, List<Integer>> g = new HashMap<>(); // key: prerequisite, value: course list. 
        int[] inDegree = new int[N + 1]; // inDegree[i]: number of prerequisites for i.
        for (int[] r : relations) {
            g.computeIfAbsent(r[0], l -> new ArrayList<>()).add(r[1]);
            ++inDegree[r[1]];
        }
        
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; ++i){
            if (inDegree[i] == 0){
                q.offer(i);
            }
        }

        int semester = 0;
        while (!q.isEmpty()) {
            for (int s = q.size(); s > 0; --s) {
                int c = q.poll();
                --N;
                if (!g.containsKey(c)) continue;
                for (int course : g.remove(c)){
                    if (--inDegree[course] == 0){
                        q.offer(course);
                    }
                }          
            }
            ++semester; // need one more semester.
        }
        return N == 0 ? semester : -1;
    }
}
____________________________________________________________________________________My BFS______________________________________________________________________
// study as much as possible in one semaster
class Solution {
    public int minimumSemesters(int N, int[][] relations) {
        HashSet<Integer>[] req = new HashSet[N + 1];
        for(int[] r : relations){
            if(req[r[1]] == null){
                req[r[1]] = new HashSet<Integer>();
            }
            req[r[1]].add(r[0]);
        }
        
        int done = 0;
        Queue<Integer> q = new LinkedList();
        for(int i = 1; i <= N; ++i){
            if(req[i] == null){
                q.offer(i);
                ++done;
            }
        }
        
        int sem = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int s = 0; s < size; ++s){
                int cur = q.poll();
                for(int i = 1; i <= N; ++i){
                    if(req[i] == null || !req[i].remove(cur)){
                        continue;
                    }
                    if(req[i].size() == 0){
                        q.offer(i);
                        ++done;
                    }
                }
            }
            ++sem;
        }
        
        return N == done ? sem : -1;
    }
}
