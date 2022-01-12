//My Solution: Only choose the maxTime to be saved when there are adjascent duplicate color
class Solution {
    public int minCost(String colors, int[] neededTime) {
        int time = 0;
        char[] chs = colors.toCharArray();
        int idx = 0;
        int nIdx = idx;
        int cost = 0;
        int maxCost = 10000;
        
        int ans = 0;
        while(nIdx < chs.length){
            cost = 0;
            maxCost = neededTime[idx];
            while(nIdx < chs.length && chs[idx] == chs[nIdx]){
                cost += neededTime[nIdx];
                maxCost = Math.max(neededTime[nIdx], maxCost);
                ++nIdx;
            }
            if(nIdx > idx + 1){
                ans += cost - maxCost;
            }
            idx = nIdx;
        }
        
        return ans;
    }
}
