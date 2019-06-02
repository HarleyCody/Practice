___________________________________________________________My Solution___________________________________________________________________
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0) return nums;
        int[] ans = new int[nums.length - k + 1];
        int max = Integer.MIN_VALUE, i = 0;
        
        while(i < k){
            if(nums[i]>= max)
                max = nums[i];
            ++i;
        }
        
        ans[0] = max;
        for(;i < nums.length; ++i){
            if(max != nums[i-k]){// max is not ruled out
                if(max < nums[i]){
                    max = nums[i];
                }
            }
            else{ // max is ruled out
                max = findMax(nums,i - k + 1, i);
            }
            ans[i-k+1] = max;
        }
        return ans;
    }
    public int findMax(int[] arr, int start, int end){
        int max = Integer.MIN_VALUE, i = start;
        
        while(i <= end){
            if(arr[i] >= max)
                max = arr[i];
            ++i;
        }
        return max;
    }
}
__________________________________________________________________Best Solution_________________________________________________________
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0) return nums;
        int[] ans = new int[nums.length - k + 1];
        int max = Integer.MIN_VALUE, i = 0;
        
        while(i < k){
            if(nums[i]>= max)
                max = nums[i];
            ++i;
        }
        
        ans[0] = max;
        for(;i < nums.length; ++i){
            if(max != nums[i-k]){
                if(max < nums[i]){
                    max = nums[i];
                }
            }
            else{
                max = nums[i];
                int j = i - k + 1;
                while(j <= i){
                    if(nums[j] > max)
                        max = nums[j];
                    ++j;
                }
            }
            ans[i-k+1] = max;
        }
        return ans;
    }
}
