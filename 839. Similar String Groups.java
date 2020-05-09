____________________________________________________________Best Solution_______________________________________________________________
class Solution {
    // Choose the way to find by time complexity
    // plain UF time complexity(n^2 * w);
    // UF with hashMap(n * w^3) third w cause hashMap is O(w) to put a new record
    
    boolean areSimilar(String left, String right) {
        int mismatch = 0;
        int mismatchIndex = -1;
        
        if(left.length() != right.length())
            return false;
        
        for(int i = 0; i < left.length(); i++) {
            if(left.charAt(i) != right.charAt(i)) {
                if(++mismatch == 1) {
                    mismatchIndex = i;
                } else if(mismatch != 2 
                          || left.charAt(mismatchIndex) != right.charAt(i) 
                          || right.charAt(mismatchIndex) != left.charAt(i)) {
                    return false;
                }
            }
        }
        
        return true;
    }
   
    class UnionFind {
        private final int[] parent;
        
        public UnionFind(int n) {
            this.parent = new int[n];
            
            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int i) {
            return parent[i] == i ? i : (parent[i] = find(parent[i]));
        }
        
        public void union(int i, int j) {
            parent[find(i)] = find(j);
        }   
        
        public int groups() {
            int groups = 0;
            for(int i = 0; i < parent.length; i++) {
                if(parent[i] == i)
                    groups++;
            }
            return groups;
        }
        
        public boolean areConnected(int i, int j) {
            return find(i) == find(j);
        }
    }
    
    public int numSimilarGroupsTraverse(String[] A) {
        UnionFind union = new UnionFind(A.length);
        
        Map<String, Integer> indices = new HashMap<>();
        
        // group same string
        for(int i = 0; i < A.length; i++) {
            if(indices.containsKey(A[i])) {
                union.union(indices.get(A[i]), i);
            }
            indices.put(A[i], i);
        }
        
        for(int i = 0; i < A.length; i++) {
            char[] word = A[i].toCharArray();
            
            for(int j = 0; j < word.length; j++) {   
                for(int k = j + 1; k < word.length; k++) { 
                    char temp = word[j];
                    word[j] = word[k];
                    word[k] = temp;
                    
                    String neighbour = new String(word);
                    if(indices.containsKey(neighbour)) {
                        union.union(i, indices.get(neighbour));
                    }
                    
                    word[k] = word[j];
                    word[j] = temp;
                }    
            }
        }
        
        return union.groups();
    }
    
    
    public int numSimilarGroupsCompare(String[] A) {
        UnionFind union = new UnionFind(A.length);
        
        for(int i = 0; i < A.length; i++) {
  
            for(int j = i + 1; j < A.length; j++) {   
                if(!union.areConnected(i, j) && areSimilar(A[i], A[j]))
                    union.union(i, j);
            }
        }
        
        return union.groups();
    }
    
    public int numSimilarGroups(String[] A) {
        long w = A[0].length();
        int n = A.length;
        
        // plain UF time complexity(n^2 * w);
        // UF with hashMap(n * w^3) third w cause hashMap is O(w) to put a new record
        if(n * n * w < n * w * w * w) {
            return numSimilarGroupsCompare(A);
        }
        
        return numSimilarGroupsTraverse(A);
    }
}
______________________________________________________________My Solution_______________________________________________________________
class Solution {
    //isConnected: only two letters are different and they are pair
    //union find
    int[] parent, rank;
    int ans;
    public int numSimilarGroups(String[] A) {
        int len = A.length;
        ans = len;
        parent = new int[len];
        rank = new int[len];
        
        for(int i = 0; i < len; ++i){
            parent[i] = i;
        }
        for(int i = 0; i < len; ++i){
            for(int j = i + 1; j < len; ++j){
                if(isConnected(A, A[i], A[j]) && find(i) != find(j)){
                    union(i, j);
                }
            }
        }
        return ans;
    }
    private void union(int x, int y){
        int px = find(x);
        int py = find(y);
        if(rank[px] > rank[py]){
            parent[py] = px;
        }else if(rank[py] > rank[px]){
            parent[px] = py;
        }else{
            parent[py] = px;
            rank[px]++;
        }
        --ans;
    } 
    
    private int find(int x){
        int px = parent[x];
        while(px != parent[px]){
            px = parent[px];
        }
        return px;
    }
    private boolean isConnected(String[] words, String w1, String w2){
        if(w1.equals(w2)){
            return true;
        }
        int len = w1.length();
        int diff = -1, swap = -1;
        for(int i = 0; i < len; ++i){
            if(w1.charAt(i) == w2.charAt(i)){
                continue;
            }
            if(diff == -1){
                diff = i;
            }else if(w1.charAt(i) == w2.charAt(diff) && 
                     w2.charAt(i) == w1.charAt(diff) &&
                     swap == -1){
                    swap = i;
            }else{
                return false;
            }
        }
        return diff < swap;
    }
}
