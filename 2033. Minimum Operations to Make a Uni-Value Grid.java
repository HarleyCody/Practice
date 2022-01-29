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
