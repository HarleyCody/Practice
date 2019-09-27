class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int ans = 0;
        if(grid.length == 0 || grid[0].length == 0) return 0;
        int[] l2r= new int[grid.length];
        int[] t2b = new int[grid[0].length];
        
        // left to right skyline; get by rows
        for(int i = 0; i < grid.length; i++){
            l2r[i] = 0;
            for(int j = 0; j < grid[0].length; j++){
                l2r[i] = Math.max(l2r[i], grid[i][j]);
            }
        }
        // top to bottom syline; get by column
        for(int i = 0; i < grid[0].length; i++){
            t2b[i] = 0;
            for(int j = 0; j < grid.length; j++){
                // gird[j][i] not gird[i][j], comparing different value from difference rows;
                t2b[i] = Math.max(t2b[i], grid[j][i]);
            }
        }
        
        // increase height;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                int standard = Math.min(t2b[j], l2r[i]);// minimal reqirement for cur building;
                if(grid[i][j] < standard){
                    ans += standard - grid[i][j];
                }
            }
        }
        return ans;
    }
}
