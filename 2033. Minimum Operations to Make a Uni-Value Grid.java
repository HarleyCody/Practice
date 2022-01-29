
//Best Solution: Bucket sort to find the middle number
class Solution {
    int m ;
    int n ;
    public int minOperations(int[][] grid, int x) {
        long sum = 0;
        m = grid.length;
        n = grid[0].length;
        int size = m * n;
        
        if (size == 1) {
            return 0;
        }
        
        List<Integer> list = new ArrayList<>();
        
        
        int[] hist = new int[10001];
        
        int rem = grid[0][0] % x;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((grid[i][j] % x) != rem) return -1;
                hist[grid[i][j]]++;
            }
        }
        
        int count = 0;
        int index = 0;
        
        int middleSize = (size +1) / 2;
        while (count < middleSize) {
            count += hist[++index];
        }
                
        return op(grid, index, x);
    }
    
    int op (int[][] grid, int medium, int x) {
        int op = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int dif = Math.abs(grid[i][j] - medium);
                if (dif % x != 0) return -1;
                op += (dif / x);
            }
        }
        return op;
    }
}
//My Solution: find the middle number and let every other number change to it
class Solution{
	public int minOperations(int[][] grid, int x){
		int row = grid.length;
		int col = grid[0].length;
		PriorityQueue<Integer> pq = new PriorityQueue();
		int size = (row * col + 1) / 2;
		for(int r = 0; r < row; ++r){
            for(int c = 0; c < col; ++c){
                pq.offer(grid[r][c]);
                if(pq.size() > size){
                    pq.poll();
                }
            }
        }
        int mid = pq.poll();
        int diff = 0;
        int ans = 0;
        for(int r = 0; r < row; ++r){
            for(int c = 0; c < col; ++c){
                diff = Math.abs(grid[r][c] - mid);
                if(diff % x != 0) return -1;
                ans += diff / x;
            }
        }

        return ans;
    }
}
