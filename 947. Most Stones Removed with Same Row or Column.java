_____________________________________________________________________________My Solution__________________________________________________________________
class Solution {
    // improve compress the path
    // In another view：
    // A stone, connect a row index and col.
    Map<Integer, Integer> f = new HashMap<>();
    int islands = 0;

    public int removeStones(int[][] stones) {
        for (int i = 0; i < stones.length; ++i)
            union(stones[i][0], ~stones[i][1]);
        return stones.length - islands;
    }

    public int find(int x) {
        if (f.putIfAbsent(x, x) == null)
            islands++;
        if (x != f.get(x))
            f.put(x, find(f.get(x)));
        return f.get(x);
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            f.put(x, y);
            islands--;
        }
    }
}
_____________________________________________________________________________My Solution__________________________________________________________________
class Solution {
    // Union find with rank
    HashMap<Integer, Integer> parent = new HashMap();
    HashMap<Integer, Integer> rank = new HashMap();
    public int removeStones(int[][] stones) {
        for(int[] s : stones){
            int hash = s[0] * 10000 + s[1];
            parent.put(hash, hash);
            rank.put(hash, 0);
        }
        int ans = 0;
        for(int[] s1 : stones){
            int hash1 = s1[0] * 10000 + s1[1];
            int p1 = find(hash1);
            for(int[] s2 : stones){
                if(s2[0] != s1[0] && s2[1] != s1[1]){
                    continue;
                }
                int hash2 = s2[0] * 10000 + s2[1];
                int p2 = find(hash2);
                if(p1 != p2){
                    union(p1, p2);
                    ++ans;
                }
            }
        }
        
        return ans;
    }
    
    private void union(int p1, int p2){
        if(rank.get(p1) >= rank.get(p2)){
            parent.put(p2, p1);
        }else if(rank.get(p1) < rank.get(p2)){
            parent.put(p1, p2);
        }
    }
    
    private int find(int c){
        int p = parent.get(c);
        while(p != c){
            c = p;
            p = parent.get(c);
        }
        
        return p;
    }
}
