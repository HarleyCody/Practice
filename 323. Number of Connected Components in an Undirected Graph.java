_________________________________________________________Best Solution(Union Find)______________________________________________
class Solution {
    // use variable n instead of set to record group number
    // if union is successful --n, if union is unsuccessful(is unioned before) n is unchanged; 
    int[] parent, rank;
    public int countComponents(int n, int[][] edges) {
        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; ++i){
            parent[i] = i;
        }
        for(int[] edge : edges){
            if(union(edge[0], edge[1]))--n;
        }
        return n;
    }
    private boolean union(int x, int y){
        int px = find(x);
        int py = find(y);
        if(px == py) return false;
        if(rank[px] > rank[py]){
            parent[py] = px;
        }else if (rank[px] < rank[py]){
            parent[px] = py;
        }else{
            parent[py] = px;
            ++rank[px]; 
        }
        return true;
    }
    private int find(int x){
        int og = x;
        while(x != parent[x]){
            x = parent[x];
        }
        parent[og] = x;
        return x;
    }
}
__________________________________________________________My Solution(Union Find)______________________________________________
class Solution {
    int[] parent, rank;
    HashSet<Integer> recorder = new HashSet();
    public int countComponents(int n, int[][] edges) {
        parent = new int[n];
        rank = new int[n];
        for(int i = 0; i < n; ++i){
            parent[i] = i;
        }
        for(int[] edge : edges){
            union(edge[0], edge[1]);
        }
        for(int i = 0; i < n; ++i){
            recorder.add(find(i));
        }
        return recorder.size();
    }
    private void union(int x, int y){
        int px = find(x);
        int py = find(y);
        if(px != py){
            if(rank[px] > rank[py]){
                parent[py] = px;
            }else if (rank[px] < rank[py]){
                parent[px] = py;
            }else{
                parent[py] = px;
                ++rank[px]; 
            }
        }
    }
    private int find(int x){
        int og = x;
        while(x != parent[x]){
            x = parent[x];
        }
        parent[og] = x;
        return x;
    }
}
