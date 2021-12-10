// Best Solution: set left and right bound in array and add by presum
class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] ans = new int[length];
        
        for(int[] update : updates){
            ans[update[0]] += update[2];
            if(update[1] == length - 1) continue;
            ans[update[1] + 1] -= update[2];
        }
        
        for(int i = 1; i < length; ++i){
            ans[i] += ans[i - 1];
        }
        return ans;
    }
}
