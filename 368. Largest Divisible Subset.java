________________________________________________________My Best Solution_______________________________________________________
class Solution {
    // dp
    // dp[cur] == Longest(dp[valid_prev]) + 1;
    // record end at max size;
    // use prev array to record link
    // use maxSize Array to record max size in pos i;
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums.length == 0) return new ArrayList();
        
        Arrays.sort(nums);
        int len = nums.length;
        
        int[] maxSize = new int[len];
        int[] prev = new int[len];
        
        int maxEnd = 0, ansSize = 0;
        
        for(int i = 0; i < len; ++i){
            prev[i] = i;
            maxSize[i] = 1;
            for(int j = 0; j < i; ++j){
                if(nums[i] % nums[j] == 0){
                    if(maxSize[j] >= maxSize[i]){
                        maxSize[i] = maxSize[j] + 1;
                        prev[i] = j;
                    }
                }
            }
            if(maxSize[i] > ansSize){
                ansSize = maxSize[i];
                maxEnd = i;
            }
        }
        
        List<Integer> ans = new ArrayList();
        ans.add(nums[maxEnd]);
        while(prev[maxEnd] != maxEnd){
            maxEnd = prev[maxEnd];
            ans.add(nums[maxEnd]);
        }
        return ans;
    }
}
____________________________________________________________My General Solution________________________________________________
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if(nums.length == 0) return new ArrayList();
        
        Arrays.sort(nums);
        int len = nums.length;
        
        List<Integer>[] dp = new ArrayList[len];
        
        List<Integer> ans = new ArrayList();
        for(int i = 0; i < len; ++i){
            dp[i] = new ArrayList();
            for(int j = 0; j <= i; ++j){
                if(nums[i] % nums[j] == 0){
                    if(dp[j].size() >= dp[i].size()){
                        dp[i] = new ArrayList(dp[j]);
                    }
                }
            }
            dp[i].add(nums[i]);
            if(dp[i].size() > ans.size()){
                ans = dp[i];
            }
        }
        return ans;
    }
}
