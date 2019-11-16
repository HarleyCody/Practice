// Critical Point
// Theory:https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
______________________________________________Critical Point O(V+E)___________________________________________________________
class Solution {
    HashMap<Integer, HashSet<Integer>> cons = new HashMap();
    HashSet<Integer> ans;
    int time = 0;
    // low record earliest visited vertex that can be reached from subtree rooted with cur.
    // ids record discovery time and isVisited. -1 : unvisited
    int[] low, parent, ids;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ans = new HashSet();
        for(int i = 0 ; i < n; i++){
            cons.put(i, new HashSet<Integer>());
        }
        for(List<Integer> list : connections){
            int first = list.get(0);
            int second = list.get(1);
            cons.get(first).add(second);
            cons.get(second).add(first);
        }
        low = new int[n];
	    ids = new int[n];
	    parent = new int[n]; 
	    Arrays.fill(ids, -1);
	    Arrays.fill(parent, -1);
        for(int i = 0; i < n; i++){
            if(ids[i] == -1)
                dfs(cons, i);
        }
        System.out.print(low[3]);
        System.out.print(ans);
        return new ArrayList();
    }
    private void dfs(HashMap<Integer, HashSet<Integer>> cons, int cur){
        int child = 0;
        ids[cur] = low[cur] = time++;
        for(int nei : cons.get(cur)){
            if(ids[nei] == -1){
                // if child == 2 means cur connect two independant graph. cur is an junction
                //so 0 is excluded from ansList
                child++;
                parent[nei] = cur;
                dfs(cons, nei);
                low[cur] = Math.min(low[nei],low[cur]);
                // in second scenario, low[nei] >= ids[cur] means nei is discovered after cur
                if(parent[cur] == -1 && child > 1 || parent[cur] != -1 && low[nei] >= ids[cur]){
                    ans.add(cur);
                }
            }else if(nei != parent[cur]){
                // update time as its undirect so nei can go to cur as well.
                low[cur] = Math.min(low[cur], ids[nei]);
            }
        }
    }
}
