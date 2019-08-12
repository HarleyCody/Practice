
_____________________________________________________Best Solution____________________________________________________________
class Solution {
// duplicated element would only appear twice.
// so Xor will eliminate duplciate element
    public int singleNumber(int[] nums) {
        int ans = 0; 
        for(int i = 0; i < nums.length; i++){
            ans = ans ^ nums[i];
        }
        return ans;
        
    }
}
_______________________________________________________My Solution____________________________________________________________
class Solution {
    public int singleNumber(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        Arrays.sort(nums);
        int ans = nums[0];
        for(int i = 0; i < nums.length; ++i){
            int times = 0;
            while(i < nums.length - 1 && nums[i] == nums[i + 1]){
                ++times;
                ++i;
            }
            if(times == 0) return nums[i];
        }
        return 0;
    }
}
