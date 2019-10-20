_______________________________________________________Best Solution(tle)_______________________________________________________________
public class Solution {
    static int[] connectedCities(int n, int g, int[] og, int[] dst) {
        int[] parent = new int[n + 1];
        int[] rank = new int[n + 1];
        for(int i = 0; i <= n; i++){
            parent[i] = i;
        }
        // only choose gcd > g to union
        for(int i = g + 1; i <= n; i++){
            for(int j = 2 * i; j <= n; j += i){
                union(j, i, parent, rank);
            }
        }
        int[] ans = new int[og.length];
        for(int i = 0; i < og.length; i++){
            if(find(og[i], parent) == find(dst[i], parent)){
                ans[i] = 1;
            }else{
                ans[i] = 0;
            }
        }
        return ans;
        // Complete this function
    }
    private static int find(int i, int[] parent){
        while(i != parent[i]){
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }
    
    private static void union(int I, int J, int[] p, int[] r){
        int i = find(I, p);
        int j = find(J, p);
        if(r[i] == r[j]){
            r[i] += r[j];
            p[j] = i;
        }else if(r[i] > r[j]){
            p[j] = i;
        }else{
            p[i] = j;
        }
    }
}
_______________________________________________________My Solution(tle)_________________________________________________________________
public class Solution {
    static class Group{
        static int parent[];
        static int rank[];
        
        public Group(int n){
            parent = new int[n + 1];
            rank = new int[n + 1];
            
            for(int i = 0; i < parent.length; i++){
                parent[i] = i;
            }
        }
        public void union(int i, int j){
            int I = find(i);
            int J = find(j);
            if(I == J) return;
            if(rank[I] > rank[J]){
                parent[J] = I;
            }else if(rank[I] == rank[J]){
                parent[J] = I; 
                rank[I] += rank[J];
            }else{
                parent[I] = J;
            }
        }
        
        public int find(int i){
            if(i != parent[i]){
                return find(parent[i]);
            }
            return i;
        }
    }
    static HashMap<Integer, HashSet<Integer>> recorder = new HashMap();
    static int[] connectedCities(int n, int g, int[] og, int[] dst) {
        for(int i = g + 1; i <= n; i++){
            HashSet<Integer> adjs = new HashSet();
            for(int j = g + 1; j <= n; j++){
                if(j == i) continue;
                if(gcd(i,j) > g){
                    adjs.add(j);
                }
                recorder.put(i, adjs);
            }
        }
        Group uf = new Group(n);
        for(int i = g + 1; i <= n; i++){
            HashSet<Integer> adjs = recorder.get(i);
            if(adjs == null)continue;
            for(int adj : adjs){
                uf.union(i, adj);
            }
        }
        int[] ans = new int[og.length];
        for(int i = 0; i < og.length; i++){
            if(uf.find(og[i]) == uf.find(dst[i])){
                ans[i] = 1;
            }else{
                ans[i] = 0;
            }
        }
        return ans;
        // Complete this function
    }
    private static int gcd(int i, int j){
        if(j == 0)
            return i;
        return gcd(j, i % j);
    }
}
