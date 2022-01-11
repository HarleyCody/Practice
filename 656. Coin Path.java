//Best Solution: From right to left record the minimal path and cost and construct path at last step
class Solution {
    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        List<Integer> result = new ArrayList<>();
        int length = coins.length;

        if (coins[length - 1] == -1) {
            return result;
        }

        int[] minSumCache = new int[length];
        int[] minSumJumpIndex = new int[length];

        minSumCache[length - 1] = coins[length - 1];
        for (int i = length - 2; i >= 0; i--) {
            if (coins[i] == -1) {
                minSumCache[i] = Integer.MAX_VALUE;
                continue;
            }

            int minSum = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = Math.min(i + maxJump, length - 1); j >= i + 1; j--) {
                if (minSumCache[j] <= minSum) {
                    minSum = minSumCache[j];
                    minIndex = j;
                }
            }

            if (minSum != Integer.MAX_VALUE) {
                minSumCache[i] = coins[i] + minSum;
                minSumJumpIndex[i] = minIndex;
            } else {
                minSumCache[i] = Integer.MAX_VALUE;
                minSumJumpIndex[i] = -1;
            }
        }

        if (minSumJumpIndex[0] == -1) {
            return result;
        }

        result.add(1);
        int k = 0;
        while (k < length -1) {
            result.add(minSumJumpIndex[k] + 1);
            k = minSumJumpIndex[k];
        }

        return result;
    }
}
//My Solution: Record path while get the minimal cost, path need to be update when same cost and same size but current is smaller or cur cost is smaller
class Solution {
    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        int len = coins.length;
        int[] cost = new int[len];
        List<Integer>[] path = new LinkedList[len];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[0] = coins[0];
        path[0] = new LinkedList();
        path[0].add(1);
        for(int i = 0; i < len; ++i){
            if(coins[i] == -1 || cost[i] == Integer.MAX_VALUE) continue;
            for(int j = 1; j <= maxJump && i + j < len; ++j){
                if(coins[i + j] == -1 || cost[i + j] < cost[i] + coins[i + j]) continue;
                if(cost[i + j] == cost[i] + coins[i + j] && path[i + j].size() == path[i].size() + 1 && path[i + j].get(path[i + j].size() - 2) < path[i].get(path[i].size() - 1)){
                    continue;
                }
                cost[i + j] = cost[i] + coins[i + j];
                path[i + j] = new LinkedList(path[i]);
                path[i + j].offer(i + j + 1);
                
            }
        }
        return path[len - 1] == null ? new LinkedList() : path[len - 1];
    }
}
