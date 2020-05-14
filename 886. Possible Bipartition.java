____________________________________________________________Best Solution_______________________________________________________________
class Solution {
    // union found split two group
    // GroupA : all hate b, GroupB: all hate a; 
    public boolean possibleBipartition(int N, int[][] dislikes) {
        int[] group = new int[N+1];
        // group with self
        for (int i = 0; i <= N; i++) {
            group[i] = i; // initial
        }
        
        for (int[] dislike : dislikes) {
            int a = dislike[0];
            int b = dislike[1];
            
            if (group[a] == a && group[b] == b) {
                group[a] = b;
                group[b] = a;
            } else if (group[a] == a && group[b] != b) {
                // let a go to group that b hates;
                group[a] = group[group[b]];
            } else if (group[b] ==b && group[a] != a) {
                // group[b] to group that a hates;
                group[b] = group[group[a]];
            } else if (group[b] == group[a]) {
                // a b hates same group a b can not bipartition
                return false;
            }
        }
        return true;        
    }
}
_____________________________________________________________My Solution________________________________________________________________
// build graph
// dfs color graph with two colors
class Solution {
    int[] color;
    HashSet<Integer>[] graph;
    public boolean possibleBipartition(int N, int[][] dislikes) {
        color = new int[N + 1];
        graph = new HashSet[N + 1];
        for(int[] d : dislikes){
            if(graph[d[0]] == null){
                graph[d[0]] = new HashSet();
            }
            graph[d[0]].add(d[1]);
            
            if(graph[d[1]] == null){
                graph[d[1]] = new HashSet();
            }
            graph[d[1]].add(d[0]);
        }
        
        //dfs color graph;
        for(int i = 0; i < N; ++i){
            if(color[i] != 0){
                continue;
            }
            if(!dfs(i, 1)){
                return false;
            }
        }
        return true;
    }
    private boolean dfs(int idx, int c){
        color[idx] = c;
        if(graph[idx] == null){
            return true;
        }
        for(int n : graph[idx]){
            if(color[n] == c || (color[n] == 0 && !dfs(n, -c))){
                return false;
            }
        }
        return true;
    }
}
