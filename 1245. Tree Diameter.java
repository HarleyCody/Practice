class Solution {
    // build graph and find path from bottom to cur
    // only store max1st and max2nd, update ans;
    int ans = 0;
    List<Integer>[] map;
    public int treeDiameter(int[][] edges) {
        int len = edges.length;
        map = new ArrayList[len + 1];
        
        for(int[] edge : edges){
            if(map[edge[0]] == null){
                map[edge[0]] = new ArrayList();
            }
            if(map[edge[1]] == null){
                map[edge[1]] = new ArrayList();
            }
            
            map[edge[0]].add(edge[1]);
            map[edge[1]].add(edge[0]);
        }
        getDiameter(0, -1);
        
        return ans;
    }
    private int getDiameter(int cur, int prev){
        int max1st = 0, max2nd = 0;
        for(int i : map[cur]){
            if(i == prev){
                continue;
            }
            int max = getDiameter(i, cur);
            if(max > max1st){
                max2nd = max1st;
                max1st = max;
            }else if(max > max2nd){
                max2nd = max;
            }
            
            int curLen = max1st + max2nd;
            ans = Math.max(curLen, ans);
        }
        return 1 + max1st;
    }
}
