___________________________________________________________________________Best Solution___________________________________________________________________________
class Solution {
    // dfs + mem
    // initialize map and build map in seperate for loop
    int[] mem;
    List<Integer>[] map;
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        mem = new int[n];
        map = new ArrayList[n];
        for(int i = 0; i < n; ++i){
            map[i] = new ArrayList();
        }
        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            map[from].add(to);
        }
        if(map[destination].size()>0){
            
        }
        return canReach(source, destination);
    }
    
    private boolean canReach(int from, int dst){
        if(map[from].size() == 0){
            return from == dst;
        }
        
        mem[from] = -1;

        for(int i = 0; i < map[from].size(); ++i){
            int next = map[from].get(i);
           if(mem[next] == -1 || !canReach(next, dst)){
               return false;
           }
        }
        mem[from] = 1;
        return true;
    }
}
___________________________________________________________________________My Solution___________________________________________________________________________

class Solution {
    // dfs + mem
    int[] mem;
    List<Integer>[] map;
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        mem = new int[n];
        map = new ArrayList[n];
        
        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            if(map[from] == null){
                map[from] = new ArrayList();
            }
            map[from].add(to);
        }
        if(map[source] == null){
            return destination == source;
        }
        if(map[destination] != null){
            return false;
        }
        int len = map[source].size();
        for(int i = 0; i < len; ++i){
            if(!canReach(map[source].get(i), destination)){
                //System.out.println("cannot reach " + map[source].get(i));
                return false;
            }
        }
        
        return true;
    }
    
    private boolean canReach(int from, int dst){
        if(from == dst){
            return true;
        }
        
        if(mem[from] == -1 || map[from] == null){
            return false;
        }
        if(mem[from] == 1){
            return true;
        }
        
        mem[from] = -1;
        boolean ans = false;
        
        for(int i = 0; i < map[from].size(); ++i){
           if(!canReach(map[from].get(i), dst)){
               ans = false;
               break;
           }else{
               ans = true;
           }
        }
        mem[from] = ans ? 1 : -1;
        
        return ans;
    }
}
