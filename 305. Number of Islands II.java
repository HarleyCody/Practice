class Solution {
    // Union: choose smallest idx as root;
    // find recursivly find root of land;
    // current island number == prev Num - # of lands are connected by current pos
    // (PS: should skip node that rebuild a land that has been built);
    // how many land is connected by current pos => 
    //      num == -1, new land is built: cur = prev + 1; 
    //      num == 0, land is claimed by a island, number keeps unchanged: cur = prev;
    //      num > 0, num + 1 island are connected, num islands disappear cur = prev - num;
    //      conclusivly, cur = prev - num;
    int R, C;
    int[] root;
    int[][] map;
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        R = m; C = n;
        root = new int[R * C];
        map = new int[R][C];
        int prev = -1;
        List<Integer> ans = new ArrayList();
        for(int[] pos : positions){
            int num = 0;
            if(map[pos[0]][pos[1]] != 1){
                int idx = pos[0] * C + pos[1];
                root[idx] = idx;
                num = buildLand(pos);
            } 
            if(prev == -1){
                ans.add(1);
                prev = 1;
            }else{
                prev -= num;
                ans.add(prev);
            }
        }
        
        return ans;
    }
    
    private int buildLand(int[] pos){
        int ans = 1;
        int x = pos[0];
        int y = pos[1];
        map[x][y] = 1;
        int idx = x * C + y;
        if(1 <= x && map[x - 1][y] == 1){
            if(union((x - 1) * C + y, idx)){
                ++ans;
            }
        }
        if(x < R - 1 && map[x + 1][y] == 1){
            if(union((x + 1) * C + y, idx)){
                ++ans;
            }
        }
        
        if(1 <= y && map[x][y - 1] == 1){
            if(union(x * C + y - 1, idx)){
                ++ans;
            }
        }
        if(y < C - 1 && map[x][y + 1] == 1){
            if(union(x * C + y + 1, idx)){
                ++ans;
            }
        }
        
        return ans - 2;
    }
    
    private boolean union(int x, int y){
        int px = find(x);
        int py = find(y);
        if(px == py){
            return false;
        }
        if(px < py){
            root[py] = px;
        }else{
            root[px] = py;
        }
        return true;
    }
    private int find(int x){
        int px = root[x];
        while(px != x){
            x = px;
            px = root[x];
        }
        return x;
    }
}
