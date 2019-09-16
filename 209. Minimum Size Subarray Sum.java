______________________________________________________________My Solution(O(n))________________________________________________________
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        // two pointers
        // i records start, j records end;
        
        int i = 0, j = 0, sum = 0, ans = nums.length + 1;
        // if it used to be no less than s; 
        int flag = 0;
        while(j < nums.length){
            while(sum < s){
                sum += nums[j++];
                if(j == nums.length) break;
            }
            flag = sum;
            while(sum >= s && i <= j){
                sum -= nums[i++];
            }
            // used to be no less than s;
            if(flag >= s){
                ans = Math.min(j - i + 1, ans);
            }
        }
        // nums.length + 1, add all elements but sum < s;
        return ans == nums.length + 1 ? 0 : ans;
    }
}
______________________________________________________________My Solution(Nlog(n))_________________________________________________________
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        // start from i calculate contigously until sum >= s;
        // Math.min(ans, length);
        // if ans == length + 1; cannot find valid result; return 0;
        // else return ans;
        if(nums.length == 0) return 0;
        int ans = nums.length + 1;
        for(int i = 0; i < nums.length; i++){
            int sum = 0;
            int j = i;
            int len = 0;
            while(j < nums.length && sum < s){
                sum += nums[j++];
                len++;
            }
            if(sum >= s){
                ans = Math.min(len, ans);
            }
        }
        return ans == nums.length + 1 ? 0 : ans;
    }
}
