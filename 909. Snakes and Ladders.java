class Solution {
    public int snakesAndLadders(int[][] board) {
        boolean flag = true;
        int n = board.length;
        int[] recorder = new int[n * n + 1];
        int x = 1;
        // transfer 2d to 1d;
        for(int i = n - 1; 0 <= i; --i){
            if(flag){
                for(int j = 0; j < n; ++j){
                    recorder[x++] = board[i][j];
                }
            }else{
                for(int j = n - 1; 0 <= j; --j){
                    recorder[x++] = board[i][j];
                }
            }
            flag = !flag;
        }
        // count records the value is not visited and the steps to this value
        int [] count = new int[n * n + 1];
        // all are not visited
        Arrays.fill(count, -1);
        // bfs
        Queue<Integer> points = new LinkedList<>();
        // start
        points.offer(1);
        count[1] = 0;
        while(!points.isEmpty()){
            int cur = points.poll();
            for(int i = 1; i < 7; ++i){
                int next = cur + i;
                // cannot reach
                if(next > n * n) break;
                // ladder or snake, go to next point, next is location(not -1, but ID of cell)
                if(recorder[next] > -1){
                    next = recorder[next];
                }
                // reach, previous steps + 1
                if( next == n * n){
                    return count[cur] + 1;
                }
                // if next value is not visited, add it to queue(prevent circle)
                if(count[next] == -1){
                    points.offer(next);
                    count[next] = count[cur] + 1;
                } 
            }
        }
        return -1;
    }
}
