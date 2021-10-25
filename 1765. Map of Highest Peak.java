//My Solution: BFS flood land to build height
class Solution {
    public int[][] highestPeak(int[][] isWater) {
        Queue<int[]> lower = new LinkedList();
        int[] dirs = new int[]{-1, 0, 1, 0,-1};
        int R = isWater.length;
        int C = isWater[0].length;
        
        for(int r = 0; r < R; ++r){
            for(int c = 0; c < C; ++c){
                if(isWater[r][c] == 1){
                    lower.offer(new int[]{r, c});
                    isWater[r][c] = 0;
                }else{
                    isWater[r][c] = -1;
                }
            }
        }
        int height = 1;
        while(!lower.isEmpty()){
            int size = lower.size();
            while(size-- > 0){
                int[] cur = lower.poll();
        
                for(int i = 0 ; i < 4; ++i){
                    int nx = cur[0] + dirs[i];
                    int ny = cur[1] + dirs[i + 1];
                    if(nx < 0 || nx == R || ny < 0 || ny == C || isWater[nx][ny] != -1){
                        continue;
                    }
                    lower.offer(new int[]{nx, ny});
                    isWater[nx][ny] = height;                    
                }
            }
            ++height;
        }
        return isWater;
    }
}
