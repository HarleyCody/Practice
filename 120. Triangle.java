__________________________________________________________Best Solution DP_____________________________________________________________
class Solution {
    
    // from top to bottom
    public int minimumTotal(List<List<Integer>> triangle) {
		if (triangle.size() == 0)
			return 0;
		if (triangle.size() == 1)
			return triangle.get(0).get(0);

		int[] dp = new int[triangle.size()];
		dp[0] = triangle.get(0).get(0);
		return minimumTotal(triangle, dp, 1);
	}

    // update current layer
	public int minimumTotal(List<List<Integer>> triangle, int[] dp, int lvlidx) {
		/**
		 * dp: dp[i]_lvlidx = the min path sum up to current level and up to
		 * index i
		 * 
		 * dp[0]_lvlidx = this_level_list[0] + dp[0]_(lvlidx-1);
		 * dp[end]_lvlidx = this_level_list[end] + dp[end-1]_(lvlidx-1);
		 * 
		 * dp[i]_lvlidx = this_level_list[i] + min{ dp[i-1]_(lvlidx-1),
		 * dp[i]_(lvlidx-1) };
		 */
        
        // trick level idx == size of curList;
		List<Integer> list = triangle.get(lvlidx);
		int pre = dp[0], temp;
		dp[0] += list.get(0);
		for (int i = 1; i < lvlidx; i++) {
            // dp record min in last layer;
			temp = dp[i];
            // only can shift to left or right in next level, pre   
			dp[i] = list.get(i) + Math.min(pre, dp[i]);
            // i move to i + 1, i in last layer will be prev, it was stored by prev
			pre = temp;
		}
		dp[lvlidx] = pre + list.get(lvlidx);

		if (lvlidx + 1 == triangle.size()) {
			int res = dp[0];
			for (int i = 1; i <= lvlidx; i++)
				res = Math.min(res, dp[i]);
			return res;
		}

		return minimumTotal(triangle, dp, lvlidx + 1);
	}
}
__________________________________________________________Best Solution________________________________________________________________
class Solution {
    // from bottom to top converge branch by min
    //S3: Recursion with 记忆化存储 to prune
    public int minimumTotal(List<List<Integer>> triangle) {
        int length = triangle.size();
        int[][] minPath = new int[length][length];
        return divideConquer(0, 0, minPath, triangle);
    }
    private int divideConquer(int row, int col, int[][] minPath, List<List<Integer>> triangle) {
        // base case
        if (row == triangle.size() - 1) {
            return triangle.get(row).get(col);
        }
        // if we already have the minimum path sum from (i,j) to bottom, pruning & return
        int left = (minPath[row + 1][col] == 0 ? 
                    divideConquer(row + 1, col, minPath, triangle) : minPath[row + 1][col]);
        int right = (minPath[row + 1][col + 1] == 0 ? 
                     divideConquer(row + 1, col + 1, minPath,triangle) : minPath[row + 1][col + 1]);
        minPath[row][col] = triangle.get(row).get(col) + Math.min(left, right);
        return minPath[row][col];
    }
}
____________________________________________________________BFS O(S)Space_______________________________________________________________
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        if(size == 0 || triangle.get(0).size() == 0) return 0;
        if(size == 1) return triangle.get(0).get(0);
        
        List<Integer> lastLayer = triangle.get(0);
        int ans = Integer.MAX_VALUE;
        for(int i = 1; i < size; ++i){
            List<Integer> curLayer = new ArrayList();
            int curSize = lastLayer.size() + 1;
            for(int j = 0; j < curSize; ++j){
                
                int lastMin;
                if(j == 0){
                    lastMin = lastLayer.get(0);
                }else if(j == curSize - 1){
                    lastMin = lastLayer.get(j - 1);
                }else{
                    lastMin = Math.min(lastLayer.get(j - 1), lastLayer.get(j));
                }
                
                int curVal = triangle.get(i).get(j);
                curLayer.add(curVal + lastMin);
                
                if(i == size - 1){
                    ans = Math.min(ans, curVal + lastMin);
                }
            }
            lastLayer = curLayer;
        }
        return ans;
    }
}
____________________________________________________________BFS O(1)Space_______________________________________________________________
class Solution {
    // add layer by layer bfs
    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        if(size == 0 || triangle.get(0).size() == 0 ) return 0;
        if(size == 1) return triangle.get(0).get(0);
        
        int ans = Integer.MAX_VALUE;
        for(int i = 1; i < size; ++i){
            List<Integer> curList = triangle.get(i);
            int curSize = curList.size();
            for(int j = 0; j < curSize; ++j){
                
                int lastMin;
                if(j == 0){
                    lastMin = triangle.get(i - 1).get(0);
                }else if(j == curSize - 1){
                    lastMin = triangle.get(i - 1).get(j - 1);
                }else{
                    lastMin = Math.min(triangle.get(i - 1).get(j - 1), triangle.get(i - 1).get(j));
                }
                
                int curVal = curList.get(j);
                curList.set(j, curVal + lastMin);
        
                if(i == size - 1){
                    ans = Math.min(ans, curVal + lastMin);
                }
            }
            triangle.set(i, curList);
        }
        return ans;
    }
}
_____________________________________________________________DFS TLE____________________________________________________________________
class Solution {
    List<List<Integer>> tree;
    int ans = Integer.MAX_VALUE, bottom;
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size() == 0 || triangle.get(0).size() == 0){
            return 0;
        }
        
        bottom = triangle.size();
        tree = triangle;
        find(1, 0, tree.get(0).get(0));
        return ans;
    }
    private void find(int floor, int lastPos, int curSum){
        if(floor == bottom){
            ans = Math.min(curSum, ans);
            return;
        }
        
        List<Integer> curFloor = tree.get(floor);
        
        int curVal = curFloor.get(lastPos);
        find(floor + 1, lastPos, curSum + curVal);
        
        curVal = curFloor.get(lastPos + 1);
        find(floor + 1, lastPos + 1, curSum + curVal);
    }
}
