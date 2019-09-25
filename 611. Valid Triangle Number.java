____________________________________________________Best Solution____________________________ _________________________________
class Solution {
    public int triangleNumber(int[] nums) {
        int count = 0;
        if(nums.length < 3) return 0;
        
        //sort arrays so you can satisfy the left < right < end property to help us speed this up
        Arrays.sort(nums);
        
        //start at end
        for(int end = nums.length - 1; end >= 2; end--){
            int left = 0;
            int right = end-1;
            //can't overlap or else won't have 3 numbers
            while(left < right){
                // must satisfy with end > minus
                if(nums[left] + nums[right] > nums[end]){
                    // left and right is static, any one from left to right can be left.
                    // include all the numbers from left to right - 1( - 1 cannot overlap);
                    count += right - left; 
                    right--; //test a smaller big number to see if it satisfies rule still
                }else{
                    left++; //need a bigger number to make bigger sum
                }
            }
        }
        return count;
    }
}
____________________________________________________My Solution________________________________________________________________
class Solution {
    // choose three num and compare
    public int triangleNumber(int[] nums) {
        int ans = 0;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                for(int k = j + 1; k < nums.length; k++){
                    if(nums[k] < nums[i] + nums[j] && nums[k] >  Math.abs(nums[i] - nums[j])){
                        ans++;
                    }else{
                        break;
                    }
                }
            }
        }
        return ans;
    }
}
