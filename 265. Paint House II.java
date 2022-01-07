//Best Solution: dp, only need to know the two previous min. if at color i previous is min1 then use min2 to add cost
class Solution {
    public int minCostII(int[][] costs) {
        for (int i = 1; i < costs.length; i++) {
        	updateHouseCosts(costs, i);
        }
        
        int min = costs[costs.length - 1][0];
        for (int i = 1; i < costs[0].length; i++) {
        	min = Math.min(min, costs[costs.length - 1][i]);
        }
        
        return min;
    }
    
	private void updateHouseCosts(int[][] costs, int index) {
		int min1 = Integer.MAX_VALUE;
		int min1Index = 0;
		int min2 = Integer.MAX_VALUE;
//		int min2Index = 0;
		
		for (int i = 0; i < costs[0].length; i++) {
			if (costs[index - 1][i] < min1) {
				min2 = min1;
//				min2Index = min1Index;
				min1 = costs[index - 1][i];
				min1Index = i;
			} else if (costs[index - 1][i] < min2) {
				min2 = costs[index - 1][i];
//				min2Index = i;
			}
		}
		
		for (int i = 0; i < costs[0].length; i++) {
			if (i != min1Index) {
				costs[index][i] += min1;
			} else {
				costs[index][i] += min2;
			}
		}
	}
}

//My Solution: dp, dp[i] = prevMin + cost, dp[i];
//             prevMin = min(latterMin, prevMin) 
class Solution {
    public int minCostII(int[][] costs) {
        int n = costs.length;
        int k = costs[0].length;
        int[] dp = new int[k];
        for(int i = 0; i < k; ++i){
            dp[i] = costs[0][i];
        }
        
        int[] prevMin = new int[k];
        int[] lattMin = new int[k];
        
        prevMin[0] = dp[0];
        lattMin[k - 1] = dp[k - 1];
        for(int i = 1; i < k; ++i){
            prevMin[i] = Math.min(dp[i], prevMin[i - 1]);
            lattMin[k - i - 1] = Math.min(dp[k - i - 1], lattMin[k - i]);
        }
    
        for(int i = 1; i < n; ++i){
            for(int j = 0; j < k; ++j){
                dp[j] = Integer.MAX_VALUE;
                int prev = j == 0 ? Integer.MAX_VALUE : prevMin[j - 1] + costs[i][j];
                int latt = j == k - 1? Integer.MAX_VALUE : lattMin[j + 1] + costs[i][j]; 
                
                dp[j] = Math.min(prev, latt);
            }
            prevMin[0] = dp[0];
            lattMin[k - 1] = dp[k - 1];
            for(int a = 1; a < k; ++a){
                prevMin[a] = Math.min(dp[a], prevMin[a - 1]);
                lattMin[k - a - 1] = Math.min(dp[k - a - 1], lattMin[k - a]);
            }
        }
        return lattMin[0];
    }
}
