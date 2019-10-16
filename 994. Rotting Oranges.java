___________________________________________________________Best Solution____________________________________________________________________
class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;
        for(int i = 0; i < grid.length; i ++){
            for(int j = 0; j < grid[i].length; j ++){
                if(grid[i][j] == 2){
                    q.add(new int[]{i, j});
                }
                else if(grid[i][j] == 1){
                    fresh ++;
                }
            }
        }

        if(fresh == 0){
            return 0;
        }

        int count = 0;

        while(!q.isEmpty()){

            count ++;
            int size = q.size();
            while(size > 0){
                size--;
                int[] cur = q.poll();
                int i = cur[0], j = cur[1];
                
    
                if(i - 1 >= 0 && grid[i - 1][j] == 1){
                    grid[i - 1][j] = 2;
                    q.add(new int[]{i - 1, j});
                    fresh --;
                }
    
                if(i + 1 < grid.length && grid[i + 1][j] == 1){
                    grid[i + 1][j] = 2;
                    q.add(new int[]{i + 1, j});
                    fresh --;
                }
    
                if(j - 1 >= 0 && grid[i][j - 1] == 1){
                    grid[i][j - 1] = 2;
                    q.add(new int[]{i, j - 1});
                    fresh --;
                }
    
                if(j + 1 < grid[i].length && grid[i][j + 1] == 1){
                    grid[i][j + 1] = 2;
                    q.add(new int[]{i, j + 1});
                    fresh --;
                }
            }
        }

        return fresh == 0? count - 1: -1;
    }
}
____________________________________________________________My Solution____________________________________________________________________
class Solution {
    int[][] dirs = new int[][]{{1,0}, {0,1}, {-1,0}, {0,-1}};
    public int orangesRotting(int[][] grid) {
        
        if(grid.length == 0 || grid[0].length == 0) return -1;
        // record orages in two category
        HashSet<Integer> rotten = new HashSet();
        HashSet<Integer> fresh = new HashSet();
        
        int len = grid[0].length;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 2){
                    rotten.add(i * len + j);
                }else if(grid[i][j] == 1){
                    fresh.add(i * len + j);
                }
            }
        }
        
        // start rotting, put fresh to rotten
        int times = 0;
        int preSize = fresh.size();
        while(!fresh.isEmpty()){
            times++;
            HashSet<Integer> newRotten = new HashSet();
            for(int idx : rotten){
                for(int i = 0; i < 4; i++){
                    int newX = idx / len + dirs[i][0];
                    int newY = idx % len + dirs[i][1];
                    if(0 <= newX && newX < grid.length && 0 <= newY && newY < grid[0].length){
                        int newIdx = newX * len + newY;
                        if(fresh.contains(newIdx)){
                            fresh.remove(newIdx);
                            newRotten.add(newIdx);
                        }
                    }
                }
            }
            // orange in the cornor cannot be rottened
            if(fresh.size() == preSize) return -1;
            // continue rotten
            rotten.clear();
            rotten.addAll(newRotten);
            preSize = fresh.size();
        }
        return times;
    }
}
