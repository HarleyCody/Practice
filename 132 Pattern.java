_______________________________________________O(n) Solution One Pass__________________________________________________________
class Solution {
    public boolean find132pattern(int[] nums) {
        int third = Integer.MIN_VALUE, top = nums.length;
        // nums[i - 1]: left 
        // nums[i]: middle 
        // third: right
        for(int i = nums.length - 1; 0 <= i ; i--){
            // left < right;
            if(nums[i] < third) return true;
            // right < middle, choose the largest right, as only nums[i] > nums[top] right will be updated
            while(top < nums.length && nums[i] > nums[top]) third = nums[top++];
            // middle <= right, record right
            nums[--top] = nums[i];
        }
        return false;
    }
}
____________________________________________________O(n) Solution__________________________________________________________
class Solution {
    public boolean find132pattern(int[] nums) {
        int[] arr = Arrays.copyOf(nums, nums.length);
        for(int i = 1; i < nums.length; i++){
            arr[i] = Math.min(arr[i - 1], nums[i - 1]);
        }
        // three nums i,j,k are respectively arr[i], nums[i], nums[third];
        for(int i = nums.length - 1, third = nums.length; i >=0; i--){
            //assure left < middle
            if(nums[i] == arr[i]) continue;
            // find from left to right assure right > left
            while(third < nums.length && nums[third] <= arr[i]) third++;
            // right < middle then true;
            if(third < nums.length && nums[third] < nums[i]) return true;
            //as nums[i] > arr[i], third >= nums[i] so third > arr[i] so third --record Min of right part;
            nums[--third] = nums[i];
        }
        return false;
    }
}
____________________________________________________O(n^2) Solution__________________________________________________________
class Solution {
    public boolean find132pattern(int[] nums) {
        // min is left value;
        int min = Integer.MAX_VALUE;
        // find middle value
        for(int i = 0; i < nums.length - 1; i++){
            min = Math.min(min, nums[i]);
            // left == middle, goes to next middle value;
            if(min == nums[i]) continue;
            // find last value from right to i;
            for(int j = nums.length - 1; i < j; j--){
                if(min < nums[j] && nums[i] > nums[j]) return true;
            }
        }
        return false;
    }
}
