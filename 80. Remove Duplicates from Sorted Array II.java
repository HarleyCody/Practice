/* My Solution: Use a pointer to set the location of new element that has frequency less than 3
*/
class Solution {
    public int removeDuplicates(int[] nums) {
        int idx = 0;
		int cur = nums[0];
		int curNum = 1;
		for(int i = 1; i < nums.length; ++i){
	        if(nums[i] == cur){
	            if(curNum < 2){
	                nums[++idx] = cur;
                }
            }else{ 
	            cur = nums[i];
	            nums[++idx] = cur;
	            curNum = 0;
            }
            ++curNum;
        }
        return idx + 1;
    }
}
