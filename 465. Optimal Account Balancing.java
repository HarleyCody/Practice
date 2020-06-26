____________________________________________________________________NPC problem General Solution_________________________________________________________________________________
class Solution {
    //record balance and backtrack
    int maxID;
    int[] debt;
    public int minTransfers(int[][] transactions) {
        maxID = 0;
        for(int[] trans : transactions){
            maxID = Math.max(maxID, trans[0]);
            maxID = Math.max(maxID, trans[1]);
        }
        
        debt = new int[maxID + 1];
        
        for(int[] trans : transactions){
            debt[trans[0]] -= trans[2];
            debt[trans[1]] += trans[2];
        }
        
        return transfer(0);
    }
    
    private int transfer(int cur){
        
        while(cur <= maxID && debt[cur] == 0){
            ++cur;
        }
        int ans = Integer.MAX_VALUE;
        for(int i = cur + 1; i <= maxID && cur <= maxID; ++i){
            if(debt[i] * debt[cur] < 0){
                debt[i] += debt[cur];
                ans = Math.min(ans, 1 + transfer(cur + 1));
                debt[i] -= debt[cur];
            }
        }
        
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
